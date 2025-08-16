
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JButton loginButton,cancelButton,signUpButton;
    JTextField textField;
    JPasswordField passwordField;
    Login(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel username= new JLabel("UserName:");
        username.setBounds(40,20,100,20);
        add(username);
        textField= new JTextField();
        textField.setBounds(150,20,150,20);
        add(textField);

        JLabel password= new JLabel("Password:");
        password.setBounds(40,80,100,20);
        add(password);
        passwordField= new JPasswordField();
        passwordField.setBounds(150,80,150,20);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(120,150,100,30);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton .setBounds(250,150,100,30);
        cancelButton.addActionListener(this);
        add(cancelButton );

        signUpButton = new JButton("Sign Up");
        signUpButton .setBounds(200,200,100,30);
        signUpButton.addActionListener(this);
        add(signUpButton );


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

        if(actionEvent.getSource()==loginButton)
        {
            String username=textField.getText();
            String password=passwordField.getText();
            try{
                Connect c=new Connect();
                String sql="select * from users where username =? and password_num =?";
                PreparedStatement statement = c.con.prepareStatement(sql);
                statement.setString(1,username);
                statement.setString(2,password);
                ResultSet res =statement.executeQuery();
                if(res.next())
                {
                    setVisible(false);
                    new Main();
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "Credentials do not match!",
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
            catch (Exception e){// This shows the real SQL error
                throw new RuntimeException(e);
            }
        }
        else if(actionEvent.getSource()==cancelButton)
        {
            setVisible(false);
        }
        else if(actionEvent.getSource()==signUpButton)
        {
            setVisible(false);
            new SignUp();
        }

    }
    public static void main(String[] args) {
        new Login();
    }


    public class AddStudent extends JFrame {

        AddStudent(){
            setSize(600,600);
            setLocation(100,100);
            setVisible(true);
        }
    }
}
