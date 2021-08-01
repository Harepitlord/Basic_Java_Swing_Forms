package SimpleStudentDatabase;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;


import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.Properties;

// This class runs the student database
public class FormsRunner {
    public static void main(String[] args) {
        Database dbase = new Database();
        new Home(dbase);
        //new sample();
    }
}

class sample {
    private JFrame frame;
    private JButton submit;
    private JDatePickerImpl datePicker;

    public sample() {
        this.frame = new JFrame("Testing");
        SqlDateModel model = new SqlDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        this.datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
        this.frame.add(this.datePicker);
        this.submit = new JButton("submit");
        this.submit.addActionListener(e -> {
            Date a = (Date)this.datePicker.getModel().getValue();


            System.out.println(a+"\n"+this.datePicker.getModel().getDay());
        });
        this.frame.add(this.submit);
        this.frame.setSize(400,400);
        this.frame.setVisible(true);
        this.frame.setLayout(new FlowLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

