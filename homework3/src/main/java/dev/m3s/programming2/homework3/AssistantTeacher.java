package dev.m3s.programming2.homework3;

import java.util.List;
import java.util.ArrayList;

public class AssistantTeacher extends Employee implements Teacher,Payment{
    private List<DesignatedCourse> courses = new ArrayList<>();

    public AssistantTeacher(String lname, String fname){
        super(lname, fname);
    }

    @Override
    public String getEmployeeIdString(){
        return "OY_ASSISTANT_";
    }

    @Override
    public String getCourses(){
        StringBuilder coursesString = new StringBuilder();
        if (this.courses != null) {
            for(DesignatedCourse course : courses){
                if(course != null){
                    coursesString.append(course.toString()).append("\n");
                }
            }
        }
        return coursesString.toString();
        
    }

    public void setCourses(List<DesignatedCourse> courses){
        if(courses != null) {
            this.courses = courses;
        }
    }

    @Override
    public String toString(){
        
        return String.format("""
            Teacher id: %s
                First name: %s, Last name: %s
                Birthdate: %s
                Salary: %.2f
                Assistant for courses:
                %s
                """, super.getIdString(), getFirstName(), getLastName(), super.getBirthDate(), super.calculatePayment(), getCourses());
    }
}
