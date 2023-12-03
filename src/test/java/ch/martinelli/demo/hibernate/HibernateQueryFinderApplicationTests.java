package ch.martinelli.demo.hibernate;

import ch.martinelli.demo.hibernate.domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Transactional
@Import(TestHibernateQueryFinderApplication.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HibernateQueryFinderApplicationTests {

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void createTestData() {
        var employee = new Employee();
        employee.setFirstName("Peter");
        employee.setLastName("Muster");

        em.persist(employee);
        em.flush();
    }

    @Test
    void contextLoads() {
        Stream<Employee> stream = em.createQuery("select e from Employee e", Employee.class)
                .getResultStream();

        stream.forEach(System.out::println);
    }

}
