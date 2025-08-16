import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.spec.ECField;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class StudentDetails extends JFrame implements ActionListener {

    Choice choiceName;
    JTable table;
    JButton search,update,add,print,cancel;
    StudentDetails() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by Name: ");
        heading.setBounds(30, 50, 150, 30);
        add(heading);

        choiceName = new Choice();
        choiceName.setBounds(180, 50, 150, 20);
        add(choiceName);

        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 150, 1000, 400);
        add(jsp);

        try {
            Connect c = new Connect();

            // fill dropdown
            String sql = "SELECT student_name FROM Student"; // ✅ check column name in your table
            PreparedStatement stat = c.con.prepareStatement(sql);
            ResultSet res = stat.executeQuery();
            while (res.next()) {
                choiceName.add(res.getString("student_name"));
            }

            // fill table
            sql = "SELECT * FROM Student";
            stat = c.con.prepareStatement(sql);
            res = stat.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(res));

        } catch (Exception e) {
            e.printStackTrace(); // show error in console
        }

        search = new JButton("Search");
        search.setBounds(30,100,100,30);
        search.addActionListener(this);
        add(search);

        update = new JButton("Update");
        update.setBounds(150,100,100,30);
        update.addActionListener(this);
        add(update);

        add = new JButton("ADD");
        add.setBounds(270,100,70,30);
        add.addActionListener(this);
        add(add);

        print = new JButton("Print");
        print.setBounds(350,100,100,30);
        print.addActionListener(this);
        add(print);

        cancel = new JButton("Cancel");
        cancel.setBounds(460,100,100,30);
        cancel.addActionListener(this);
        add(cancel);

        setSize(1000, 500);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ✅ ensures window closes
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==search)
        {

            try {
                Connect c = new Connect();
                String sql = "select * from Student where student_name = ?";
                PreparedStatement stat = c.con.prepareStatement(sql);
                stat.setString(1,choiceName.getSelectedItem());
                ResultSet res = stat.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(res));

            } catch (Exception e) {
                e.printStackTrace(); // show error in console
            }

        }
        else if(actionEvent.getSource()==print)
        {
            try{
                table.print();
            }catch (Exception e){
                throw  new RuntimeException();
            }

        }
        else if(actionEvent.getSource()==add)
        {
            setVisible(false);
            new AddStudent();
        }
        else if (actionEvent.getSource()==update)
        {
            setVisible(false);
            // new UpdateStudent;
        }
        else if(actionEvent.getSource()==cancel){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentDetails();
    }
}
