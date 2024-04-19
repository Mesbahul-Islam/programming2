package dev.m3s.programming2.homework3;

import java.time.Year;

public class StudentCourse {
    private Course course;
    private int gradeNum;
    private int yearCompleted;

    public StudentCourse() {
    }

    public StudentCourse(Course course, final int gradeNum, final int yearCompleted) {
        this.setCourse(course);
        if (checkGradeValidity(gradeNum))
            this.gradeNum = gradeNum;
        if (yearCompleted > 2000 && yearCompleted <= Year.now().getValue()) {
            this.yearCompleted = yearCompleted;
        }
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGradeNum() {
        return gradeNum;
    }

    protected void setGrade(int gradeNum) {
        if (checkGradeValidity(gradeNum)) {
            this.gradeNum = gradeNum;
        }
        if (yearCompleted == 0) {
            this.yearCompleted = Year.now().getValue();
        }
    }

    private boolean checkGradeValidity(final int gradeNum) {
        if (course.isNumericGrade()) {
            return gradeNum >= 0 && gradeNum <= 5;
        } else {
            return gradeNum == 'A' || gradeNum == 'F' || gradeNum == 'a' || gradeNum == 'f';
        }
    }

    public boolean isPassed() {
        if(course.isNumericGrade()){
            return gradeNum > ConstantValues.MIN_GRADE;
        } else {
            return gradeNum == 'A' || gradeNum == 'a';
        }
    }

    public int getYear() {
        return this.yearCompleted;
    }

    public void setYear(final int yearCompleted) {
        if (yearCompleted > 2000 && yearCompleted <= Year.now().getValue()) {
            this.yearCompleted = yearCompleted;
        }
    }

    public String toString() {
        String grade;
        int numGrade = getGradeNum();
        int year = getYear();
        if (numGrade == 0) {
            grade = "Not graded";
        } else if (numGrade == 'F' || numGrade == 'A' || numGrade == 'f' || numGrade == 'a') {
            grade = "" + Character.toUpperCase((char) numGrade);
        } else {
            grade = String.valueOf(numGrade);
        }
        return String.format("""
                [%s Year: %d, Grade: %s.]
                """, course.toString(), year, grade);
    }
}

