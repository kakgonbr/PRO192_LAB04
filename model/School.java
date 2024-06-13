package model;

import java.util.ArrayList;

public class School {
    private ArrayList<Student> students = new ArrayList<>();
    private double schoolAverage;

    public School(){
        students.add(new Student(1, "Nguyen Van a", "15/1/2000", 8.5d, 9.d));
        students.add(new Student(2, "Tran Thi B", "23/11/1999", 7.8d, 8.2d));
        students.add(new Student(3, "Le Van C", "10/3/2001", 9.2d, 8.8d));
        students.add(new Student(4, "Pham Thi D", "22/07/1998", 7.4d, 7.7d));
        students.add(new Student(5, "Hoang Van E", "11/05/1996", 7.d, 9.4d));
    }

    public ArrayList<Student> getStudentList(){
        // System.out.println("Student list: " + students.size() + " students");
        // for (final Student student : students){
        //     System.out.println(student);
        // }
        return students;
    }

    public double getSchoolAverage(){
        double average = 0;
        for (final Student student : students)
            average += student.getGradeAverage();
        
        return (schoolAverage = average / students.size());
    }

    public ArrayList<Student> searchStudent(String _dob){
        ArrayList<Student> matches = new ArrayList<>();

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dob;
        try {
             dob = sdf.parse(_dob);
        } catch (java.text.ParseException exc) {
            System.out.println("Invalid date of birth entered.");
            return matches;
        }
        long dateOfBirth = dob.getTime();

        for (final Student student :students){
            if (dateOfBirth == student.getLongDateOfBirth()) matches.add(student);
        }

        return matches;
    }

    public ArrayList<Student> searchStudent(double boundLower, double boundUpper){
        ArrayList<Student> matches = new ArrayList<>();

        // for (const Student& student : students)
        for (final Student student : students){
            if (student.getGradeAverage() >= boundLower && student.getGradeAverage() <= boundUpper) matches.add(student);
        }

        return matches;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public ArrayList<Student> getSortedStudents(){
        ArrayList<Student> sortedStudents = new ArrayList<>(students);
                
        sortedStudents.sort((a, b) -> {return Double.compare(b.getGradeAverage(), a.getGradeAverage());});

       return sortedStudents;
    }

    public ArrayList<Student> getPre2000Stats(){
        ArrayList<Student> result = new ArrayList<>();

        for (final Student student: students){
            java.util.Date date = new java.util.Date(student.getLongDateOfBirth());
            if (date.before(new java.util.Date(946684800000L)))
                result.add(student);
        }
        return result;
    }
}
