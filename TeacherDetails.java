import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.spec.ECField;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class TeacherDetails extends JFrame implements ActionListener {

    Choice choiceName;
    JTable table;
    JButton search,update,add,print,cancel;
    TeacherDetails() {
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
            String sql = "SELECT teacher_name FROM Teacher"; // ✅ check column name in your table
            PreparedStatement stat = c.con.prepareStatement(sql);
            ResultSet res = stat.executeQuery();
            while (res.next()) {
                choiceName.add(res.getString("teacher_name"));
            }

            // fill table
            sql = "SELECT * FROM Teacher";
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
                String sql = "select * from Teacher where teacher_name = ?";
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
            new AddTeacher();
        }
        else if (actionEvent.getSource()==update)
        {
            setVisible(false);
            // new UpdateTeacher;
        }
        else if(actionEvent.getSource()==cancel){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TeacherDetails();
    }
}
