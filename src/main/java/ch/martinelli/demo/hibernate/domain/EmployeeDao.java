package ch.martinelli.demo.hibernate.domain;

import org.hibernate.annotations.processing.Find;
import org.hibernate.annotations.processing.HQL;

import java.util.List;

public interface EmployeeDao {

    @Find
    List<Employee> findAllByFirstName(String firstName);

    @HQL("select e from Employee e")
    List<Employee> findAll();
}
