package org.softwire.training.app;

import org.softwire.training.models.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

class OrmAccess {

    private final EntityManagerFactory entityManagerFactory;

    OrmAccess() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(
                "org.softwire.training");
    }

    List<Employee> getAllEmployeesWithHql() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Employee> employees = em.createQuery("FROM Employee", Employee.class).getResultList();

        em.close();

        return employees;
    }

    Employee getEmployeeWithSql(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Employee> results = em.createNativeQuery("SELECT * FROM employees WHERE id=?1", Employee.class)
                .setParameter(1, id)
                .getResultList();
        em.close();

        return results.isEmpty() ? null : results.get(0);
    }

    Employee getEmployeeWithoutSql(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Employee employee = em.find(Employee.class, id);

        em.close();

        return employee;
    }

    void updateEmployeeWithSql(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.createNativeQuery("UPDATE employees SET first_name=?1, last_name=?2, position=?3, age=?4, salary=?5 WHERE id=?6")
                .setParameter(1, employee.getFirstName())
                .setParameter(2, employee.getLastName())
                .setParameter(3, employee.getPosition())
                .setParameter(4, employee.getAge())
                .setParameter(5, employee.getSalary())
                .setParameter(6, employee.getId())
                .executeUpdate();

        transaction.commit();
        em.close();
    }

    void updateEmployeeWithoutSql(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.merge(employee);

        transaction.commit();
        em.close();
    }
}
