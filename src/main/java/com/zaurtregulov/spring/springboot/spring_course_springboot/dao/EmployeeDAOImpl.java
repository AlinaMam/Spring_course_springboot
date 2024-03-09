package com.zaurtregulov.spring.springboot.spring_course_springboot.dao;

import com.zaurtregulov.spring.springboot.spring_course_springboot.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
      /*  Session session = entityManager.unwrap(Session.class);
        List<Employee> allEmployees = session.createQuery("from Employee", Employee.class).getResultList();*/
        List<Employee> allEmployees = entityManager.createQuery("from Employee", Employee.class).getResultList();
        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
       /* Session session = entityManager.unwrap(Session.class);
        Employee emp = session.merge(employee);*/
        Employee newEmployee = entityManager.merge(employee);
        employee.setId(newEmployee.getId());
    }

    @Override
    public Employee getEmployee(int id) {
        /*Session session = entityManager.unwrap(Session.class);
        Employee employee = session.get(Employee.class, id);*/
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
       /* Session session = entityManager.unwrap(Session.class);
        session.createMutationQuery("delete from Employee where id = :employeeId")
                .setParameter("employeeId", id)
                .executeUpdate();*/
        entityManager.createQuery("delete from Employee where id = :employeeId")
                .setParameter("employeeId", id)
                .executeUpdate();
    }
}
