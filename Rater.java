import java.util.*;
/**
 * Write a description of Rater here.
 * Interface for rater classes
 * @Suhas (your name) 
 * @31/07/2021 (a version number or a date)
 */
public interface Rater {
    public void addRating(String item, double rating);
    public boolean hasRating(String item);
    public String getID();
    public double getRating(String item);
    public int numRatings();
    public ArrayList<String> getItemsRated();
}
