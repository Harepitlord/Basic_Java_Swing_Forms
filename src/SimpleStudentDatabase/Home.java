package SimpleStudentDatabase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Home {

    Student student;
    JFrame frame;
    JButton login,signup,update,delete,search,logOut;
    JPanel panel,spanel;
    JLabel intro,welcome;
    Database dbase;

    // Constructors
    public Home(Database d) {
        this.student = null;
        this.dbase = d;
        this.prepareInterface();
    }

    public Home(Student s,Database d) {
        this.student = s;
        this.dbase = d;
        this.prepareInterface();
    }

    private void prepareInterface() {

        this.prepareButtons();

        this.prepareLabels();

        this.prepareActionListeners();

        this.preparePanels();

        this.prepareFrames();

        this.addElements();
    }

    // This function initializes the buttons with text and additional modification
    private void prepareButtons() {
        this.login  = new JButton("Login");
        this.signup = new JButton("SignUp");
        this.update = new JButton("Update");
        this.delete = new JButton("Delete");
        this.search = new JButton("search");

        if(this.student != null) {
            this.logOut = new JButton("Log Out");
            this.logOut.setBackground(Color.blue);
            this.logOut.setForeground(Color.white);
        }

        this.login.setForeground(Color.white);
        this.signup.setForeground(Color.white);
        this.update.setForeground(Color.white);
        this.delete.setForeground(Color.white);
        this.search.setForeground(Color.white);

        this.login.setBackground(Color.blue);
        this.signup.setBackground(Color.blue);
        this.update.setBackground(Color.BLUE);
        this.delete.setBackground(Color.blue);
        this.search.setBackground(Color.blue);

//        this.signup.addActionListener(this);
//        this.update.addActionListener(this);
//        this.delete.addActionListener(this);
//        this.search.addActionListener(this);
    }

    private void prepareActionListeners() {
        this.login.addActionListener(e->{
            new LoginForm(this.dbase);
            this.frame.dispose();  });

        if(this.student != null) {
            this.logOut.addActionListener(e -> this.signOut());
        }

        this.signup.addActionListener(e -> {
            new RegistrationForm(this.dbase);
            this.frame.dispose();   });

        this.update.addActionListener(e -> {
            new UpdateForm(this.student,this.dbase);
            this.frame.dispose();
        });

        this.delete.addActionListener(e -> {
            new DeleteForm(this.student,this.dbase);
            this.frame.dispose();
        });

//        this.logOut.addActionListener(e-> new Home(this.dbase));
    }

    // This function initializes the labels with text and additional modification
    private void prepareLabels() {
        this.intro = new JLabel("Welcome to Student Database.");
        if (this.student !=null)
            this.welcome = new JLabel("Hi "+this.student.getName());
    }

    // This function initializes the panels with required configurations.
    private void preparePanels() {
        this.spanel = new JPanel();
        this.panel = new JPanel();

        this.spanel.setBackground(Color.white);
        this.spanel.setLayout(null);
        this.spanel.setBorder(new LineBorder(Color.BLACK,2));
        this.spanel.setBounds(50,50,400,500);

        this.panel.setBackground(Color.white);
        this.panel.setLayout(new GridLayout(8,1,20,20));
        this.panel.setBounds(50,50,300,350);
        this.panel.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
    }

    private void prepareFrames() {
        this.frame = new JFrame("Student Database");
        this.frame.getContentPane().setBackground(Color.blue);
        this.frame.setLayout(null);
        this.frame.setSize(600,700);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.setResizable(true);
    }

    // This function adds the elements to their respective containers
    private void addElements() {

        if(this.student != null)
            this.panel.add(this.welcome);
        this.panel.add(this.intro);

        if(this.student == null) {
            this.panel.add(this.login);
            this.panel.add(this.signup);
        }

        if(this.student != null) {
            this.panel.add(this.update);
            this.panel.add(this.delete);
            this.panel.add(this.logOut);
        }

        this.spanel.add(this.panel);

        this.frame.add(this.spanel);
    }


//        }
//        else if(this.logOut.isSelected()) {
//            this.signOut();
//        }

    private void signOut() {
        this.frame.dispose();
        new Home(this.dbase);
    }

}
