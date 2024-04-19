package dev.m3s.programming2.homework2;
public class Course {
    private String name = ConstantValues.NO_NAME;
    private String courseCode;
    private Character courseBase;
    private int courseType;
    private int period;
    private  double credits;
    private boolean numericGrade;

    public Course(){}
    public Course(String name, final int code, Character courseBase, final int type, final int period, final double credits, boolean numericGrade){
        if (name != null && !name.isEmpty())
            setName(name);
        this.setCourseCode(code, courseBase);
        this.setCourseType(type);
        this.setPeriod(period);
        if(Math.round(credits) >= ConstantValues.MIN_CREDITS && Math.round(credits) <= ConstantValues.MAX_COURSE_CREDITS) {
            setCredits(Math.round(credits));
        }
        this.setNumericGrade(numericGrade);
    }
    public Course(Course course){
    String courseCode = course.getCourseCode();
    String numericPart = "";
    
    for (char c : courseCode.toCharArray()) {
        if (Character.isDigit(c)) {
            numericPart += c;
        } else {
            break;
        }
    }
    int parsedCourseCode = Integer.parseInt(numericPart);
    this.setName(course.getName());
    this.setCourseCode(parsedCourseCode, course.getCourseBase());
    this.setCourseType(course.getCourseType());
    this.setPeriod(course.getPeriod());
    this.setCredits(course.getCredits());
    this.setNumericGrade(course.isNumericGrade());
}
    public String getName(){
        return name;
    }
    public void setName(String name){
        if (name != null && !name.isEmpty()){
            this.name = name;
        }
    }
    public String getCourseTypeString(){
        return courseType == 0 ? "Optional" : "Mandatory";
    }
    public int getCourseType(){
        return courseType;
    }
    public void setCourseType(final int type){
        if (type == 0 || type == 1) {
            this.courseType = type;
        }
    }
    public String getCourseCode(){
        return courseCode;
    }

    public void setCourseCode(final int courseCode, Character courseBase) {
        if (courseBase != null) {
            char[] base = {'A', 'P', 'S'};
            boolean validBase = false;
            for (char c : base) {
                if (c == Character.toUpperCase(courseBase)) {
                    validBase = true;
                    break;
                }
            }
            if (validBase && courseCode > 0 && courseCode < 1000000) {
                this.courseCode = String.valueOf(courseCode) + Character.toUpperCase(courseBase);
                this.courseBase = Character.toUpperCase(courseBase); // Update courseBase
            }
        }
    }


    public Character getCourseBase(){
        return courseBase;
    }
    public int getPeriod(){
        return period;
    }
    public void setPeriod(final int period){
        if(period >= ConstantValues.MIN_PERIOD && period <= ConstantValues.MAX_PERIOD){
            this.period = period;
        }
    }
    public double getCredits(){
        return credits;
    }
    private void setCredits(final double credits){
        if(Math.round(credits) >= ConstantValues.MIN_CREDITS && Math.round(credits) <= ConstantValues.MAX_COURSE_CREDITS){
            this.credits = Math.round(credits);
        }
    }
    public boolean isNumericGrade(){
        return numericGrade;
    }
    public void setNumericGrade(boolean numericGrade){
        this.numericGrade = numericGrade;
    }
    public String toString(){
        return String.format("""
                [%s (%.2f cr), "%s". %s, period: %d.]
                """, getCourseCode(), getCredits(), getName(), getCourseTypeString(), getPeriod());
    }
}
