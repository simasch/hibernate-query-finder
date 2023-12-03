package ch.martinelli.demo.hibernate.domain;

import org.hibernate.annotations.processing.Find;

import java.util.List;
import java.util.stream.Stream;

public interface EmployeeDao {

    @Find
    List<Employee> findEmployees();

    @Find
    List<Employee> findEmployeesByFirstName(String firstName);
}
