package dev.m3s.programming2.homework1;
import java.util.Scanner;

public class Student {
    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private int id;
    private double bachelorCredits;
    private double masterCredits;
    private String titleOfMasterThesis = ConstantValues.NO_TITLE;
    private String titleOfBachelorThesis = ConstantValues.NO_TITLE;
    private int startYear = ConstantValues.CURRENT_YEAR;
    private int graduationYear;
    private String birthDate = ConstantValues.NO_BIRTHDATE;
    private final Scanner input = new Scanner(System.in);

    Student(){
        id = getRandomId();
    }
    Student(String lname, String fname){
        if(lname != null){
            lastName = lname;
        }
        if (fname != null){
            firstName = fname;
        }
        id = getRandomId();
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

    public double getBachelorCredits() {
        return bachelorCredits;
    }


    public void setBachelorCredits(final double bachelorCredits){
        if(bachelorCredits >= ConstantValues.MIN_CREDITS && bachelorCredits <= ConstantValues.MAX_CREDITS){
            this.bachelorCredits = bachelorCredits;
        }
        else{
            System.out.println("Use number between 0 and 300");
        }
    }


    public double getMasterCredits() {
        return masterCredits;
    }


    public void setMasterCredits(final double masterCredits){
        if(masterCredits >= ConstantValues.MIN_CREDITS && masterCredits <= ConstantValues.MAX_CREDITS){
            this.masterCredits = masterCredits;
        }
        else{
            System.out.println("Use number between 0 and 300");
        }
    }


    public String getTitleOfMasterThesis(){
        return titleOfMasterThesis;
    }


    public void setTitleOfMasterThesis(String title){
        if(title != null){
            this.titleOfMasterThesis = title;
        }
    }


    public String getTitleOfBachelorThesis(){
        return titleOfBachelorThesis;
    }


    public void setTitleOfBachelorThesis(String title){
        if(title != null){
            this.titleOfBachelorThesis = title;
        }
    }


    public int getStartYear(){
        return startYear;
    }


    public void setStartYear(final int startYear){
        if(startYear > 2000 && startYear <= ConstantValues.CURRENT_YEAR){
            this.startYear = startYear;
        }
    }

    
    public int getGraduationYear(){
        return graduationYear;
    }


    public String setGraduationYear(final int graduationYear){
        if(!canGraduate()){
            return "Check the required studies";
        }
        else if(graduationYear < startYear || graduationYear > ConstantValues.CURRENT_YEAR){
            return "Check graduation year";
        }
        this.graduationYear = graduationYear;
        return "OK";
    }


    public boolean hasGraduated(){
        return graduationYear != 0;
    }


    private boolean canGraduate(){
        double bachCredits = getBachelorCredits();
        double masCredits = getMasterCredits();

        return (bachCredits >= ConstantValues.BACHELOR_CREDITS && masCredits >= ConstantValues.MASTER_CREDITS
                && !titleOfBachelorThesis.equals(ConstantValues.NO_TITLE) && !titleOfMasterThesis.equals(ConstantValues.NO_TITLE));
    }


    public int getStudyYears(){
        if(hasGraduated()){
            return this.graduationYear - startYear;
        }
        else
            return ConstantValues.CURRENT_YEAR - startYear;
    }


    private int getRandomId(){
        return (int)((Math.random()*ConstantValues.MAX_ID)+ConstantValues.MIN_ID);
    }


    public String toString() {
        String status = hasGraduated() ? String.format("The student has graduated in %d", getGraduationYear()) : "The student has not graduated, yet.";
        int studyYears = getStudyYears();
        double remainingBachelorCredits = 180 - getBachelorCredits();
        double remainingMasterCredits = 120 - getMasterCredits();
        String bachelors = remainingBachelorCredits <= 0 ? String.format("All required bachelor credits completed (%.1f/180.0)", getBachelorCredits())
                : String.format("Missing bachelor credits %.1f (%.1f/180.0)", remainingBachelorCredits, getBachelorCredits());
        String masters = remainingMasterCredits <= 0 ? String.format("All required master's credits completed (%.1f/120.0)", getMasterCredits())
                : String.format("Missing master's credits %.1f (%.1f/120.0)", remainingMasterCredits, getMasterCredits());


        return String.format(
                """
                    Student id: %d
                    FirstName: %s, LastName: %s
                    Date of Birth: %s
                    Status: %s
                    StartYear: %d (studies have lasted for %d years)
                    BachelorCredits: %.1f ==> %s
                    TitleOfBachelorThesis: %s
                    MasterCredits: %.1f ==> %s
                    TitleOfMastersThesis: %s
                    """,
                getId(), getFirstName(), getLastName(), birthDate, status, getStartYear(), studyYears, getBachelorCredits(), bachelors, getTitleOfBachelorThesis(),
                getMasterCredits(), masters, getTitleOfMasterThesis());
    }


    public String setPersonId(final String personID){
        if (checkPersonIDNumber(personID)){
            char sym = personID.charAt(6);
            String century = "";
            switch (sym){
                case '+': century = "18"; break;
                case '-': century = "19"; break;
                case 'A': century = "20"; break;
            }
            String birthDateLocal = String.format("%s.%s.%s", personID.substring(0,2),
                    personID.substring(2,4), century + personID.substring(4,6));
            if(checkBirthDate(birthDateLocal)){
                if(checkValidCharacter(personID)){
                    this.birthDate = birthDateLocal;
                    return "Ok";
                }
                else
                    return "Incorrect Checkmark";
            }
            else
                return "Invalid Birthday";
        }
        else
            return "Invalid Birthday";
    }


    private boolean checkPersonIDNumber(final String personID){
        boolean flag = false;
        if(personID != null && personID.length() == 11){
            char[] symbol = {'+', '-', 'A'};
            for (char c : symbol) {
                if (personID.charAt(6) == c) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }


    private boolean checkLeapYear(int year){
        return ((year % 400 == 0) || (year % 100 != 0) && (year % 4 == 0));
    }


    private boolean checkValidCharacter(final String personID){
        String calculationNumber = personID.substring(0,6) + personID.substring(7,10);
        int calculation = Integer.parseInt(calculationNumber) % 31;
        char[] controlCharacters = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'H', 'J', 'K', 'L',
                'M', 'N', 'P', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y'
        };
        return personID.charAt(10) == controlCharacters[calculation];
    }

    
    private boolean checkBirthDate(final String date) {
        String[] dateParts = date.split("\\.");
        if (dateParts.length != 3) {
            return false;
        }

        int day, month, year;
        day = Integer.parseInt(dateParts[0]);
        month = Integer.parseInt(dateParts[1]);
        year = Integer.parseInt(dateParts[2]);

        if ((year <= 0) || (month < 1 || month > 12) ) {
            return false;
        }

        int daysInMonth;
        switch (month) {
            case 4: case 6: case 9: case 11:
                daysInMonth = 30;
                break;
            case 2:
                daysInMonth = checkLeapYear(year) ? 29 : 28;
                break;
            default:
                daysInMonth = 31;
                break;
        }

        return day >= 1 && day <= daysInMonth;
    }

}

