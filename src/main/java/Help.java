import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
It provides general instructions to play snake game
 */
public class Help {
    private JFrame jFrame;
    private JLabel jLabel;
    private JButton backButton;

    public Help(){

        //creating label to display heading
        JLabel heading = new JLabel();
        heading.setBounds(200, 70, 300, 50);
        heading.setFont(new Font("Times New Roman", 1, 40));
        heading.setForeground(new Color(136, 0, 27));
        heading.setText("HELP");


        //creates the label and sets instructions in them
        JLabel[] inst = new JLabel[5];
        for(int i = 0; i<5 ; i++){
            inst[i] =new JLabel();
            inst[i].setFont(new Font("Times New Roman", 1, 20));
            inst[i].setForeground(Color.white);

        }
        inst[0].setText("Right Arrow key will move the snake in right direction \n");
        inst[1].setText("Left Arrow key will move the snake in left direction \n");
        inst[2].setText("Up Arrow key will move the snake in Upward direction \n");
        inst[3].setText("Down Arrow key will move the snake in Downward direction \n" );
        inst[4].setText("Game will over if snake hits the boundary or hits itself");

        jLabel = new JLabel();
        jLabel.setSize(600,600);
        jLabel.setBackground(Color.black);
        jLabel.setOpaque(true);
        jLabel.add(createBackButton());

        //adding sub labels to one main label
        jLabel.add(heading);
        int y = 200;
       for(int i=0; i<5; i++){

            inst[i].setBounds(15, y, 550, 45);
            jLabel.add(inst[i]);
            y = y + 50;
        }

        //creating jFrame for the HELP scene
        jFrame = new JFrame("Help");
        jFrame.add(jLabel);
        jFrame.setBackground(Color.black);
        jFrame.setSize(600, 600);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    //creating back button which provides the option to move back to home page
    public JButton createBackButton(){
        backButton = new JButton("BACK");
        backButton.setBounds(20, 20, 120, 35);
        backButton.setBackground(new Color(98,52, 18));
        backButton.setFont(new Font("Times New Roman",Font.BOLD,  25));
        backButton.setForeground(Color.black);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();

                new Home();
            }
        });
        return backButton;
    }

}
