import java.util.*;
/**
 * Write a description of FinalRunner here.
 * Final movie recommendation runner/tester.
 * @Suhas (your name) 
 * @05/01/2022 (a version number or a date)
 */
public class FinalRunner {
    public static void main (String args[]) {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratersFile);
        RecommendationRunner helperRunner = new RecommendationRunner();
        String raterID = "0";
        ArrayList<String> moviesID = helperRunner.getItemsToRate();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your ratings for the following movies to get movie recommendations");
        for(int i = 0; i < 15; i++) {
            System.out.println(MovieDatabase.getTitle(moviesID.get(i)));
            double rating = sc.nextDouble();
            RaterDatabase.addRaterRating(raterID,moviesID.get(i),rating);
        }
        int minRaters = 2;
        int numSimilarRaters = 10;
        FourthRatings fr  = new FourthRatings();
        ArrayList<Rating> avgRatings = fr.getSimilarRatings(raterID, numSimilarRaters, minRaters);
        Collections.sort(avgRatings, new RatingComparator1());
        int size = avgRatings.size();
        if(size == 0) {
            System.out.println(avgRatings.size() + " movies are recommended based on your preferences");
        }
        else if(size > 20) {
            size = 20;
            System.out.println("Top 20 recommended movies based on your preferences are :");
        }
        else {
            System.out.println("Top " + size + " recommended movies based on your preferences are :");
        }
        
        if(size > 0) {
            for(int i = 0; i < size; i++) {
                Rating rating = avgRatings.get(i);
                String movieID = rating.getItem();
                String movieTitle = MovieDatabase.getTitle(movieID);
                String movieGenre = MovieDatabase.getGenres(movieID);
                int minutes = MovieDatabase.getMinutes(movieID);
                String movieDirectors = MovieDatabase.getDirector(movieID);
                int movieYear = MovieDatabase.getYear(movieID);
                double movieRating = rating.getValue();
                System.out.println("Movie Title: " + movieTitle + "\tMovie Rating: " + movieRating + "\tMovie Genre: " + movieGenre + "\tMovie Year: " + movieYear + "\tDirectors: " + movieDirectors + "\tRuntime (in minutes): " + minutes);
            }
        }
    }
}
