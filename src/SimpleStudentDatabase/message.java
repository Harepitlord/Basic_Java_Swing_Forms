package SimpleStudentDatabase;

import javax.swing.*;
import java.awt.*;

// This class is to create message popup dialog box
public class message {

    // Data Members
    private JFrame frame;
    private JLabel data;
    private JButton submit;

    // Constructors
    public message(String s) {
        this.prepareInterface(s);
    }

    // This function is to prepare the interface and the components and generates the UI
    private void prepareInterface(String a) {
        this.prepareButtons();

        this.prepareActionListeners();

        this.prepareLabels(a);

        this.prepareFrames();

        this.addElements();
    }

    // This function is to add components to their respective containers
    private void addElements() {
        this.frame.add(data);
        this.frame.add(this.submit);
    }

    // This function is to initialize and customize the labels
    private void prepareLabels(String a) {
        this.data = new JLabel(a);
    }

    // This function is to initialize and customize the buttons
    private void prepareButtons() {
        this.submit = new JButton("OK");
    }

    // This function is to initialize and customize the frames
    private void prepareFrames() {
        this.frame = new JFrame("Alert");

        this.frame.setSize(400,200);
        this.frame.setLayout(new GridLayout(2,1,10,10));
        this.frame.getContentPane().setBackground(Color.WHITE);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    // This function is to add the action listeners to the components
    private void prepareActionListeners() {
        this.submit.addActionListener(e -> this.frame.dispose());
    }
}
