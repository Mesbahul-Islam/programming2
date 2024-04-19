package dev.m3s.programming2.homework3;
public class PersonID {
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    public String getBirthDate(){
        return birthDate;
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
