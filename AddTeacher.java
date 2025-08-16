import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class AddTeacher extends JFrame implements ActionListener {
    JTextField teacherName,teacherEmail,teacherPhone,teacherAge,teacherAddress,teacherDepartment;
    JButton save,exit;

    AddTeacher(){


        setLayout(null);
        JLabel heading = new JLabel("New Teacher Information");
        heading.setBounds(310,30,500,50);
        heading.setFont(new Font("Serif",Font.BOLD,30));
        add(heading);

        JLabel name = new JLabel("Teacher Name: ");
        name.setBounds(30,100,200,30);
        name.setFont(new Font("Serif",Font.BOLD,20));
        add(name);

        teacherName= new JTextField();
        teacherName.setBounds(230,100,350,30);
        add(teacherName);

        JLabel age = new JLabel("Age: ");
        age.setBounds(600,100,70,30);
        age.setFont(new Font("Serif",Font.BOLD,20));
        add(age);

        teacherAge= new JTextField();
        teacherAge.setBounds(680,100,50,30);
        add(teacherAge);

        JLabel phone = new JLabel("Phone: ");
        phone.setBounds(30,150,100,30);
        phone.setFont(new Font("Serif",Font.BOLD,20));
        add(phone);

        teacherPhone= new JTextField();
        teacherPhone.setBounds(150,150,250,30);
        add(teacherPhone);

        JLabel email = new JLabel("Email: ");
        email.setBounds(450,150,100,30);
        email.setFont(new Font("Serif",Font.BOLD,20));
        add(email);

        teacherEmail= new JTextField();
        teacherEmail.setBounds(550,150,300,30);
        add(teacherEmail);

        JLabel address = new JLabel("Address: ");
        address.setBounds(30,200,120,30);
        address.setFont(new Font("Serif",Font.BOLD,20));
        add(address);

        teacherAddress= new JTextField();
        teacherAddress.setBounds(150,200,200,30);
        add(teacherAddress);

        JLabel department = new JLabel("Department: ");
        department.setBounds(400,200,150,30);
        department.setFont(new Font("Serif",Font.BOLD,20));
        add(department);

        teacherDepartment= new JTextField();
        teacherDepartment.setBounds(550,200,200,30);
        add(teacherDepartment);

        save = new JButton("Save");
        save.setBounds( 350, 250,100,30);
        save.addActionListener(this);
        add(save);

        exit = new JButton("Exit");
        exit.setBounds(500,250,100,30);
        exit.addActionListener(this);
        add(exit);



        setSize(1000,400);
        setLocation(100,100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String name= teacherName.getText();
        String ag=teacherAge.getText();
        String phone=teacherPhone.getText();
        String email=teacherEmail.getText();
        String address = teacherAddress.getText();
        String department = teacherDepartment.getText();
        if(actionEvent.getSource()==save)
        {
            if (name.isEmpty()  || ag.isEmpty() || !ag.matches("\\d{1,2}") || address.isEmpty() || department.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Information Not Valid",
                        "Information Not Valid",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                int age=Integer.parseInt(ag);
                String sql = "insert into Teacher  ( teacher_name,phone,age,email,address,department) values (?,?,?,?,?,?)";
                try {
                    Connect c= new Connect();
                    PreparedStatement statement=c.con.prepareStatement(sql);
                    statement.setString(1,name);
                    statement.setString(2,phone);
                    statement.setInt(3,age);
                    statement.setString(4,email);
                    statement.setString(5,address);
                    statement.setString(6,department);

                    int rowInsert= statement.executeUpdate();
                    if(rowInsert>0)
                    {
                        System.out.println("Update teacher Information");
                    }

                }
                catch ( Exception e)
                {
                    throw new RuntimeException(e);
                }
                setVisible(false);
                new Main();
            }
        }
        else if(actionEvent.getSource()==exit)
        {
            setVisible(false);
            new Main();
        }
    }

    public static void main(String[] args) {
        new AddTeacher();
    }
}


