package com.company;

public class Employee {

    private int id;
    private String name;
    private String department;
    private String position;
    private double salary;

    public Employee(
            int id,
            String name,
            String department,
            String position,
            double salary) {

        this.id = id;
        this.name = name;
        this.department = department;
        this.position = position;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }
}
