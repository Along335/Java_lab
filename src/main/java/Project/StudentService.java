package Project;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentService implements Serializable {
    private static final Logger LOGGER = LogManager.getLogger(StudentService.class);
    private List<Student> listOfStudents = new ArrayList<>();
    Scanner scanner;

    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    public void addStudent() {
        LOGGER.info("Execute: addStudent()" + "\n");
        Student newStudent = new Student();
        scanner = new Scanner(System.in);
        System.out.println("Please, enter student name:");
        newStudent.setName(scanner.nextLine());
        System.out.println("Please, enter student ID:");
        newStudent.setStudentID(scanner.nextLine());
        System.out.println("Please, enter student mail:");
        newStudent.setEmail(scanner.nextLine());
        boolean isCorrect = false;
        while (!isCorrect) {
            System.out.println("Please, enter student day of birth in format \"dd/MM/yyyy\":");
            String userInput = scanner.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date = dateFormat.parse(userInput);
                newStudent.setDateOfBirth(date);
                listOfStudents.add(newStudent);
                isCorrect = true;
                LOGGER.info("Record has been added");
            } catch (Exception e) {
                LOGGER.error("Invalid date format. Please enter a date in the format dd/MM/yyyy.");
            }
        }
    }

    public void removeRecord() {
        LOGGER.info("Execute: removeRecord()" + "\n");
        if (listOfStudents.isEmpty()) {
            LOGGER.error("Records not found");
        } else {
            scanner = new Scanner(System.in);
            String id;
            System.out.print("Please, enter student ID: ");
            id = scanner.nextLine();
            boolean wasFound = false;
            for (Student x : listOfStudents) {
                if (x.getStudentID().equals(id)) {
                    listOfStudents.remove(x);
                    LOGGER.info("Record was deleted" + "\n");
                    wasFound = true;
                    break;
                }
            }
            if (!wasFound) {
                LOGGER.info("Record not found, deletion failed" + "\n");
            }
        }
    }


    public void showRecords() {
        LOGGER.info("Execute: showRecords()" + "\n");
        if (listOfStudents.isEmpty()) {
            LOGGER.error("Records not found");
        } else {
            for (Student x : listOfStudents) {
                System.out.println(x);
            }
        }
    }

    public void findRecordByID() {
        LOGGER.info("Execute: findRecordByID()" + "\n");
        if (listOfStudents.isEmpty()) {
            LOGGER.error("Records not found");
        } else {
            scanner = new Scanner(System.in);
            String id;
            System.out.print("Please, enter student ID: ");
            id = scanner.nextLine();
            boolean wasFound = false;
            for (Student x : listOfStudents) {
                if (x.getStudentID().equals(id)) {
                    System.out.println(x);
                    LOGGER.info("Record was found" + "\n");
                    wasFound = true;
                }
            }
            if (!wasFound) {
                LOGGER.info("Record wasn't found" + "\n");
            }
        }
    }

    public void writeToFile() {
        LOGGER.info("Execute: writeToFile()" + "\n");
        try {
            FileOutputStream fileOut = new FileOutputStream("Save.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(listOfStudents);
            out.close();
            LOGGER.info("Records saved" + "\n");

        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error("Records wasn't saved" + "\n");
        }
    }

    public void readFromFile() {
        LOGGER.info("Execute: readFromFile()" + "\n");
        try {
            FileInputStream fileIn = new FileInputStream("Save.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            listOfStudents = (List<Student>) objectIn.readObject();
            fileIn.close();
            LOGGER.info("File was read" + "\n");

        } catch (Exception ex) {
            LOGGER.error("File wasn't read" + "\n");
        }
    }

    public void makeReport() {
        LOGGER.info("Execute: makeReport()" + "\n");
        try {
            FileWriter writer = new FileWriter("Report.txt");
            for (Student student : listOfStudents) {
                writer.write(student.toString() + "\n");
            }
            writer.close();
            LOGGER.info("Report created" + "\n");
        } catch (IOException e) {
            LOGGER.error("Report creation failed" + "\n");
            throw new RuntimeException(e);
        }
    }
}