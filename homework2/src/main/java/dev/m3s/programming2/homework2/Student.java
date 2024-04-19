package dev.m3s.programming2.homework2;
import java.time.Year;
import java.util.Objects;

public class Student extends PersonID{
    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private int id;
    private int startYear = Year.now().getValue();
    private int graduationYear;
    private int degreeCount = 3;
    private Degree[] degrees;
    private String birthDate = ConstantValues.NO_BIRTHDATE;
    public Student() {
        this.id = getRandomId();
        startYear = Year.now().getValue();
        degrees = new Degree[degreeCount];
        for (int i = 0; i < degreeCount; i++) {
            degrees[i] = new Degree(); 
        }
    }
    public Student(String lname, String fname) {
        this();
        if (lname != null) {
            lastName = lname;
        }
        if (fname != null) {
            firstName = fname;
        }

    }
    public String getFirstName() {
        if (firstName.isEmpty()){
            return ConstantValues.NO_NAME;
        }
        else
            return firstName;
    }
    public void setFirstName(String firstName){
        if (firstName != null)
            this.firstName = firstName;
    }
    public String getLastName() {
        if (lastName.isEmpty()){
            return ConstantValues.NO_NAME;
        }
        else
            return lastName;
    }
    public void setLastName(String lastName){
        if(lastName != null)
            this.lastName = lastName;

    }
    public int getId(){
        return id;
    }
    public void setId(final int id){
        if (id >= ConstantValues.MIN_ID && id <= ConstantValues.MAX_ID){
            this.id = id;
        }
        else{
            System.out.println("Use number between 1 and 100");
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
        else if(graduationYear <= startYear || graduationYear > Year.now().getValue()){
            return "Check graduation year";
        }
        this.graduationYear = graduationYear;
        return "OK";
    }

    public void setDegreeTitle(final int i, String dName){
        if(i >=0 && i < degreeCount && dName != null){
            degrees[i].setDegreeTitle(dName);
        }
    }
    public boolean addCourse(final int i, StudentCourse course){
        if(i >= 0 && i < degreeCount && course != null && degrees[i] != null){
            return degrees[i].addStudentCourse(course);
        }
        return false;
    }

    public int addCourses(final int i, StudentCourse[] courses){
        int count = 0;
        if(courses != null){
            for(StudentCourse course: courses){
                if(i >= 0 && i < degreeCount && course != null){
                    addCourse(i,course);
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
        if(i >=0 && i < degreeCount && title != null)
            degrees[i].setTitleOfThesis(title);
    }
    public String getBirthDate(){
        return birthDate;
    }
    public String setBirthDate(String personID){
        if (personID != null && Objects.equals(setPersonId(personID), "Ok")){
            this.birthDate = super.getBirthDate();
            return birthDate;
        }
        return "No change";
    }
    public boolean hasGraduated(){
        return graduationYear != 0;
    }
    private boolean canGraduate(){
        double bachCredits = 0;
        double masCredits = 0;
        String bachelorTitle = ConstantValues.NO_TITLE;
        String masTitle = ConstantValues.NO_TITLE;
        bachCredits = degrees[0].getCredits();
        bachelorTitle = degrees[0].getDegreeTitle();
        masCredits = degrees[1].getCredits();
        masTitle = degrees[1].getDegreeTitle();


        return (bachCredits >= ConstantValues.BACHELOR_CREDITS && masCredits >= ConstantValues.MASTER_CREDITS
                && !bachelorTitle.equals(ConstantValues.NO_TITLE) && !masTitle.equals(ConstantValues.NO_TITLE));
    }
    public int getStudyYears(){
        if(hasGraduated()){
            return this.graduationYear - startYear;
        }
        else
            return Year.now().getValue() - startYear;
    }
    private int getRandomId(){
        return (int)((Math.random()*ConstantValues.MAX_ID)+ConstantValues.MIN_ID);
    }

    @Override
    public String toString() {
        String status = hasGraduated() ? String.format("The student has graduated in %d", getGraduationYear()) : "The student has not graduated, yet.";
        int studyYears = getStudyYears();
        double bachCredits = degrees[0].getCredits();
        double masCredits = degrees[1].getCredits();
        double doctoral = degrees[2].getCredits();
        double totalCredits = bachCredits + masCredits;
        double remainingBachelorCredits = 180 - bachCredits;
        double remainingMasterCredits = 120 - masCredits;
        String bachelorTitle = degrees[0].getDegreeTitle();
        String masTitle = degrees[1].getDegreeTitle();
        String doctoralTitle = degrees[2].getDegreeTitle();
        String bachelors = remainingBachelorCredits <= 0 ? String.format("Total Bacherlor Credits completed (%.1f/180.0)", bachCredits)
                : String.format("Missing bachelor credits %.1f (%.1f/180.0)", remainingBachelorCredits, bachCredits);
        String masters = remainingMasterCredits <= 0 ? String.format("Total master's credits completed(%.1f/120.0)", masCredits)
                : String.format("Missing master's credits %.1f (%.1f/180.0)", remainingMasterCredits, masCredits);


        return String.format(
                """
                    Student id: %d
                        FirstName: %s, LastName: %s
                        Date of Birth: %s
                        Status: %s
                        StartYear: %d (studies have lasted for %d years)
                        Total credits = %.1f
                        Bachelor Credits: %.1f
                            %s
                            Title of BSc thesis: "%s"
                        Master Credits = %.1f
                            %s
                            Title of MSc Thesis: "%s"
                    
                    """,
                getId(), getFirstName(), getLastName(), birthDate, status, getStartYear(), studyYears, totalCredits, bachCredits, bachelors, bachelorTitle,
                masCredits, masters, masTitle);
    }
}