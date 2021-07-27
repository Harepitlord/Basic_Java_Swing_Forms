package SimpleStudentDatabase;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Date;
import java.util.Properties;


// This class implements the User Interface for the student registration
public class RegistrationForm {

    // Data members
    private final Database dbase;
    private JFrame frame;
    private JPanel spanel,panel;
    private JLabel regNo,name,dept,yrOfStudy,email,dob;
    private JTextField RegNo,Name,Emails;
    private JComboBox<String> YrOfStudy,Dept;
    private JDatePickerImpl datePicker;
    private JButton submit;


    // Constructor
    public RegistrationForm(Database d) {
        this.dbase = d;
        this.prepareInterface();
    }

    // This function is to prepare all UI components
    private void prepareInterface() {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        this.datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());

        this.prepareFrames();

        this.preparePanels();

        this.prepareTextFields();

        this.prepareLabels();

        this.prepareButtons();

        this.prepareActionListeners();

        this.addElements();
    }

    // This function add all UI Components to respective containers
    private void addElements() {
        this.panel.add(this.regNo);
        this.panel.add(this.RegNo);
        this.panel.add(this.name);
        this.panel.add(this.Name);
        this.panel.add(this.dept);
        this.panel.add(this.Dept);
        this.panel.add(this.yrOfStudy);
        this.panel.add(this.YrOfStudy);
        this.panel.add(this.email);
        this.panel.add(this.Emails);
        this.panel.add(this.dob);
        this.panel.add(this.datePicker);
        this.panel.add(this.submit);

        this.spanel.add(this.panel);
        this.frame.add(this.spanel);
    }

    // This function to initialize and customize the buttons
    private void prepareButtons() {
        this.submit = new JButton("Submit");
        this.submit.setForeground(Color.white);
        this.submit.setBackground(Color.blue);
    }

    // This function is to add the action listeners to the respective component
    private void prepareActionListeners() {
        this.submit.addActionListener(e -> this.register());
    }

    // This function is to initialize and customize the frames
    private void prepareFrames() {
        this.frame = new JFrame("Registration Form");
        this.frame.getContentPane().setBackground(Color.blue);
        this.frame.setLayout(null);
        this.frame.setSize(500,700);
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
        this.spanel.setBorder(new LineBorder(Color.BLACK,2));
        this.spanel.setBounds(50,50,500,600);

        this.panel.setBackground(Color.white);
        this.panel.setLayout(new GridLayout(8,1,20,20));
        this.panel.setBounds(100,50,400,500);
        this.panel.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
    }

    // This function is to initialize and customize the labels
    private void prepareLabels() {
        this.regNo = new JLabel("Register No: ");
        this.name = new JLabel("Name: ");
        this.dept = new JLabel("Department: ");
        this.yrOfStudy = new JLabel("Year Of Study: ");
        this.email = new JLabel("Email: ");
        this.dob = new JLabel("Date Of Birth: ");
    }

    // This function is To initialize and customize the text fields
    private void prepareTextFields() {
        String[] s = {"I","II","III","IV"};
        String[] d = {"NONE","CSE","CSBS","EEE","ECE","CIVIL"};
        this.RegNo = new JTextField();
        this.Name = new JTextField();
        this.Dept = new JComboBox<>(d);
        this.Emails = new JTextField();
        this.YrOfStudy = new JComboBox<>(s);

        this.Dept.setSelectedIndex(0);

    }

    // This Function is to validate the input data and stores the correct data
    private void register() {
        String regno = this.RegNo.getText();
        if(regno.length()<2)
            new message("Enter valid register number");

        String sName = this.Name.getText();
        if(sName.length()<2)
            new message("Enter valid Name");

        String sDept = (String)this.Dept.getSelectedItem();
        if(sDept == null)
            new message("Select the department");

        String yr = (String) this.YrOfStudy.getSelectedItem();
        if(yr == null)
            new message("Select the year of study: ");

        Email e;
        try {
            e = new Email(this.Emails.getText());
        }
        catch (Exception ex) {
            new message("Enter valid email");
        }
        Date d = (Date) this.datePicker.getModel().getValue();
        Student s = new Student(regno,sName,sDept,Integer.parseInt(yr),this.Emails.getText(),d);
        if(this.dbase.insertStudent(s)) {
            new message("Registration successful");
            new Home(s, this.dbase);
            this.frame.dispose();
        }
        else
            new message("Registration is unsuccessful. Register number already exists.");

    }
}
