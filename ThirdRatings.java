import java.util.*;
/**
 * Write a description of ThirdRatings here.
 * Generates data related to movie and ratings in an efficient way and also applies filter
 * @Suhas (your name) /
 * @01/08/2021 (a version number or a date)/
 */
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsFile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsFile);
    }
    
    public int getRaterSize () {
        return myRaters.size();
    }
    
    private double getAverageByID (String movieID, int minimalRaters) {
        FirstRatings fr = new FirstRatings();
        int movieRaters = fr.numRaters(myRaters, movieID);
        
        
        
        double avg = 0;
        if(movieRaters < minimalRaters) {
            return 0.0;
        }
        for(Rater rater : myRaters) {
            double rating = rater.getRating(movieID);
            if(rating != -1) {
                avg = avg + rating;
            }
        }
        return avg/movieRaters;
    }
    
    public ArrayList<Rating> getAverageRatings (int minimalRaters) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        avgRatings.clear();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for(String movie : myMovies) {
            double avg = getAverageByID(movie, minimalRaters);
            
            if(avg != 0.0) {
                Rating avgRating = new Rating(movie, avg);
                avgRatings.add(avgRating);
            }
        }
        return avgRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        avgRatings.clear();
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
        for(String movie : myMovies) {
            double avg = getAverageByID(movie, minimalRaters);
            
            if(avg != 0.0) {
                Rating avgRating = new Rating(movie, avg);
                avgRatings.add(avgRating);
            }
        }
        return avgRatings;
    }
    
}
