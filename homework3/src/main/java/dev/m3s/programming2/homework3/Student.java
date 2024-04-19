package dev.m3s.programming2.homework3;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private int id;
    private int startYear = Year.now().getValue();
    private int graduationYear;
    private List<Degree> degrees = new ArrayList<>();

    public Student(String lname, String fname){
        super(lname, fname);
        for(int i = 0; i < 3; i++){
            degrees.add(new Degree());
        }
        this.id = getRandomId(1, 100);
    }


    public int getId(){
        return id;
    }


    public void setId(final int id){
        if (id >= 1 && id <= 100){
            this.id = id;
        }
    }


    public int getStartYear(){
        return startYear;
    }


    public void setStartYear(final int startYear){
        if(startYear > 2000 && startYear <= Year.now().getValue()){
            this.startYear = startYear;
        }
    }


    public int getGraduationYear(){
        return graduationYear;
    }


    public String setGraduationYear(final int graduationYear){
        if(!canGraduate()){
            return "Check amount of required credits";
        }
        else if(graduationYear < startYear || graduationYear > Year.now().getValue()){
            return "Check graduation year";
        }
        else{
            this.graduationYear = graduationYear;
            return "Ok";
        }
        
    }


    public void setDegreeTitle(final int i, String dName){
        if(i >=0 && i < degrees.size() && dName != null){
            degrees.get(i).setDegreeTitle(dName);
        }
    }


    public boolean addCourse(final int i, StudentCourse course){
        if(i >= 0 && i < degrees.size() && course != null){
            return degrees.get(i).addStudentCourse(course);
        }
        return false;
    }


    public int addCourses(final int i, List<StudentCourse> courses){
        int count = 0;
        if(courses != null){
            for(StudentCourse course: courses){
                if(i >= 0 && i < degrees.size() && course != null && addCourse(i, course)){
                    count++;
                }
            }
        }
        return count;
    }


    public void printCourses(){
        for(Degree d : degrees){
            if(d != null){
                d.printCourses();
            }
        }
    }


    public void printDegrees(){
        for(Object o : degrees){
            if (o != null)
                System.out.print(o.toString());
        }
    }


    public void setTitleOfThesis(final int i, String title){
        if(i >= 0 && i < degrees.size() && title != null)
            degrees.get(i).setTitleOfThesis(title);
    }


    public boolean hasGraduated(){
        return graduationYear != 0;
    }


    private boolean canGraduate(){
        double bachCreditsAll = 0;
        double masCreditsAll = 0;
        String bachelorTitle = "";
        String masTitle = "";
        bachCreditsAll = degrees.get(0).getCreditsByType(2);
        bachelorTitle = degrees.get(0).getTitleOfThesis();
        masCreditsAll = degrees.get(1).getCreditsByType(2);
        masTitle = degrees.get(1).getTitleOfThesis();
        double bachCreditMandatory = degrees.get(0).getCreditsByType(1);
        double masCreditMandatory = degrees.get(1).getCreditsByType(1);

        return (bachCreditsAll >= ConstantValues.BACHELOR_CREDITS && masCreditsAll >= ConstantValues.MASTER_CREDITS
                && !bachelorTitle.equals(ConstantValues.NO_TITLE) && !masTitle.equals(ConstantValues.NO_TITLE)
                && bachCreditMandatory >= ConstantValues.BACHELOR_MANDATORY && masCreditMandatory >= ConstantValues.MASTER_MANDATORY);
    }


    public int getStudyYears(){
        int startYear = getStartYear();
        if (!hasGraduated() && startYear == Year.now().getValue()){
            return 0;
            
        }
        if(!hasGraduated() && startYear != Year.now().getValue()){
            return Year.now().getValue() - startYear;
        }
        if(hasGraduated()){
            return graduationYear - startYear;
        }
        else
            return 0;
    }

    private double GPA(){
        double sum;
        double count;
        List<Double> gpaBach = this.degrees.get(0).getGPA(ConstantValues.ALL);
        List<Double> gpaMas = this.degrees.get(1).getGPA(ConstantValues.ALL);
        sum = gpaBach.get(0) + gpaMas.get(0);
        count = gpaBach.get(1) + gpaMas.get(1);
        return sum/count;

    }

    //eita baki
    @Override
    public String toString(){
        int id = getId();
        String dateofBirth = getBirthDate();
        String firstName = getFirstName();
        String lastName = getLastName();
        String status = hasGraduated() ? "The student has graduated" : "The student has not graduated, yet";
        int startYear = getStartYear();
        int studyYears = getStudyYears();
        double totalCredits = degrees.get(0).getCredits() + degrees.get(1).getCredits() + degrees.get(2).getCredits();
        double totalGpa = GPA();
        double bachCredits = degrees.get(0).getCredits();
        double bachGpa = degrees.get(0).getGPA(ConstantValues.ALL).get(2);
        String bachTitle = degrees.get(0).getTitleOfThesis();
        double masCredits = degrees.get(1).getCredits();
        double masGpa = degrees.get(1).getGPA(ConstantValues.ALL).get(2);
        String masTitle = degrees.get(1).getTitleOfThesis();
        double remainingBachelorCredits = ConstantValues.BACHELOR_CREDITS - bachCredits;
        double remainingMasterCredits = ConstantValues.MASTER_CREDITS - masCredits;
        double mandatoryBachelorCredits = degrees.get(0).getCreditsByType(ConstantValues.BACHELOR_TYPE);
        double mandatoryMasterCredits = degrees.get(1).getCreditsByType(ConstantValues.MASTER_TYPE);
        String bachelors = remainingBachelorCredits <= 0 ? String.format("Total Bacherlor Credits completed (%.1f/180.0)", bachCredits)
                : String.format("Missing bachelor credits %.1f (%.1f/180.0)", remainingBachelorCredits, bachCredits);
        String masters = remainingMasterCredits <= 0 ? String.format("Total master's credits completed(%.1f/120.0)", masCredits)
                : String.format("Missing master's credits %.1f (%.1f/180.0)", remainingMasterCredits, masCredits);
        String mandatoryBach = mandatoryBachelorCredits >= ConstantValues.BACHELOR_MANDATORY ? String.format("All mandatory bachelor credits completed (%.1f/150.0)", mandatoryBachelorCredits)
        : String.format("Missing mandatory bachelor credits %.1f (%.1f/150.0)", mandatoryBachelorCredits, bachCredits);
        String mandatoryMas = mandatoryMasterCredits >= ConstantValues.MASTER_MANDATORY ? String.format("All mandatory master credits completed (%.1f/50.0)", mandatoryMasterCredits)
        : String.format("Missing mandatory master credits %.1f (%.1f/50.0)", mandatoryMasterCredits, masCredits);   

        return String.format("""
        %s
        First name: %s, Last name: %s
        Date of birth: "%s"
        Status: %s
        Start year: %d (studies have lasted for %d years)
        Total credits: %.2f (GPA = %.2f)
        Bachelor credits: %.2f
            %s
            %s
            GPA of Bachelor studies: %.2f
            Title of BSc Thesis: "%s"
        Master credits: %.2f
            %s
            %s
            GPA of Master studies: %.2f
            Title of MSc Thesis: "%s"
        """, getIdString(), firstName, lastName, dateofBirth,
        status, startYear, studyYears,
        totalCredits, totalGpa,
        bachCredits, bachelors, mandatoryBach,
        bachGpa, bachTitle,
        masCredits, masters, mandatoryMas,
        masGpa, masTitle);
    }

    @Override
    public String getIdString(){
        return String.format("""
                Student id: %d
                """, id);
    }
}
