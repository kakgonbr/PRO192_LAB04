package controller;

public final class SchoolManagement extends view.Menu<String> {
    private model.School school = new model.School();
    private static String inp;
    private view.Menu<String> subMenu = new view.Menu<String>(new String[]{"Search by date of birth", "Search by average grade"}, "Choose search method")
    {
        @Override
        protected boolean execute(int choice) {
            switch (choice) {
            case 1:
                for (final model.Student student: school.searchStudent(misc.Utils.getLine("Enter date of birth: ")))
                    System.out.println(student);
            break;
            case 2:
                double boundUpper;
                double boundLower;
                if ((inp = misc.Utils.getLine("Enter lower bound: ", misc.Utils.validations.vDouble)).isBlank() || (boundLower = Double.parseDouble(inp)) < 0){
                    System.out.println("Invalid lower bound, canceling.");
                    return false;
                }
                if ((inp = misc.Utils.getLine("Enter uppper bound: ", misc.Utils.validations.vDouble)).isBlank() || (boundUpper = Double.parseDouble(inp)) > 10.d){
                    System.out.println("Invalid upper bound, canceling.");
                    return false;
                }
                if (boundUpper < boundLower) {
                    System.out.println("Invalid upper and lower bounds, lower bound cannot be larger than upper.");
                    return false;
                }
                for (final model.Student student: school.searchStudent(boundLower, boundUpper))
                    System.out.println(student);
            break;
            default:
                System.out.println("Invalid input, canceling.");
            }
            return false;
        }};

    public SchoolManagement() {
        super(new String[]{"Display student list",
                            "Search for student",
                            "Add a student",
                            "Sort students by average grade",
                            "Get and save average of students",
                            "Get statistics or a list of students born before 2000"}, "School management");
    }

    @Override
    protected boolean execute(int choice) {
        switch (choice) {
            case 1:
                for (final model.Student entry : school.getStudentList())
                    System.out.println(entry);
            break;
            case 2:
                subMenu.display();
            break;
            case 3:
                model.Student student = new model.Student();
                updateStudentInfo(student);
                school.addStudent(student);
            break;
            case 4:
                for (final model.Student entry : school.getSortedStudents())
                    System.out.println(entry);
            break;
            case 5:
                System.out.println(String.format("School average: %.2f", school.getSchoolAverage()));
            break;
            case 6:
                for (final model.Student pre2000Student : school.getPre2000Stats())
                    System.out.println(pre2000Student);
            break;
            case 7:
                System.out.println("Exiting.");
                return false;        
            default:
                System.out.println("INVALID INPUT.");
        }
        return true;
    }

    private static void updateStudentInfo(model.Student student){
        while ((inp = misc.Utils.getLine("Enter ID: ", misc.Utils.validations.vInt)).isBlank() || !student.setID(Integer.parseInt(inp)))
            System.out.println("ID is taken or is invalid.");
        while ((inp = misc.Utils.getLine("Enter name: ")).isBlank() || !student.setName(inp))
            System.out.println("Name cannot be blank or be comprised entirely of special symbols");
        while ((inp = misc.Utils.getLine("Enter date of birth: ")).isBlank() || !student.setDateOfBirth(inp))
            System.out.println("Invalid date of birth.");
        while ((inp = misc.Utils.getLine("Enter Java grade: ", misc.Utils.validations.vDouble)).isBlank() || !student.setGradeJava(Double.parseDouble(inp)))
            System.out.println("Grade must be in the range from 0 to 10.");
        while ((inp = misc.Utils.getLine("Enter HTML grade: ", misc.Utils.validations.vDouble)).isBlank() || !student.setGradeHTML(Double.parseDouble(inp)))
            System.out.println("Grade must be in the range from 0 to 10.");
    }
}

// switch (subMenu.getChoice()){
//     case 1:
//         for (final model.Student student: school.searchStudent(misc.Utils.getLine("Enter date of birth: ")))
//             System.out.println(student);
//     break;
//     case 2:
//         double boundUpper;
//         double boundLower;
//         while ((inp = misc.Utils.getLine("Enter lower bound: ", misc.Utils.validations.vDouble)).isBlank() || (boundLower = Double.parseDouble(inp)) < 0);
//         while ((inp = misc.Utils.getLine("Enter uppper bound: ", misc.Utils.validations.vDouble)).isBlank() || (boundUpper = Double.parseDouble(inp)) > 10.d);
//         if (boundUpper < boundLower) {
//             System.out.println("Invalid upper and lower bounds, lower bound cannot be larger than upper.");
//             break;
//         }
//         for (final model.Student student: school.searchStudent(boundLower, boundUpper))
//             System.out.println(student);
//     break;
// }