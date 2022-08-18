import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JFrame frame;
    private ImageIcon snake;
    private JLabel label;
    private JButton playButton, helpButton, scoreButton;

    public Home(){
        //designing home page
        snake = new ImageIcon(this.getClass().getResource("/snakes.png"));
        label = new JLabel(snake);
        label.setSize(600,600);

        label.add(createPlayButton());
        label.add(createHelpButton());
        label.add(createScoresButton());


        frame = new JFrame("Snake Game");
        frame.add(label);
        frame.setBackground(Color.black);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    //creates Play button and provides its action listener
    public JButton createPlayButton(){
        playButton = new JButton("PLAY");
        playButton.setBounds(50, 200, 200, 45);
        playButton.setBackground(new Color(98,52, 18));
        playButton.setFont(new Font("Times New Roman",Font.BOLD,  36));
        playButton.setForeground(Color.black);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //adds the frame to enter name of the player
                addNameFrame();
            }
        });
        return playButton;
    }

    //create help button and provide its action listener
    public JButton createHelpButton(){
        helpButton = new JButton("HELP");
        helpButton.setBounds(50, 400, 200, 45);
        helpButton.setBackground(new Color(98,52, 18));
        helpButton.setFont(new Font("Times New Roman",Font.BOLD,  36));
        helpButton.setForeground(Color.black);
        helpButton.setBorderPainted(false);
        helpButton.setFocusPainted(false);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Help();
            }
        });
        return helpButton;
    }

    //create High Scores button and provide its action listener
    public JButton createScoresButton(){
        scoreButton = new JButton("High Scores");
        scoreButton.setBounds(50, 300, 200, 45);
        scoreButton.setBackground(new Color(98,52, 18));
        scoreButton.setFont(new Font("Times New Roman",Font.BOLD,  30));
        scoreButton.setForeground(Color.black);
        scoreButton.setBorderPainted(false);
        scoreButton.setFocusPainted(false);
        scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HighScores();
            }
        });
        return scoreButton;
    }

    public void addNameFrame(){

        JFrame nameFrame = new JFrame("Name Input");
        JLabel name = new JLabel();

        JLabel nameEnter = new JLabel();
        nameEnter.setBounds(150, 70, 300, 50);
        nameEnter.setFont(new Font("Times New Roman", 1, 28));
        nameEnter.setForeground(new Color(136, 0, 27));
        nameEnter.setText("Enter your name: ");
        nameEnter.setVisible(true);

        JTextField nameInput = new JTextField();
        nameInput.setBounds(150, 140, 300, 40);
        nameInput.setVisible(true);

        JButton Enter  = new JButton("Continue");
        Enter.setBounds(300, 300, 180, 45);
        Enter.setBackground(new Color(98,52, 18));
        Enter.setFont(new Font("Times New Roman",Font.BOLD,  30));
        Enter.setForeground(Color.black);
        Enter.setBorderPainted(false);
        Enter.setFocusPainted(false);
        Enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(nameInput.getText().length() == 0)
                    JOptionPane.showMessageDialog(name, "Please Enter name to continue");
                else {
                    nameFrame.dispose();
                    SnakeGame.name = nameInput.getText();
                    new GameFrame();
                }
            }
        });




        name.add(nameEnter);
        name.add(nameInput);
        name.add(Enter);
        name.setSize(600,600);
        name.setVisible(true);
        name.setBackground(Color.black);
        name.setOpaque(true);



        nameFrame.add(name);
        nameFrame.setBackground(Color.black);
        nameFrame.setSize(600, 600);
        nameFrame.setLayout(null);
        nameFrame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        nameFrame.setLocationRelativeTo(null);
        nameFrame.setResizable(false);
        nameFrame.setVisible(true);





    }
}
