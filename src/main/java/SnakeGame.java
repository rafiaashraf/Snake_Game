import java.io.*;
import java.util.ArrayList;

public class SnakeGame {
    public static ArrayList<Scores> list = new ArrayList<>();   //stores the data from txt file
    public static String name;

    public static void main(String[] args){

        //reads data from file and stores it in the "list" arraylist
        FileReader reader = null;
        try {
            File f = new File("High Scores.txt");
            if(!f.exists())
                f.createNewFile();
            reader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line ;

            String[] array = new String[2];
            while((line = bufferedReader.readLine()) != null){
                     array = line.split(":");
                    list.add(new Scores(Integer.parseInt(array[0]), array[1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //instantiate the home class
        new Home();

    }
}
