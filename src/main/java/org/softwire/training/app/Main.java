package org.softwire.training.app;

import org.flywaydb.core.Flyway;
import org.softwire.training.models.Employee;
import org.softwire.training.properties.Creds;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, java.io.IOException {

        Creds creds = new Creds().getCreds();

        Flyway flyway = Flyway.configure().dataSource(
                creds.getUrl(),
                creds.getUser(),
                creds.getPassword()).load();
        flyway.migrate();

        displayEmployeesDirectFromDatabase();
        displayEmployeesFirstNamesWithHql();
        getEmployeeWithSql();
        getEmployeeWithoutSql();
        updateEmployeeWithSql();
        updateEmployeeWithoutSql();
    }

    private static void displayEmployeesDirectFromDatabase() throws SQLException, java.io.IOException {
        System.out.println("Direct access employees");
        List<String> ownerNames = new DirectAccess().getAllEmployeeNames();
        System.out.println(ownerNames);
        System.out.println();
    }

    private static void displayEmployeesFirstNamesWithHql() {
        System.out.println("ORM access with HQL");
        List<Employee> employees = new OrmAccess().getAllEmployeesWithHql();
        System.out.println(Arrays.toString(employees.stream().map(Employee::getFirstName).toArray()));
        System.out.println();
    }

    private static void getEmployeeWithSql() {
        System.out.println("ORM access with SQL");
        System.out.println(new OrmAccess().getEmployeeWithSql(6).toArrayString());
        System.out.println();
    }

    private static void getEmployeeWithoutSql() {
        System.out.println("ORM access without SQL");
        Employee employee = new OrmAccess().getEmployeeWithoutSql(2);
        System.out.println(employee.toArrayString());
        System.out.println();
    }

    private static void updateEmployeeWithSql() {
        System.out.println("ORM update with SQL");
        OrmAccess ormAccess = new OrmAccess();
        Employee employee = new OrmAccess().getEmployeeWithoutSql(2);
        System.out.println(employee.toArrayString());
        employee.setAge(employee.getAge() + 1);
        ormAccess.updateEmployeeWithSql(employee);
        System.out.println(employee.toArrayString());
        System.out.println();
    }

    private static void updateEmployeeWithoutSql() {
        System.out.println("ORM update without SQL");
        OrmAccess ormAccess = new OrmAccess();
        Employee employee = new OrmAccess().getEmployeeWithoutSql(3);
        System.out.println(employee.toArrayString());
        employee.setAge(employee.getAge() + 1);
        ormAccess.updateEmployeeWithoutSql(employee);
        System.out.println(ormAccess.getEmployeeWithoutSql(3).toArrayString());
    }
}