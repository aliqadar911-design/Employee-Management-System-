

package com.company;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    public static List<Employee> employees = new ArrayList<>();

    private static int employeeId = 1;

    public static void addEmployee(String name, String department, String position, double salary) {

        Employee employee = new Employee( employeeId++, name, department, position, salary);

        employees.add(employee);
    }

    public static Employee findEmployee(int id) {

        for (Employee employee : employees) {

            if (employee.getId() == id) {

                return employee;
            }
        }

        return null;
    }

    public static void deleteEmployee(int id) {

        Employee employee = findEmployee(id);

        if (employee != null) {

            employees.remove(employee);
        }
    }
}
