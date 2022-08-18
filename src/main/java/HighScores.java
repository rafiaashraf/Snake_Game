import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//It will display top 3 high scores made till now
public class HighScores {
    private JFrame jFrame;
    private JLabel jLabel;
    private JButton backButton;
    public HighScores(){


        JLabel heading = new JLabel();
        heading.setBounds(150, 70, 300, 50);
        heading.setFont(new Font("Times New Roman", 1, 30));
        heading.setForeground(new Color(136, 0, 27));
        heading.setText("HIGH SCORES");

        /*
        reads the high score data from arraylist and sets it as text in labels
         */
        JLabel[] scoreData = new JLabel[8];
        for(int i=0; i<8; i++){
            scoreData[i] = new JLabel();
            scoreData[i].setFont(new Font("Times New Roman", 1, 20));
            if(i==0 || i ==1)
                scoreData[i].setForeground(Color.cyan);
            else
                scoreData[i].setForeground(Color.white);

        }

        scoreData[0].setText("SCORES");
        scoreData[1].setText("NAMES");
        int j = 2;
        for(int i=0; i<SnakeGame.list.size(); i++){

            scoreData[j].setText(String.valueOf(SnakeGame.list.get(i).getScore()));
            j++;
            scoreData[j].setText(SnakeGame.list.get(i).getName());
            j++;
        }



        jLabel = new JLabel();
        jLabel.setSize(600,600);
        jLabel.setBackground(Color.black);
        jLabel.setOpaque(true);

        jLabel.add(createBackButton());
        jLabel.add(heading);
        int y = 150;
        for(int i=0; i<7; i+=2){
            scoreData[i].setBounds(50, y, 150, 45);
            scoreData[i+1].setBounds(300, y,200, 45 );
            jLabel.add(scoreData[i]);
            jLabel.add(scoreData[i+1]);
            y = y + 50;
        }






        jFrame = new JFrame("High Scores");
        jFrame.add(jLabel);
        jFrame.setBackground(Color.black);
        jFrame.setSize(600, 600);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    public JButton createBackButton(){
        backButton = new JButton("BACK");
        backButton.setBounds(20, 20, 120, 35);
        backButton.setBackground(new Color(98,52, 18));
        backButton.setFont(new Font("Times New Roman",Font.BOLD,  25));
        backButton.setForeground(Color.black);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {     //move back to homepage
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new Home();
            }
        });
        return backButton;
    }
}
