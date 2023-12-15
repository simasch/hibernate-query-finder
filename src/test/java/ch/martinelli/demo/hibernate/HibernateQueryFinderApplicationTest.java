package ch.martinelli.demo.hibernate;

import ch.martinelli.demo.hibernate.domain.Employee;
import ch.martinelli.demo.hibernate.domain.EmployeeDao_;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.AssertionInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Import(TestHibernateQueryFinderApplication.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HibernateQueryFinderApplicationTest {

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

    @Test
    void findByFirstName() {
        List<Employee> employees = EmployeeDao_.findAllByFirstName(em, "Peter");

        assertThat(employees).hasSize(1);
        assertThat(employees.get(0).getFirstName()).isEqualTo("Peter");
    }

    @Test
    void findAll() {
        List<Employee> employees = EmployeeDao_.findAll(em);

        assertThat(employees).hasSize(1);
    }

}
