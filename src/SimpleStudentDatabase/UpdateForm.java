package SimpleStudentDatabase;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

// This class is to generate update form UI
public class UpdateForm {

    // Data members
    private final Database dbase;
    private final Student student;

    private JFrame frame;
    private JPanel spanel,panel;
    private JLabel regNo,name,dept,yrOfStudy,email,dob;
    private JTextField RegNo,Name,Emails;
    private JComboBox<String> YrOfStudy,Dept;
    private JDatePickerImpl datePicker;
    private JButton submit;

    // Constructor
    public UpdateForm(Student s,Database d) {
        this.dbase = d;
        this.student = s;
        this.prepareInterface();
    }

    // This function is to prepare the User Interface of the form and initialize the datepicker model
    private void prepareInterface() {
        UtilDateModel model = new UtilDateModel();
        Date a =null;
        try {
            a = new SimpleDateFormat("dd/MM/yyyy").parse(this.student.getDOB());
        }
        catch(Exception ex) {
            System.out.println("prepareInterface : "+ex);
        }
        model.setValue(a);
        model.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        this.datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());

        this.prepareLabels();

        this.prepareTextFields();

        this.prepareButtons();

        this.prepareActionListeners();

        this.preparePanels();

        this.prepareFrames();

        this.addElements();
    }

    // This function is to add the components to their respective containers
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

    // This function is to add action listeners to their components
    private void prepareActionListeners() {
        this.submit.addActionListener(e -> this.update());
    }

    // This function is to initialize and customize the buttons
    private void prepareButtons() {
        this.submit = new JButton("Submit");
        this.submit.setForeground(Color.white);
        this.submit.setBackground(Color.blue);
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

    // This function is to initialize and customize text fields
    private void prepareTextFields() {
        String[] st = {"I","II","III","IV"};
        String[] d = {"NONE","CSE","CSBS","EEE","ECE","CIVIL"};
        this.RegNo = new JTextField();
        this.Name = new JTextField();
        this.Dept = new JComboBox<>(d);
        this.Emails = new JTextField();
        this.YrOfStudy = new JComboBox<>(st);

        this.regNo.setText(this.student.getRegNo());
        this.Name.setText(this.student.getName());
        this.Dept.setSelectedItem(this.student.getDept());
        this.Emails.setText(this.student.getEmail());
        this.YrOfStudy.setSelectedItem(this.student.getYrOfStudy());
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

    // This function is to initialize and customize the frames
    private void prepareFrames() {
        this.frame = new JFrame("Update Form");

        this.frame.setSize(500,700);
        this.frame.setLayout(null);
        this.frame.getContentPane().setBackground(Color.blue);
        this.frame.setResizable(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    // This function will validate user input data and if it is valid then it updates the student record with given record
    private void update() {
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
        if(this.dbase.updateStudent(s)) {
            new message("Update Successful");
            new Home(this.student, this.dbase);
            this.frame.dispose();
        }
        else
            new message("Update Unsuccessful");
    }
}
