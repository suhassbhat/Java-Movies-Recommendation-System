import java.util.*;
/**
 * Write a description of SecondRatings here.
 * Prcoesses movie and ratings data to find average ratings of the movies.
 * @Suhas (your name) 
 * @31/07/2021 (a version number or a date)
 */



public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviesFile, String ratingsFile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviesFile);
        myRaters = fr.loadRaters(ratingsFile);
    }
    
    public int getMovieSize () {
        return myMovies.size();
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
        
        for(Movie movie : myMovies) {
            double avg = getAverageByID(movie.getID(), minimalRaters);
            
            if(avg != 0.0) {
                Rating avgRating = new Rating(movie.getID(), avg);
                avgRatings.add(avgRating);
            }
        }
        return avgRatings;
    }
    
    public String getTitle(String movieID) {
        for(Movie movie : myMovies) {
            if(movie.getID().equals(movieID)) {
                return movie.getTitle();
            }
        }
        return "ID NOT FOUND";
    }
    
    public String getID(String movieTitle) {
        for(Movie movie : myMovies) {
            if(movie.getTitle().equals(movieTitle)) {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}
