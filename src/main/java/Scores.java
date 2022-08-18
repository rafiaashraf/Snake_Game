import java.util.ArrayList;

public class Scores {
    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scores(int score, String name) {
        this.score = score;
        this.name = name;
    }

    //sort the arraylist of Scores in descending order
    public static void sort(ArrayList<Scores> s){
        for(int i = 0; i<s.size(); i++){
            int nextIndex = i;
            for(int j = i+1; j< s.size(); j++ ){
                if(s.get(j).getScore() > s.get(nextIndex).getScore()){
                    nextIndex = j;
                }
            }
            int tempScore = s.get(i).getScore();
            String tempName = s.get(i).getName();
            s.get(i).setScore(s.get(nextIndex).getScore()) ;
            s.get(i).setName(s.get(nextIndex).getName());

            s.get(nextIndex).setScore(tempScore) ;
            s.get(nextIndex).setName(tempName);


        }
    }
}
