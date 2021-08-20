import java.util.*;
/**
 * Write a description of EfficientRater here.
 * Efficient version of the PlainRater class created using HashMap
 * @Suhas (your name) 
 * @01/08/2021 (a version number or a date)
 */
public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
        myRatings.clear();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if(myRatings.containsKey(item)) {
            Rating rating = myRatings.get(item);
            return rating.getValue();
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> items = new ArrayList<String>();
        items.clear();
        for(String item : myRatings.keySet()) {
            items.add(item);
        }
        return items;
    }
}
