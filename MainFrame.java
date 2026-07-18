package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTextField nameField;
    private JTextField departmentField;
    private JTextField positionField;
    private JTextField salaryField;

    private JTable employeeTable;

    private DefaultTableModel tableModel;

    public MainFrame() {

        setTitle("Employee Management System");

        setSize(1000, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        createGUI();
    }

    private void createGUI() {

        setLayout(new BorderLayout());

        createHeader();

        createForm();

        createTable();
    }

    private void createHeader() {

        JPanel header = new JPanel();

        header.setBackground( new Color(45, 85, 140));

        JLabel title = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");

        title.setForeground(Color.WHITE);

        title.setFont( new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        header.add(title);

        add(
                header,
                BorderLayout.NORTH
        );
    }

    private void createForm() {

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                5,
                                2,
                                10,
                                10
                        )
                );

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Employee Information"
                )
        );

        nameField =
                new JTextField();

        departmentField =
                new JTextField();

        positionField =
                new JTextField();

        salaryField =
                new JTextField();

        JButton addButton =
                new JButton(
                        "Add Employee"
                );

        JButton clearButton =
                new JButton(
                        "Clear"
                );

        formPanel.add(
                new JLabel(
                        "Employee Name:"
                )
        );

        formPanel.add(nameField);

        formPanel.add(
                new JLabel(
                        "Department:"
                )
        );

        formPanel.add(
                departmentField
        );

        formPanel.add(
                new JLabel(
                        "Position:"
                )
        );

        formPanel.add(
                positionField
        );

        formPanel.add(
                new JLabel(
                        "Salary:"
                )
        );

        formPanel.add(
                salaryField
        );

        formPanel.add(addButton);

        formPanel.add(clearButton);

        add(
                formPanel,
                BorderLayout.NORTH
        );

        addButton.addActionListener(e -> {

            addEmployee();
        });

        clearButton.addActionListener(e -> {

            clearFields();
        });
    }

    private void createTable() {

        tableModel =
                new DefaultTableModel(
                        new String[]{
                                "ID",
                                "Name",
                                "Department",
                                "Position",
                                "Salary"
                        },
                        0
                );

        employeeTable =
                new JTable(
                        tableModel
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        employeeTable
                );

        add(
                scrollPane,
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel();

        JButton deleteButton =
                new JButton(
                        "Delete Selected Employee"
                );

        bottomPanel.add(
                deleteButton
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        deleteButton.addActionListener(e -> {

            deleteEmployee();
        });
    }

    private void addEmployee() {

        String name =
                nameField.getText();

        String department =
                departmentField.getText();

        String position =
                positionField.getText();

        String salaryText =
                salaryField.getText();

        if (
                name.isEmpty()
                        ||
                        department.isEmpty()
                        ||
                        position.isEmpty()
                        ||
                        salaryText.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!"
            );

            return;
        }

        try {

            double salary =
                    Double.parseDouble(
                            salaryText
                    );

            DataStore.addEmployee(
                    name,
                    department,
                    position,
                    salary
            );

            refreshTable();

            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Employee added successfully!"
            );

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid salary!"
            );
        }
    }

    private void refreshTable() {

        tableModel.setRowCount(0);

        for (
                Employee employee :
                DataStore.employees
        ) {

            tableModel.addRow(
                    new Object[]{
                            employee.getId(),
                            employee.getName(),
                            employee.getDepartment(),
                            employee.getPosition(),
                            employee.getSalary()
                    }
            );
        }
    }

    private void clearFields() {

        nameField.setText("");

        departmentField.setText("");

        positionField.setText("");

        salaryField.setText("");
    }

    private void deleteEmployee() {

        int selectedRow =
                employeeTable
                        .getSelectedRow();

        if (
                selectedRow == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an employee!"
            );

            return;
        }

        int employeeId =
                (int) tableModel.getValueAt(
                        selectedRow,
                        0
                );

        DataStore.deleteEmployee(
                employeeId
        );

        refreshTable();

        JOptionPane.showMessageDialog(
                this,
                "Employee deleted successfully!"
        );
    }
}
