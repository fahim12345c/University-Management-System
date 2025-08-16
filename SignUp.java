import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp extends JFrame implements ActionListener {
    JButton signUpButton,cancelButton;
    JTextField userField;
    JPasswordField passwordField,confirmPasswordField;
    SignUp ()
    {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel username= new JLabel("UserName:");
        username.setBounds(40,20,100,20);
        add(username);
        userField= new JTextField();
        userField.setBounds(150,20,150,20);
        add(userField);

        JLabel password= new JLabel("Password:");
        password.setBounds(40,80,100,20);
        add(password);
        passwordField= new JPasswordField();
        passwordField.setBounds(150,80,150,20);
        add(passwordField);

        JLabel confirmPassword= new JLabel("Confirm Pass:");
        confirmPassword.setBounds(40,150,100,20);
        add(confirmPassword);
        confirmPasswordField= new JPasswordField();
        confirmPasswordField.setBounds(150,150,150,20);
        add(confirmPasswordField);

        signUpButton = new JButton("Sign Up");
        signUpButton .setBounds(120,200,100,30);
        signUpButton.addActionListener(this);
        add(signUpButton );

        cancelButton = new JButton("Cancel");
        cancelButton .setBounds(250,200,100,30);
        cancelButton.addActionListener(this);
        add(cancelButton );


        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2=i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel image= new JLabel(i3);
        image.setBounds(350,20,200,200);
        add(image);


        setLocation(500,300);
        setSize(520,300);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==signUpButton)
        {
            String username=userField.getText();
            String password=passwordField.getText();
            String confirmPassword=confirmPasswordField.getText();
            System.out.println(password);
            System.out.println(confirmPassword);
            if(!password.equals(confirmPassword) && username!=null)
            {
                System.out.println("i am here");
                JOptionPane.showMessageDialog(this,
                        "Credentials do not match!",
                        "Registration Error",
                        JOptionPane.ERROR_MESSAGE);

                // Clear fields and focus on password
                passwordField.setText("");
                confirmPasswordField.setText("");
                passwordField.requestFocus();
            }
            else
            {
                String sql = "INSERT INTO users (username, password_num) VALUES (?, ?)";

                try {
                    Connect c= new Connect();
                    PreparedStatement statement=c.con.prepareStatement(sql);
                    statement.setString(1,username);
                    statement.setString(2,password);

                    int rowInsert =statement.executeUpdate();
                    if(rowInsert>0)
                    {
                        System.out.println("new student login");
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                setVisible(false);


            }


        }
        else if(actionEvent.getSource()==cancelButton)
        {
            setVisible(false);
        }
        new Login();
    }
}
