package SimpleStudentDatabase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

// This class is generate a UI for the login process
public class LoginForm {

    private JFrame frame;
    private JPanel spanel,panel;
    private JLabel userName,Password;
    private JButton login,signup,forgotPassword,home;
    private JTextField email,password;
    private final Database dbase;

    // Constructor
    public LoginForm(Database d) {
        // Data Members
        Student student = null;
        this.dbase = d;
        this.prepareInterface();
    }

    // This function is to prepare the UI and its components
    private void prepareInterface() {
        this.prepareFrames();

        this.prepareButtons();

        this.prepareLabels();

        this.prepareActionListeners();

        this.prepareTextField();

        this.preparePanels();

        this.addElements();
    }

    // This function is to add the components to their respective class
    private void addElements() {
        this.panel.add(this.userName);
        this.panel.add(this.email);
        this.panel.add(this.Password);
        this.panel.add(this.password);
        this.panel.add(this.login);
        this.panel.add(this.signup);
        this.panel.add(this.forgotPassword);
        this.panel.add(this.home);

        this.spanel.add(panel);
        this.frame.add(this.spanel);

    }

    // This function is to add the action listeners to the components
    private void prepareActionListeners() {
        this.login.addActionListener(e -> this.signIn());
        this.home.addActionListener(e -> {
            new Home(this.dbase);
            this.frame.dispose();
        });
        this.signup.addActionListener(e->{
            new RegistrationForm(this.dbase);
            this.frame.dispose();
        });
    }

    // This function is to initialize and customize the text fields
    private void prepareTextField() {
        this.email = new JTextField();
        this.password = new JTextField();
    }

    // This function is to initialize and customize the buttons
    private void prepareButtons() {
        this.login = new JButton("Login");
        this.signup = new JButton("Sign Up");
        this.forgotPassword = new JButton("Forgot Password");
        this.home = new JButton("Home");

        this.login.setBackground(Color.blue);
        this.signup.setBackground(Color.blue);
        this.forgotPassword.setBackground(Color.blue);
        this.home.setBackground(Color.blue);

        this.login.setForeground(Color.white);
        this.signup.setForeground(Color.white);
        this.forgotPassword.setForeground(Color.white);
        this.home.setForeground(Color.white);
    }

    // This function is to initialize and customize the labels
    private void prepareLabels() {
        this.userName = new JLabel("Email/RegNo: ");
        this.Password = new JLabel("Password");
    }

    // This function is to initialize and customize the frames
    private void prepareFrames() {
        this.frame = new JFrame("Login");
        this.frame.getContentPane().setBackground(Color.blue);
        this.frame.setLayout(null);
        this.frame.setSize(720,720);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.setResizable(true);
    }

    // This function is to initialize and customize the panels
    private void preparePanels() {
        this.spanel = new JPanel();
        this.panel = new JPanel();

        this.spanel.setBackground(Color.white);
        this.spanel.setLayout(null);
        this.spanel.setBorder(new LineBorder(Color.blue,2));
        this.spanel.setBounds(50,50,600,600);

        this.panel.setBackground(Color.white);
        this.panel.setLayout(new GridLayout(8,2,20,20));
        this.panel.setBounds(50,50,400,500);
        this.panel.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
    }

    // This function validate the login info then logs in the student
    private void signIn() {
        String uName = this.email.getText();
        String pass = this.password.getText();
        Student st = dbase.checkLogin(uName,pass);
        new Home(st,this.dbase);
        this.frame.dispose();
    }

}
