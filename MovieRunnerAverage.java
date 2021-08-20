import java.util.*;
/**
 * Write a description of MovieRunnerAverage here.
 * Genreates average movie ratings based on ratings of the raters in the data files
 * @Suhas (your name) 
 * @31/07/2021 (a version number or a date)
 */
public class MovieRunnerAverage {
    public void printAverageRatings () {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        SecondRatings sr = new SecondRatings(moviesFile, ratersFile);
        System.out.println("Number of movies in " + moviesFile + " are " + sr.getMovieSize());
        System.out.println("Number of raters in " + ratersFile + " are " + sr.getRaterSize());
        int minRaters = 12;
        ArrayList<Rating> avgRatings = sr.getAverageRatings(minRaters);
        Collections.sort(avgRatings, new RatingComparator());
        for(int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            String movieID = rating.getItem();
            String movieTitle = sr.getTitle(movieID);
            Double movieRating = rating.getValue();
            System.out.println(movieRating + "\t" + movieTitle);
        }
        System.out.println(avgRatings.size() + " movies have " + minRaters + " or more ratings");
    }
    
    public void getAverageRatingOneMovie() {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        SecondRatings sr = new SecondRatings(moviesFile, ratersFile);
        System.out.println("Number of movies in " + moviesFile + " are " + sr.getMovieSize());
        System.out.println("Number of raters in " + ratersFile + " are " + sr.getRaterSize());
        String movieTitle = "Vacation";
        String movieID = sr.getID(movieTitle);
        ArrayList<Rating> avgRatings = sr.getAverageRatings(0);
        for(int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            String movID = rating.getItem();
            if(movID.equals(movieID)) {
                Double movieRating = rating.getValue();
                System.out.println(movieRating + "\t" + movieTitle);
            }
            
        }
    }
}
