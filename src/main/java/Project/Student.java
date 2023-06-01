package Project;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private String name;
    private String studentID;
    private String email;
    private Date dateOfBirth;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", studentID='" + studentID + '\'' + ", email='" + email + '\'' +", dateOfBirth=" + dateOfBirth + '}';
    }
}

