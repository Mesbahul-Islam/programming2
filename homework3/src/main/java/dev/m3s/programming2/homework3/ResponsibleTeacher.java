package dev.m3s.programming2.homework3;

import java.util.ArrayList;
import java.util.List;

public class ResponsibleTeacher extends Employee implements Teacher,Payment{
    private List<DesignatedCourse> courses = new ArrayList<>();

    public ResponsibleTeacher(String lname, String fname){
        super(lname, fname);
    }

    @Override
    public String getEmployeeIdString(){
        return "OY_TEACHER_";
    }

    @Override
    public String getCourses() {
        StringBuilder coursesString = new StringBuilder();
        for (DesignatedCourse course : courses) {
            if (course.isResponsible()) {
                coursesString.append("Responsible teacher: ");
            } else {
                coursesString.append("Teacher: ");
            }
            coursesString.append(course.toString()).append("\n");
        }
        return coursesString.toString();
    }

    public void setCourses(List<DesignatedCourse> courses){
        if (courses != null)
            this.courses = courses;
    }

    @Override
    public String toString() {
        return String.format("""
            Teacher id: %s
            First name: %s, Last name: %s
            Birthdate: %s
            Salary: %.2f
            Teacher for courses:
            %s
            """, getIdString(), getFirstName(), getLastName(), super.getBirthDate(), super.calculatePayment(), getCourses());
    }
}
