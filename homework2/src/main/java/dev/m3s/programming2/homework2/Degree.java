package dev.m3s.programming2.homework2;

public class Degree {
    private static final int MAX_COURSES = 50;
    private int count = 0;
    private String degreeTitle = ConstantValues.NO_TITLE;
    private String titleOfThesis = ConstantValues.NO_TITLE;
    private StudentCourse[] myCourses = new StudentCourse[MAX_COURSES];

    public StudentCourse[] getCourses() {
        return myCourses.clone();
    }

    public void addStudentCourses(StudentCourse[] courses) {
        if (courses != null) {
            for (StudentCourse course : courses) {
                addStudentCourse(course);
            }
        }
    }

    public boolean addStudentCourse(StudentCourse course) {
        if (course != null && count < MAX_COURSES) {
            myCourses[count] = course;
            count++;
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
        StudentCourse[] courses = getCourses();
        double credits = 0;
        for (int i = 0; i < count; i++) {
            if (courses[i] != null && courses[i].getCourse().getCourseBase() == base) {
                credits += courses[i].getCourse().getCredits();
            }
        }
        return credits;
    }

    public double getCreditsByType(final int courseType) {
        StudentCourse[] courses = getCourses();
        double credits = 0;
        for (int i = 0; i < count; i++) {
            if (courses[i] != null && courses[i].getCourse().getCourseType() == courseType) {
                credits += courses[i].getCourse().getCredits();
            }
        }
        return credits;
    }

    public double getCredits() {
        StudentCourse[] courses = getCourses();
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
        for (int i = 0; i < count; i++) {
            if (myCourses[i] != null) {
                System.out.println(myCourses[i].toString());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder coursesString = new StringBuilder();
        StudentCourse[] courses = getCourses();

        for (int i = 0; i < count; i++) {
            if (courses[i] != null) {
                coursesString.append(i + 1).append(". ").append(courses[i]).append("\n");
            }
        }

        return String.format("""
            Degree [Title: "%s" (courses: %d)
            Thesis title: "%s"
            %s
            """, getDegreeTitle(), count, getTitleOfThesis(), coursesString.toString());
    }
}
