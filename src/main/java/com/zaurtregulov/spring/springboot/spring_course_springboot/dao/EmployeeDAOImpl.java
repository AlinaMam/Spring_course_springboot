package com.zaurtregulov.spring.rest.dao;

import com.zaurtregulov.spring.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private SessionFactory factory;

    @Autowired
    public EmployeeDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> allEmployees = null;
        try (Session session = factory.openSession()) {
            allEmployees = session.createQuery("from Employee", Employee.class).getResultList();
        }
        return allEmployees;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Session session = factory.getCurrentSession();
        Employee emp = session.merge(employee);
        return emp;
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = factory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = factory.getCurrentSession();
        session.createMutationQuery("delete from Employee where id = :employeeId")
                .setParameter("employeeId", id)
                .executeUpdate();
    }
}
