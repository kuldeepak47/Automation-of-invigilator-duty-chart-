package Utils;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

import javax.swing.AbstractButton;
import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class gui extends JFrame implements ActionListener {

    protected JButton button1, button2, button3, button4, button5, button6, button7, button8, button9;
    JComboBox combobox1,combobox2,combobox3; 

    FileOutputStream outputStream;

    File f1;
    File f2;
    File f3;
    File f4;

    String s1 = "Regular";
    String s2 = "Sheet1";
    String s3 = "Sheet1";
    String s4 = "Form responses 1";

    // Reader r1 = new Reader(f1, s1, f2, s2);

    gui() {
        // GUI
        JLabel header = new JLabel("Acropolis Institue of Technology & Science, Indore", SwingConstants.CENTER);
        // header.setText("Shri G.S Institue of Technology & Science, Indore");
        header.setFont(new Font("Calbiri", Font.BOLD, 30));
        header.setBounds(200, 0, 1000, 100);
        add(header);

        JLabel s_header = new JLabel("Exam Duty Invigilation Chart Generator", SwingConstants.CENTER);
        s_header.setFont(new Font("Calbiri", Font.ITALIC, 25));
        s_header.setBounds(200, 70, 1000, 80);
        // s_header.put(s_header.UNDERLINE, s_header.UNDERLINE_ON);
        add(s_header);

        JLabel line_break = new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------", SwingConstants.CENTER);
        line_break.setFont(new Font("Calbiri", Font.CENTER_BASELINE, 10));
        line_break.setBounds(200, 90, 1000, 80);
        // s_header.put(s_header.UNDERLINE, s_header.UNDERLINE_ON);
        add(line_break);



        JLabel instructions = new JLabel("*INSTRUCTIONS:");
        instructions.setFont(new Font("Calbiri", Font.PLAIN, 23));
        instructions.setBounds(200, 130, 1000, 80);
        add(instructions);

        JLabel instructions1 = new JLabel("1.Select Input files in proper format .");
        instructions1.setFont(new Font("Calbiri", Font.PLAIN, 16));
        instructions1.setBounds(200, 170, 1000, 80);
        add(instructions1);

        JLabel instructions2 = new JLabel("2.View Sample files for reference .");
        instructions2.setFont(new Font("Calbiri", Font.PLAIN, 16));
        instructions2.setBounds(200, 200, 1000, 80);
        add(instructions2);

        JLabel instructions3 = new JLabel("3.For More help refer User Manual.");
        instructions3.setFont(new Font("Calbiri", Font.PLAIN, 16));
        instructions3.setBounds(200, 230, 1000, 80);
        add(instructions3);

        JLabel h3 = new JLabel("_________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________", SwingConstants.CENTER);
        h3.setFont(new Font("Calbiri", Font.BOLD, 10));
        h3.setBounds(200, 250, 1000, 80);
        add(h3);

        JLabel h4 = new JLabel("SELECT INPUT FILES");
        h4.setFont(new Font("Calbiri", Font.PLAIN, 26));
        h4.setBounds(200, 300, 1000, 80);
        add(h4);


        try {
            final ImageIcon icon = new ImageIcon(
                    Objects.requireNonNull(this.getClass().getResource("/Internproject/src/main/java/logo.png")));
            JLabel logo = new JLabel();
            logo.setIcon(icon);
            logo.setBounds(50, 300, 200, 40);
            add(logo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Generate Button
        button1 = new JButton("Generate Chart");
        button1.setBounds(650, 680, 150, 40);
        button1.setFont(new Font("Calbiri", Font.PLAIN, 15));
        button1.addActionListener(this);
        add(button1);

        // File 1
        JLabel label2 = new JLabel();
        label2.setText("File of regular faculties:");
        label2.setFont(new Font("Calbiri", Font.BOLD, 20));
        label2.setBounds(30, 400, 300, 40);
        add(label2);

        button2 = new JButton("Choose File");
        button2.setBounds(550, 400, 150, 40);
        button2.addActionListener(this);
        add(button2);

        button6 = new JButton("View sample file");
        button6.setBounds(360, 400, 150, 40);
        button6.addActionListener(this);
        add(button6);

        // File 2
        JLabel label3 = new JLabel();
        label3.setText("File of contract faculties:");
        label3.setFont(new Font("Calbiri", Font.BOLD, 20));
        label3.setBounds(30, 450, 300, 40);
        add(label3);

        button3 = new JButton("Choose file");
        button3.setBounds(550, 450, 150, 40);
        button3.addActionListener(this);
        add(button3);

        button7 = new JButton("View sample file");
        button7.setBounds(360, 450, 150, 40);
        button7.addActionListener(this);
        add(button7);

        // File 3
        JLabel label4 = new JLabel();
        label4.setText("File of exam dates:");
        label4.setFont(new Font("Calbiri", Font.BOLD, 20));
        label4.setBounds(30, 500, 300, 40);
        add(label4);

        button4 = new JButton("Choose file");
        button4.setBounds(550, 500, 150, 40);
        button4.addActionListener(this);
        add(button4);

        button8 = new JButton("View sample file");
        button8.setBounds(360, 500, 150, 40);
        button8.addActionListener(this);
        add(button8);

        // File 4
        JLabel label5 = new JLabel();
        label5.setText("File of unavailable faculties:");
        label5.setFont(new Font("Calbiri", Font.BOLD, 20));
        label5.setBounds(30, 550, 300, 40);
        add(label5);

        button5 = new JButton("Choose file");
        button5.setBounds(550, 550, 150, 40);
        button5.addActionListener(this);
        add(button5);

        button9 = new JButton("View sample file");
        button9.setBounds(360, 550, 150, 40);
        button9.addActionListener(this);
        add(button9);

        JLabel label6= new JLabel();
         label6.setText("SELECT NO. OF PROFESSORS and Date FOR DUTY");
         label6.setBounds(700, 300, 1000, 80);
         label6.setFont(new Font("Calbiri", Font.PLAIN, 26));
         add(label6);

         JLabel label7 = new JLabel();
         label7.setText("Professor");
         label7.setFont(new Font("Calbiri", Font.BOLD, 20));
         label7.setBounds(800, 400, 300, 40);
         add(label7);
         
         JLabel label8 = new JLabel();
         label8.setText("Assistant Professor");
         label8.setFont(new Font("Calbiri", Font.BOLD, 20));
         label8.setBounds(800, 450, 300, 40);
         add(label8);
         
         JLabel label9 = new JLabel();
         label9.setText("Associate Professor");
         label9.setFont(new Font("Calbiri", Font.BOLD, 20));
         label9.setBounds(800, 500, 300, 40);
         add(label9);


         
 
        // JButton button6 = new JButton("Choose file");
        // button6.setBounds(800, 500, 100, 40);
        // button6.addActionListener(this);
        // add(button6);

        JLabel label10 = new JLabel();
        label10.setText("Date of Exam ->");
        label10.setFont(new Font("Calbiri", Font.BOLD, 20));
        label10.setBounds(800, 560, 300, 40);
        add(label10);

        JTextField tField1 = new JTextField();
        tField1.setBounds(1000,560,110,40);
        add(tField1);


       //JComboBox combobox1,combobox2,combobox3;         //Combobox Declaration

    //    String[] NoP = {"0","1","2","3"};            //array of no. of professors for duty
    //    String[] NoAP = {"0","1","2","3","4","5","6"};           //array of no. of Assistant professors for duty
    //    String[] NoAP2 = {"0","1","2","3","4","5","6"};         //array of no. of Associate professors for duty 
       
        combobox1= new JComboBox();
        combobox1.addItem("0");
        combobox1.addItem("1");
        combobox1.addItem("2");
        combobox1.addItem("3");
        combobox1.setBounds(1050, 410, 60, 20);
       combobox1.addActionListener(this);
        this.add(combobox1);

        
        combobox2= new JComboBox();
        combobox2.addItem("0");
        combobox2.addItem("1");
        combobox2.addItem("2");
        combobox2.addItem("3");
        combobox2.addItem("4");
        combobox2.addItem("5");
        combobox2.addItem("6");
        combobox2.addItem("7");
        combobox2.setBounds(1050, 460, 60, 25);
        this.add(combobox2);
       combobox2.addActionListener(this);

        combobox3= new JComboBox();
        combobox3.addItem("0");
        combobox3.addItem("1");
        combobox3.addItem("2");
        combobox3.addItem("3");
        combobox3.addItem("4");
        combobox3.addItem("5");
        combobox3.addItem("6");
        combobox3.addItem("7");
       
        combobox3.setBounds(1050, 510, 60, 25);
       combobox3.addActionListener(this);
        this.add(combobox3);


        // JTextField tf2 = new JTextField();
   
        // tft2.setBounds(1100, 450, 100, 200);

        
         
    
    }

    private void setFile1(File ss) {
        this.f1 = ss;
    }

    private void setFile2(File sss) {
        this.f2 = sss;
    }

    private void setFile3(File ssss) {
        this.f3 = ssss;
    }

    private void setFile4(File sssss) {
        this.f4 = sssss;
    }



    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == button2) {
            JFileChooser fileChooser = new JFileChooser();
            // restrict the user to select files of all types
            fileChooser.setAcceptAllFileFilterUsed(false);

            // set a title for the dialog
            fileChooser.setDialogTitle("Select a .xlsx file");

            // only allow files of .txt extension
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .xlsx files", "xlsx");
            fileChooser.addChoosableFileFilter(restrict);
            int i = fileChooser.showOpenDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                File file1 = fileChooser.getSelectedFile();
                setFile1(file1);
                System.out.println("File 1 selected");
            }
        } else if (evt.getSource() == button3) {
            JFileChooser fileChooser = new JFileChooser();
            // restrict the user to select files of all types
            fileChooser.setAcceptAllFileFilterUsed(false);

            // set a title for the dialog
            fileChooser.setDialogTitle("Select a .xlsx file");

            // only allow files of .txt extension
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .xlsx files", "xlsx");
            fileChooser.addChoosableFileFilter(restrict);
            int i = fileChooser.showOpenDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                File file1 = fileChooser.getSelectedFile();
                setFile2(file1);
                System.out.println("File 2 selected");
            }
        } else if (evt.getSource() == button4) {
            JFileChooser fileChooser = new JFileChooser();
            // restrict the user to select files of all types
            fileChooser.setAcceptAllFileFilterUsed(false);

            // set a title for the dialog
            fileChooser.setDialogTitle("Select a .xlsx file");

            // only allow files of .txt extension
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .xlsx files", "xlsx");
            fileChooser.addChoosableFileFilter(restrict);
            int i = fileChooser.showOpenDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                File file1 = fileChooser.getSelectedFile();
                setFile3(file1);
                System.out.println("File 3 selected");
            }
        } else if (evt.getSource() == button5) {
            JFileChooser fileChooser = new JFileChooser();
            // restrict the user to select files of all types
            fileChooser.setAcceptAllFileFilterUsed(false);

            // set a title for the dialog
            fileChooser.setDialogTitle("Select a .xlsx file");

            // only allow files of .txt extension
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .xlsx files", "xlsx");
            fileChooser.addChoosableFileFilter(restrict);
            int i = fileChooser.showOpenDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                File file1 = fileChooser.getSelectedFile();
                setFile4(file1);
                System.out.println("File 4 selected");
            }
        } else if (evt.getSource() == button1) {

            System.out.println("Generate clicked");
            Reader r1 = new Reader(this.f1, this.s1, this.f2, this.s2);
            r1.store();
            // r1.print();
            r1.readHeader(this.f3, this.s3);
            // r1.printHeader();
            r1.availabilityStore(this.f4, this.s4);

            try {
                outputStream = new FileOutputStream("ResultSheet.xlsx");
                r1.generateFile(outputStream);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }

        }

        else if (evt.getSource() == button6) {
            try {
                Desktop.getDesktop().open(new File("Internproject/src/main/java/file3.xlsx"));
            } catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }

        else if (evt.getSource() == button7) {
            try {
                Desktop.getDesktop().open(new File("Internproject/src/main/java/Contract_Faculties.xlsx"));
            } catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }

        else if (evt.getSource() == button8) {
            try {
                Desktop.getDesktop().open(new File("Internproject/src/main/java/Datefile.xlsx"));
            } catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }

        else if (evt.getSource() == button9) {
            try {
                Desktop.getDesktop().open(new File("Internproject/src/main/java/Test_file.xlsx"));
            } catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }


        if (evt.getSource()== combobox1){
            System.out.println(combobox1.getSelectedItem());
            
        }
        if (evt.getSource()== combobox2){
            System.out.println(combobox2.getSelectedItem());
            
        }
        if (evt.getSource()== combobox3){
            System.out.println(combobox3.getSelectedItem());
            
        }
    }



}   
