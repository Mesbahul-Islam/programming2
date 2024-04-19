package dev.m3s.programming2.homework3;

abstract class Person {
    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private String birthDate = ConstantValues.NOT_AVAILABLE;

    public Person(String lname, String fname) {
        if (lname != null) {
            lastName = lname;
        }
        if (fname != null) {
            firstName = fname;
        }
    }

    public String getFirstName() {
        if (firstName.isEmpty()) {
            return ConstantValues.NO_NAME;
        } else
            return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null)
            this.firstName = firstName;
    }

    public String getLastName() {
        if (lastName.isEmpty()) {
            return ConstantValues.NO_NAME;
        } else
            return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null)
            this.lastName = lastName;
    

    }

    public String getBirthDate() {
        return birthDate;
    }

    public String setBirthDate(String personId) {
        PersonID person = new PersonID();
        if (personId != null && person.setPersonId(personId) == "Ok"){
            this.birthDate = person.getBirthDate();
            return this.birthDate;
        }
        else{
            return "No change";
        }
            
    }

    protected int getRandomId(final int min, final int max){
        return (int)((Math.random()*(max-min)+1)+min);
    }
    abstract String getIdString();

}
