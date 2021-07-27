package SimpleStudentDatabase;

// This class runs the student database
public class FormsRunner {
    public static void main(String[] args) {
        Database dbase = new Database();
        new Home(dbase);
    }
}
