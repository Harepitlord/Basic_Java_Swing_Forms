package SimpleStudentDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

// This class is used as the database interface by the UI class
public class Database {

    // Data Members
    private Connection conn;

    // Constructor
    public Database() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:E:\\Assignment\\BootCamp\\src\\FORMS\\students.db");
            conn.setAutoCommit(true);
            this.createTable();
        }
        catch (Exception e) {
            System.out.println("Exception : "+e);
        }
    }

    // This function checks the presence of required tables before proceeding to data storing and retrieval if not creates it
    public void createTable() {
        try {
            Statement st = conn.createStatement();
            String query = String.format("SELECT name FROM sqlite_master WHERE type='table' AND name='%s'","Student");
            ResultSet set = st.executeQuery(query);
            if (!set.next()) {
                query = "CREATE TABLE Student (" +
                        "StudentId integer not null primary key autoincrement ," +
                        "RegNo VARCHAR(20) UNIQUE," +
                        "StudentName VARCHAR(30) NOT NULL," +
                        "Department VARCHAR(30) NOT NULL," +
                        "YearOfStudy INT NOT NULL," +
                        "Email VARCHAR(30)  NOT NULL," +
                        "Dob DATE NOT NULL );";
                st.executeUpdate(query);
            }
            query = String.format("SELECT name FROM sqlite_master WHERE type='table' AND name='%s'","Login");
            set = st.executeQuery(query);
            if(!set.next()) {
                query = "create table Login (" +
                        "loginId integer primary key autoincrement ," +
                        "RegNo varchar(20) unique," +
                        "LoginPassword varchar(20) not null );";
                st.executeUpdate(query);
            }
        }
        catch(Exception e) {
            System.out.println("Create Tables : "+e);
        }
    }

    // This function is to add a given student into database
    public boolean insertStudent(Student s) {
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(s.getInsertQuery());
        }
        catch (Exception e) {
            System.out.println("Insert : "+e);
            return false;
        }
        return true;
    }

    // This function is to update the student record in the database
    public boolean updateStudent(Student s) {
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(s.getUpdateQuery());
        }
        catch(Exception e) {
            System.out.println("Update: "+e);
            return false;
        }
        return true;
    }

    // This function is to delete the student record in the database
    public boolean deleteStudent(Student s) {
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(s.getDeleteQuery());
        }
        catch(Exception e) {
            System.out.println("Delete Student : " +e);
            return false;
        }
        return true;
    }

    // This function is to get the specific student record from the database
    public Student getStudent(String Reg) {
        try {
            Statement st = this.conn.createStatement();
            ResultSet set = st.executeQuery(String.format("select RegNo,StudentName,Department,YearOfStudy,Dob from Student " +
                    "where RegNo = '%s';",Reg));
            if(set.next()) {
                return new Student(set);
            }
        }
        catch (Exception e) {
            System.out.println("Get Student : "+e);
        }
        return null;
    }

//    This function needs work to be done
//    public ArrayList<Student> searchStudents(String reg, String name,String dept,int yr,String email) {
//        ArrayList<Student> data = new ArrayList<>();
//        String query = "select RegNo,StudentName,Department,YearOfStudy from Student where ";
//        if (reg != null)
//            query += "regno='"+reg+"'";
//
//        try {
//            Statement st = this.conn.createStatement();
//
//        }
//        catch(Exception e) {
//            System.out.println("Search : "+e);
//            return null;
//        }
//        return data;
//    }

    // This function is to get all student records from the database
    public ArrayList<Student> allStudents() {
        ArrayList<Student> data = new ArrayList<>();
        try {
            String query = "select RegNo, StudentName,Department,YearOfStudy from Student;";
            Statement st = this.conn.createStatement();
            ResultSet set = st.executeQuery(query);
            while(set.next()) {
                data.add(new Student(set));
            }
            if (data.size()==0)
                return null;
        }
        catch(Exception e) {
            System.out.println("Query all : "+e);
            return null;
        }
        return data;
    }

    // This function is to check whether given username and password is valid or not if valid returns the student's record
    public Student checkLogin(String reg,String password) {
        try {
            Statement st = this.conn.createStatement();
            String query = String.format("select RegNo,LoginPassword from Login where RegNo = '%s'",reg);
            ResultSet set = st.executeQuery(query);
            if (set.next())
                if(password.equals(set.getString("LoginPassword")))
                    return this.getStudent(reg);
        }
        catch(Exception e) {
            System.out.println("Check Login: "+e);
            return null;
        }
        return null;
    }
}
