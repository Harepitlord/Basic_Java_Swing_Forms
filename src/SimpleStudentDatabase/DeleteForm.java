package SimpleStudentDatabase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

// This class is to generate UI for deletion of a student record from the database
public class DeleteForm {

    // Data members
    private final Database dbase;
    private final Student student;

    private JFrame frame;
    private JPanel spanel,panel;
    private JLabel data;
    private JButton submit,cancel;

    // Constructor
    public DeleteForm(Student s,Database d) {
        this.student = s;
        this.dbase = d;
        this.prepareInterface();
    }

    // This function is to prepare the components of the interface and generate the interface
    private void prepareInterface() {

        this.prepareLabels();

        this.prepareButtons();

        this.prepareActionListeners();

        this.preparePanels();

        this.prepareFrames();

        this.addElements();
    }

    // This function is to add components to their respective containers
    private void addElements() {
        this.panel.add(this.data);
        this.panel.add(this.submit);
        this.panel.add(this.cancel);

        this.spanel.add(this.panel);

        this.frame.add(spanel);
    }

    // This function adds the action listeners to the respective components
    private void prepareActionListeners() {
        this.submit.addActionListener(e->this.delete());
        this.cancel.addActionListener(e -> {
            new Home(this.student,this.dbase);
            this.frame.dispose();
        });

    }

    // This function is to initialize and customize the buttons
    private void prepareButtons() {
        this.submit = new JButton("Yes");
        this.cancel = new JButton("No");

        this.submit.setBackground(Color.blue);
        this.cancel.setBackground(Color.blue);

        this.submit.setForeground(Color.white);
        this.cancel.setForeground(Color.white);
    }

    // This function is to initialize and customize the panels
    private void preparePanels() {
        this.spanel = new JPanel();
        this.panel = new JPanel();

        this.spanel.setBackground(Color.white);
        this.spanel.setLayout(null);
        this.spanel.setBorder(new LineBorder(Color.BLACK,2));
        this.spanel.setBounds(50,50,200,300);

        this.panel.setBackground(Color.white);
        this.panel.setLayout(new GridLayout(8,1,20,20));
        this.panel.setBounds(30,30,150,250);
        this.panel.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
    }

    // This function is to initialize and customize the labels
    private void prepareLabels() {
        this.data = new JLabel("Do want to Delete: ");
    }

    // This function is to initialize and customize the frames
    private void prepareFrames() {
        this.frame = new JFrame("Delete Form");

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(300,400);
        this.frame.setLayout(null);
        this.frame.setVisible(true);
    }

    // This function will delete the student record if the user prompts to
    private void delete() {
        if(this.dbase.deleteStudent(this.student)) {
            new Home(this.dbase);
            this.frame.dispose();
        }
        else
            new message("Delete Unsuccessful");
    }
}
