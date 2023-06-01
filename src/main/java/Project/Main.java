package Project;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.Scanner;


public class Main {
    private static final Logger LOGGER
            = LogManager.getLogger(StudentService.class);

    public static void main(String[] args) {
        Student student = new Student();
        StudentService studentService = new StudentService();
        Scanner scanner = new Scanner(System.in);
        studentService.readFromFile();
        String mainMenuOption = "";
        while (!mainMenuOption.equals("Q")) {
            mainMenuText();
            System.out.print("Please, enter your option: ");
            mainMenuOption = scanner.next();
            mainMenuOption = mainMenuOption.toUpperCase();
            switch (mainMenuOption) {
                case ("A") -> {
                    studentService.addStudent();
                    System.out.println();
                }
                case ("R") -> {
                    studentService.removeRecord();
                    System.out.println();
                }
                case ("CR") -> {
                    studentService.makeReport();
                    System.out.println();
                }
                case ("S") -> {
                    studentService.showRecords();
                    System.out.println();
                }
                case ("F") -> {
                    studentService.findRecordByID();
                    System.out.println();
                }
                case ("Q") -> {
                    LOGGER.info("Execute: Close program" + "\n");
                    System.out.println("Do you want save changes?");
                    System.out.println("Y=Yes, N=No");
                    String exit = "";
                    boolean correct = false;
                    while (!correct) {
                        exit = scanner.next();
                        exit = exit.toUpperCase();
                        if (exit.equals("Y")) {
                            studentService.writeToFile();
                            LOGGER.info("Changes was saved" + "\n");
                            correct = true;
                        } else if (exit.equals("N")) {
                            LOGGER.info("Exit without changes" + "\n");
                            correct = true;
                        } else {
                            System.out.println("No such option");
                        }
                    }
                    System.out.println();
                }
                default -> System.out.println("No such option as: " + mainMenuOption + "\n");
            }
        }
    }

    public static void mainMenuText() {
        System.out.println("Please, chose one of options:");
        System.out.println("A = Add student record");
        System.out.println("R = Remove student record");
        System.out.println("CR = Create report");
        System.out.println("S = Show all records");
        System.out.println("F = Find student by student ID");
        System.out.println("Q = Close program");
    }
}