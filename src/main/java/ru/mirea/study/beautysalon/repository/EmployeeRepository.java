package ru.mirea.study.beautysalon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.study.beautysalon.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}