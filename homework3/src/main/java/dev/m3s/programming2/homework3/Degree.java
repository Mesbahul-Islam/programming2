package dev.m3s.programming2.homework3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Degree{
    private static final int MAX_COURSES = 50;
    private String degreeTitle = ConstantValues.NO_TITLE;
    private String titleOfThesis = ConstantValues.NO_TITLE;
    private List<StudentCourse> myCourses = new ArrayList<>();
    public List<StudentCourse> getCourses() {
        return myCourses;
    }
    public void addStudentCourses(List<StudentCourse> courses){
        if(courses != null){
            for(StudentCourse course : courses){
                addStudentCourse(course);
            }
        }
    }
    public boolean addStudentCourse(StudentCourse course){
        if(course != null && myCourses.size() < MAX_COURSES){
            myCourses.add(course);
            return true;
        }
        return false;
    }

    public String getDegreeTitle() {
        return degreeTitle;
    }

    public void setDegreeTitle(String degreeTitle) {
        if (degreeTitle != null) {
            this.degreeTitle = degreeTitle;
        }
    }

    public String getTitleOfThesis() {
        return titleOfThesis;
    }

    public void setTitleOfThesis(String titleOfThesis) {
        if (titleOfThesis != null)
            this.titleOfThesis = titleOfThesis;
    }

    public double getCreditsByBase(Character base) {
        List<StudentCourse> courses = getCourses();
        double credits = 0;
        for (StudentCourse course : courses) {
            if (course != null && Character.toUpperCase(course.getCourse().getCourseBase()) == Character.toUpperCase(base) && isCourseCompleted(course)) {
                credits += course.getCourse().getCredits();
            }
        }
        return credits;
    }

    public double getCreditsByType(final int courseType) {
        List<StudentCourse> courses = getCourses();
        double credits = 0;
        if (courseType == ConstantValues.OPTIONAL) {
            for (StudentCourse course : courses) {
                if (course != null && course.getCourse().getCourseType() == ConstantValues.OPTIONAL && isCourseCompleted(course)) {
                    credits += course.getCourse().getCredits();
                }
            }
        } 
        else if (courseType == ConstantValues.MANDATORY) {
            for (StudentCourse course : courses) {
                if (course != null && course.getCourse().getCourseType() == ConstantValues.MANDATORY && isCourseCompleted(course)) {
                    credits += course.getCourse().getCredits();
                }
            }
        
        }
        else if (courseType == ConstantValues.ALL){
            credits = getCredits();
        }
        return credits;
    }

    public double getCredits() {
        List<StudentCourse> courses = getCourses();
        double credits = 0;
        for (StudentCourse c : courses) {
            if (isCourseCompleted(c)){
                credits += c.getCourse().getCredits();
            }
        }
        return credits;
    }



    private boolean isCourseCompleted(StudentCourse c) {
        return c != null && c.isPassed();
    }

    public void printCourses() {
        for (int i = 0; i < myCourses.size(); i++) {
            if (myCourses.get(i) != null) {
                System.out.println(myCourses.get(i).toString());
            }
        }
    }
    public List<Double> getGPA(int type){
        double sum = 0.0;
        double count = 0.0;
        double average = 0.0;
        List<StudentCourse> courses = getCourses();
        for(StudentCourse course : courses){
            if((type == ConstantValues.ALL || course.getCourse().getCourseType() == type) && course.getCourse().isNumericGrade()){
                sum += course.getGradeNum();
                count++;
            }
        }
        if(count != 0){
            average = sum/count;
            BigDecimal bd = new BigDecimal(Double.toString(average));
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            average = bd.doubleValue();
        }
        return new ArrayList<Double>(Arrays.asList(sum, count, average));
    }
    @Override
    public String toString() {
        StringBuilder coursesString = new StringBuilder();
        List<StudentCourse> courses = getCourses();

        for (int i = 0; i < myCourses.size(); i++) {
            if (myCourses.get(i) != null) {
                coursesString.append(i + 1).append(". ").append(courses.get(i)).append("\n");
            }
        }

        return String.format("""
            Degree [Title: "%s" (courses: %d)
            Thesis title: "%s"
            %s
            """, getDegreeTitle(), myCourses.size(), getTitleOfThesis(), coursesString.toString());
    }


}
