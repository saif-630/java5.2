import java.io.*;

class Student implements Serializable {
    private int studentID;
    private String name;
    private String grade;

    public Student(int studentID, String name, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    public void display() {
        System.out.println("ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Grade: " + grade);
    }
}

public class seri {
    public static void main(String[] args) {
        String filename = "student.ser";

        Student s1 = new Student(101, "Saif", "A");

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(s1);
            oos.close();
            System.out.println("Serialization successful.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            Student s2 = (Student) ois.readObject();
            ois.close();
            System.out.println("Deserialization successful.");
            s2.display();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
