package model;

import java.util.ArrayList;

public final class Student {
    private int ID;
    private String name;
    private long dateOfBirth;
    private double gradeJava;
    private double gradeHTML;
    private double gradeAverage;
    
    private static ArrayList<Integer> occupiedID = new ArrayList<>();
    private static int studentCount = 0;

    public Student(){
        int temp = 0;
        while (!setID(temp++));
        setName("Student No " + ++studentCount);
        setDateOfBirth("1/1/2000");
        setGradeHTML(5.d);
        setGradeJava(5.d);
    }

    public Student(int _ID, String _name, String _dob, double _gradeJava, double _gradeHTML){
        while (!setID(_ID++));
        if (!setName(_name)) throw new IllegalArgumentException("Invalid name.");
        if (!setDateOfBirth(_dob)) throw new IllegalArgumentException("Invalid date of birth.");
        if (!setGradeJava(_gradeJava)) throw new IllegalArgumentException("Invalid grade (Java).");
        if (!setGradeHTML(_gradeHTML)) throw new IllegalArgumentException("Invalid grade (HTML)");
        studentCount++;
    }

    public int getID() {
        return ID;
    }

    public String getDateOfBirth() {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy"); 

        return format.format(new java.util.Date(dateOfBirth));
    }

    public long getLongDateOfBirth(){
        return dateOfBirth;
    }

    public double getGradeAverage() {
        return (gradeAverage = (gradeJava + gradeHTML) / 2);
    }
    
    public double getGradeHTML() {
        return gradeHTML;
    }
    
    public double getGradeJava() {
        return gradeJava;
    }

    public String getName() {
        return name;
    }

    public boolean setDateOfBirth(String _dob) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        // sdf.setTimeZone(java.util.TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        java.util.Date dob;
        try {
             dob = sdf.parse(_dob);
        } catch (java.text.ParseException exc) {
            return false;
        }
        dateOfBirth = dob.getTime();
        
        long currentTime = System.currentTimeMillis();
        int yearDiff;
        return (yearDiff = (int) ((currentTime - dateOfBirth) / (1000 * 60 * 60 * 24 * 365.25))) > 4 && yearDiff < 122;
    }

    // public boolean setGradeAverage(int _gradeAverage) {
    //     return (gradeAverage = _gradeAverage) >= 0 && gradeAverage <= 100;
    // }

    public boolean setGradeHTML(double _gradeHTML) {
        return (gradeHTML = _gradeHTML) >= 0 && gradeHTML <= 10.d;
    }

    public boolean setGradeJava(double _gradeJava) {
        return (gradeJava = _gradeJava) >= 0 && gradeJava <= 10.d;
    }

    public boolean setID(int _ID) {
        if (_ID == ID) return true; // alr set
        if (occupiedID.contains(_ID)) return false;

        occupiedID.remove(Integer.valueOf(ID));
        ID = _ID;
        occupiedID.add(_ID);

        return true;
    }

    public boolean setName(String _name) {
        return !(name = misc.Utils.normalizeName(_name)).isBlank();
    }
    

    @Override
    public String toString(){
        return "\n"
        + "-".repeat(50)
        + "\nStudent ID: " + getID()
        + "\nName: " + getName()
        + "\nDate of birth: " + getDateOfBirth()
        + "\nGrades:"
        + String.format("\n\tJava: %.2f", getGradeJava())
        + String.format("\n\tHTML: %.2f", getGradeHTML())
        + String.format("\n\tAverage: %.2f", getGradeAverage())
        // + "\n\tJava: " + getGradeJava()
        // + "\n\tHTML: " + getGradeHTML()
        // + "\n\tAverage: " + getGradeAverage()
        ;
    }
}
