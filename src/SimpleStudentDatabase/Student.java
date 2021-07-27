package SimpleStudentDatabase;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

// Student class is to create a template object which will be useful for handling data in forms and database
public class Student {

        // Data members
        private String RegNo;
        private String Name;
        private String Dept;
        private int YrOfStudy;
        private Email email;
        private Date dob;

        // Constructor
        public Student(String r,String n,String d,int y,String e,Date date) {
                this.RegNo = r;
                this.Name = n;
                this.Dept = d;
                this.YrOfStudy = y;
                this.dob = date;
                try {
                        this.email = new Email(e);
                }
                catch (Exception ex) {
                        System.out.println("Creating Student : "+ ex);
                }
        }

        public Student(ResultSet set) {
                try {
                        this.RegNo = set.getString("regno");
                        this.Name = set.getString("name");
                        this.Dept = set.getString("dept");
                        this.YrOfStudy = set.getInt("YearOfStudy");
                        this.email = new Email(set.getString("email"));
                        this.dob = new Date(set.getString("Dob"));
                }
                catch(Exception e) {
                        System.out.println("Student Constructor : "+e);
                }

        }

        // Getter Functions
        public String getRegNo() {
                return this.RegNo;
        }

        public String getName() {
                return this.Name;
        }

        public String getDept() {
                return this.Dept;
        }

        public int getYrOfStudy() {
                return this.YrOfStudy;
        }

        public String getEmail() {
                return this.email.getEmail();
        }

        public String getDOB() {
                return this.dob.toString();
        }

        // Setter Functions
        public void setRegNo(String regNo) {
                this.RegNo = regNo;
        }

        public void setName(String name) {
                this.Name = name;
        }

        public void setDept(String dept) {
                this.Dept = dept;
        }

        public void setYrOfStudy(int yrOfStudy) {
                this.YrOfStudy = yrOfStudy;
        }

        public void setEmail(Email email) {
                this.email = email;
        }

        public void setDOB(String s) {
                try {
                        this.dob = new SimpleDateFormat("dd/MM/yyyy").parse(s);
                }
                catch(Exception e) {
                        System.out.println("setDOB : "+e);
                }
        }

        // utility Functions

        public String getInsertQuery() {
                return String.format("insert into Student(RegNo,StudentName,Department,YearOfStudy,Email,Dob) " +
                        "values( %s,%s,%s,%s,%s,%s);",this.RegNo,this.Name,this.Dept, this.YrOfStudy,this.getEmail(),this.dob);
        }

        public String getUpdateQuery() {
                return String.format("update Student set StudentName = %s,Department = %s ,YearOfStudy = %s,Email = %s"+
                        "DOb = %s where RegNo = %s ;",this.Name,this.Dept, this.YrOfStudy,this.getEmail(),this.RegNo,this.dob);
        }

        public String getDeleteQuery() {
                return String.format("delete from Student where RegNo = %s;",this.RegNo);
        }
}
