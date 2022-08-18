import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 585;    //Game Screen width
    static final int SCREEN_HEIGHT = 565;   //Game Screen height
    static final int UNIT_SIZE = 15;    //Size of object
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;   //Objects that can fit on screen
    static  final int DELAY = 75;   //delay time for timer
    final int x[] = new int[GAME_UNITS];    //Represents x coordinates of body part of snake
    final int y[] = new int[GAME_UNITS];    //Represents y coordinates of body part of snake
    int bodyParts = 6;  //initial body parts of snake
    int applesEaten = 0;    //apple eaten by snake or the number of score
    int appleX; //x coordinate of apple
    int appleY; // y coordinate of apple
    char direction = 'R';   //direction of snake motion... R_Right, L_Left, U_Up, D_Down
    boolean running = false;
    Timer timer;
    Random random;
    JButton homeButton;
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame(){
        newApple(); //create new Apple on screen
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }
    public void draw(Graphics g){

        if(running) {
            g.setColor(Color.red);  //set apple color
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);   //draw apple

            //drawing snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) { //snake head
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {   //snake body
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

            //Displays score at top
            g.setColor(Color.blue);
            g.setFont(new Font("Times New Roman", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize() );
        }
        else{
            gameOver(g);
        }

    }

    public void newApple(){
        //generate x and y coordinates of apple
        appleX = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
        appleY = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;

    }
    public void move(){
        for(int i=bodyParts; i>0; i--){ //moving each body part
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch(direction){
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
        }

    }
    public void checkApple(){
        if((x[0] == appleX) && (y[0] == appleY)){
            bodyParts++;    //increase body parts
            applesEaten++;  //increments the scores
            newApple(); //call to generate new apple
        }

    }
    public void checkCollisions(){
        //check if head collides with body
        for(int i=bodyParts; i>0; i--){
            if((x[0]== x[i]) && (y[0] == y[i])){
                running = false;
            }
        }

        //Check if head collides with left boundary
        if(x[0] < 0){
            running = false;
        }

        //Check if head collides with right boundary
        if(x[0] > SCREEN_WIDTH){
            running = false;
        }

        //Check if head collides with top boundary
        if(y[0] < 0){
            running = false;
        }

        //Check if head collides with bottom boundary
        if(y[0] > SCREEN_HEIGHT){
            running = false;
        }

        if(!running){
            timer.stop();
        }
    }

    public void gameOver(Graphics g){
        //Dispaly game over message
        g.setColor(new Color(136, 0, 27));
        g.setFont(new Font("Times New Roman", Font.BOLD, 80));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2,SCREEN_WIDTH/2 );

        //display total scores
        g.setColor(Color.blue);
        g.setFont(new Font("Times New Roman", Font.BOLD, 60));
        FontMetrics metricsScore = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metricsScore.stringWidth("Score: " + applesEaten))/2,g.getFont().getSize() );

        //check high scores
        int count = 0;
        //check if user made score greater than the already stored scores
        for (int i = 0; i < SnakeGame.list.size(); i++) {
            if (applesEaten > SnakeGame.list.get(i).getScore())
                count++;
        }

        //check if user made the high score
        if ((count > 0 || (SnakeGame.list.size() < 3)) && applesEaten != 0) {

            g.setFont(new Font("Times New Roman", 1, 28));
            g.drawString("Congratulations! You have made High Scores..", 30, 350);

            //add scores in the list
            SnakeGame.list.add(new Scores(applesEaten, SnakeGame.name));

            //sort the list
            Scores.sort(SnakeGame.list);

            //removes if their are more than 3 scores in list
            if(SnakeGame.list.size()>3) {
                for (int i = 3; i < SnakeGame.list.size(); i++) {
                    SnakeGame.list.remove(i);
                }
            }




            PrintWriter writer = null;
            FileWriter fwrite = null;
            try {
                File f = new File("High Scores.txt");


                //stores new top 3 scores in file
                fwrite = new FileWriter("High Scores.txt", false);
                for (int i = 0; i < SnakeGame.list.size(); i++) {
                    fwrite.write(SnakeGame.list.get(i).getScore() + ":" + SnakeGame.list.get(i).getName() + ":\n");
                }
                fwrite.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                //writer.close();
                try {
                    fwrite.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }




        }


        //display button to return to home page
        homeButton = new JButton("Back to Home");
        homeButton.setBounds(150, 400, 300, 45);
        homeButton.setBackground(new Color(98,52, 18));
        homeButton.setFont(new Font("Times New Roman",Font.BOLD,  28));
        homeButton.setForeground(Color.black);
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Home();
            }
        });
        homeButton.setVisible(true);

        this.add(homeButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();

    }



    public class MyKeyAdapter extends KeyAdapter{
        @Override
        //controls direction using arrow keys
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }
        }


    }

}
