package dev.m3s.programming2.homework3;

import java.time.Year;

public abstract class Employee extends Person implements Payment{
    private String empId;
    private int startYear = Year.now().getValue();
    private Payment payment;

    public Employee(String lname, String fname){
        super(lname, fname);
        this.empId = getEmployeeIdString() + String.valueOf(super.getRandomId(2001,3000));
        startYear = Year.now().getValue();
    }
    @Override
    public String getIdString(){
        return empId;
    }
    public int getStartYear(){
        return startYear;
    }
    public void setStartYear(final int startYear){
        if(startYear > 2000 && startYear <= Year.now().getValue()){
            this.startYear = startYear;
        }
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        if (payment != null)
            this.payment = payment;
    }
    //do this after implementing payment
    public double calculatePayment(){
        if (this.payment != null && (payment instanceof MonthlyPayment || payment instanceof HourBasedPayment)){
            return payment.calculatePayment();
        }
        else
            return 0.0;
    }
    protected abstract String getEmployeeIdString();
}
