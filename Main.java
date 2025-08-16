import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    JMenuItem ex,calculator,notepad, studentInformation,facultyInformation,facultyDetails,studentDetails;
    Main(){
        setSize(1540,720);
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/RU1.jpg"));
        Image i2=i1.getImage().getScaledInstance(1400, 600, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel image= new JLabel(i3);
        add(image);
        JMenuBar menu= new JMenuBar();
        JMenu newInformation = new JMenu("New Information");
        newInformation.setForeground(Color.BLUE);
        menu.add(newInformation);

        facultyInformation= new JMenuItem("Faculty Information");
        facultyInformation.addActionListener(this);
        newInformation.add(facultyInformation);
        JMenuItem departmentInformation= new JMenuItem("Department Information");
        newInformation.add(departmentInformation);
        studentInformation= new JMenuItem("Student Information");
        studentInformation.addActionListener(this);
        newInformation.add(studentInformation);

        JMenu details = new JMenu("Details");
        details.setForeground(Color.BLUE);
        menu.add(details);

        facultyDetails= new JMenuItem("Faculty Details");
        facultyDetails.addActionListener(this);
        details.add(facultyDetails);
        JMenuItem departmentDetails= new JMenuItem("Department Details");
        details.add(departmentDetails);
        studentDetails= new JMenuItem("Student Details");
        studentDetails.addActionListener(this);
        details.add(studentDetails);

        JMenu examDetails = new JMenu("Exam Details");
        examDetails.setForeground(Color.BLUE);
        menu.add(examDetails);

        JMenu leave = new JMenu("Leave");
        leave.setForeground(Color.red);
        menu.add(leave);

        JMenuItem facultyLeave= new JMenuItem("Faculty Leave");
        leave.add(facultyLeave);
        JMenuItem studentLeave = new JMenuItem("Student Leave");
        leave.add(studentLeave);

        JMenuItem examInformation = new JMenuItem( "Exam Information");
        examDetails.add(examInformation);
        JMenuItem examResults = new JMenuItem("Exam Results");
        examDetails.add(examResults);

        JMenu feeDetails= new JMenu("Fee Details");
        feeDetails.setForeground(Color.BLUE);
        menu.add(feeDetails);

        JMenuItem admissionFee= new JMenuItem("Admission Fee");
        feeDetails.add(admissionFee);
        JMenuItem tuitionFee = new JMenuItem("Tuition Fee");
        feeDetails.add(tuitionFee);

        JMenuItem examFee = new JMenuItem("Exam Fee");
        feeDetails.add("Exam Fee");


        JMenu updateDetails= new JMenu("Update Details");
        updateDetails.setForeground(Color.green);
        menu.add(updateDetails);

        JMenuItem studentUpdateDetails= new JMenuItem("Student Update Info");
        updateDetails.add(studentUpdateDetails);
        JMenuItem facultyUpdateDetails = new JMenuItem("Faculty Update Info");
        updateDetails.add(facultyUpdateDetails);

        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.BLUE);
        menu.add(utility);

        calculator =new JMenuItem("Calculator");
        calculator.addActionListener(this);
        utility.add(calculator);
        notepad=new JMenuItem("NotePad");
        notepad.addActionListener(this);
        utility.add(notepad);

        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.red);
        menu.add(exit);

        ex = new JMenuItem("exit");
        ex.addActionListener(this);
        exit.add(ex);


        setJMenuBar(menu);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==ex)
        {
             setVisible(false);
        }
        else if(actionEvent.getSource()==calculator){
            try{
                String os = System.getProperty("os.name").toLowerCase();

                if (os.contains("win")) {
                    // Windows
                    Runtime.getRuntime().exec("calc.exe");
                } else if (os.contains("nux") || os.contains("nix") || os.contains("mac")) {
                    Runtime.getRuntime().exec("gnome-calculator");
                }

            }
            catch (Exception e){
                System.out.println("error");
            }

        }
        else if(actionEvent.getSource()==notepad){
            try{
                String os = System.getProperty("os.name").toLowerCase();

                if (os.contains("win")) {
                    // Windows
                    Runtime.getRuntime().exec("notepad.exe");
                } else if (os.contains("nux") || os.contains("nix") || os.contains("mac")) {
                    // Linux or macOS
                    // Try gedit (GNOME), xed (Linux Mint), or nano (terminal)
                    Runtime.getRuntime().exec("xed"); // Linux Mint's default GUI editor
                }
            }
            catch (Exception e){
                System.out.println("error");
            }

        }
        else if(actionEvent.getSource()==studentInformation)
        {
            setVisible(false);
            new AddStudent();
        }
        else if(actionEvent.getSource()==facultyInformation)
        {
            setVisible(false);
            new AddTeacher();
        }
        else if(actionEvent.getSource()==studentDetails)
        {
            setVisible(false);
            new StudentDetails();
        }
        else if(actionEvent.getSource()==facultyDetails)
        {
            setVisible(false);
            new TeacherDetails();
        }
    }


    public static void main(String[] args) {
        new Main();
    }
}
