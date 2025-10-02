import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary);
    }
}

public class Menu {
    private static final String filename = "employees.ser";

    public static void addEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Designation: ");
        String designation = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        Employee emp = new Employee(id, name, designation, salary);

        try {
            ArrayList<Employee> list = new ArrayList<>();
            File file = new File(filename);
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                list = (ArrayList<Employee>) ois.readObject();
                ois.close();
            }
            list.add(emp);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(list);
            oos.close();
            System.out.println("Employee added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayEmployees() {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("No employee records found.");
                return;
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            ArrayList<Employee> list = (ArrayList<Employee>) ois.readObject();
            ois.close();
            if (list.isEmpty()) {
                System.out.println("No employee records available.");
            } else {
                System.out.println("Employee Records:");
                for (Employee e : list) {
                    e.display();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
