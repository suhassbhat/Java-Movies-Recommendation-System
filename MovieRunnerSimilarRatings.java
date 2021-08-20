import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * Finds weignted average ratings based on different preferences and also uses filters
 * @Suhas (your name) 
 * @02/08/2021 (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void printAverageRatings () {
        String moviesFile = "ratedmovies_short.csv";
        //String moviesFile = "ratedmoviesfull.csv";
        String ratersFile = "ratings_short.csv";
        //String ratersFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratersFile);
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + RaterDatabase.size());
        int minRaters = 1;
        ArrayList<Rating> avgRatings = fr.getAverageRatings(minRaters);
        System.out.println(avgRatings.size() + " movies have " + minRaters + " or more ratings");
        Collections.sort(avgRatings, new RatingComparator());
        for(int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            String movieID = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            Double movieRating = rating.getValue();
            System.out.println(movieID + "\t" + movieTitle);
        }
        
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        String moviesFile = "ratedmovies_short.csv";
        //String moviesFile = "ratedmoviesfull.csv";
        String ratersFile = "ratings_short.csv";
        //String ratersFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratersFile);
        
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + RaterDatabase.size());
        
        int minRaters = 1;
        String genre = "Drama";
        Filter f1 = new GenreFilter(genre);
        int year = 2013;
        Filter f2 = new YearAfterFilter(year);
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(minRaters,f);
        System.out.println(avgRatings.size() + " movies have " + minRaters + " or more ratings and satisfy the criteria");
        Collections.sort(avgRatings, new RatingComparator());
        for(int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            String movieID = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            String movieGenre = MovieDatabase.getGenres(movieID);
            int movieYear = MovieDatabase.getYear(movieID);
            Double movieRating = rating.getValue();
            System.out.println(movieRating + "\t" + movieYear + "\t" + movieTitle);
            System.out.println("\t" + movieGenre);
        }
        
    }
    
    public void printSimilarRatings () {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratersFile);
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + RaterDatabase.size());
        int minRaters = 5;
        int numSimilarRaters = 20;
        String raterID = "71";
        ArrayList<Rating> avgRatings = fr.getSimilarRatings(raterID, numSimilarRaters, minRaters);
        System.out.println(avgRatings.size() + " movies have " + minRaters + " or more ratings and satisfy the criterias");
        Collections.sort(avgRatings, new RatingComparator());
        for(int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            String movieID = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            Double movieRating = rating.getValue();
            System.out.println(movieID + "\t" + "\t" + movieTitle);
        }
        
    }
    
    public void printSimilarRatingsByGenre() {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratersFile);
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + RaterDatabase.size());
        int numSimilarRaters = 20;
        String raterID = "964";
        int minRaters = 5;
        String genre = "Mystery";
        Filter f = new GenreFilter(genre);
        ArrayList<Rating> avgRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters,minRaters,f);
        System.out.println(avgRatings.size() + " movies have " + minRaters + " or more ratings and satisfy the criteria");
        Collections.sort(avgRatings, new RatingComparator());
        for(int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            String movieID = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            String movieGenre = MovieDatabase.getGenres(movieID);
            Double movieRating = rating.getValue();
            System.out.println(movieRating + "\t" + movieTitle);
            System.out.println("\t" + movieGenre);
        }
        
    }
    
    public void printSimilarRatingsByMinutes() {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratersFile);
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + RaterDatabase.size());
        int numSimilarRaters = 20;
        String raterID = "65";
        int minRaters = 5;
        int minMinutes = 105;
        int maxMinutes = 135;
        Filter f = new MinutesFilter(minMinutes, maxMinutes);
        ArrayList<Rating> avgRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters,minRaters,f);
        System.out.println(avgRatings.size() + " movies have " + minRaters + " or more ratings and satisfy the criteria");
        Collections.sort(avgRatings, new RatingComparator());
        for(int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            String movieID = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            int minutes = MovieDatabase.getMinutes(movieID);
            Double movieRating = rating.getValue();
            System.out.println(movieRating + "\t" + "Time : " + minutes + " minutes\t" + movieTitle);
            
        }
        
    }
    
    public void printSimilarRatingsByDirectors() {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratersFile);
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + RaterDatabase.size());
        int numSimilarRaters = 10;
        String raterID = "120";
        int minRaters = 2;
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        Filter f = new DirectorsFilter(directors);
        ArrayList<Rating> avgRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minRaters,f);
        System.out.println(avgRatings.size() + " movies have " + minRaters + " or more ratings and satisfy the criteria");
        Collections.sort(avgRatings, new RatingComparator());
        for(int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            String movieID = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            String movieDirectors = MovieDatabase.getDirector(movieID);
            Double movieRating = rating.getValue();
            System.out.println(movieRating + "\t" + movieTitle);
            System.out.println("\t" + movieDirectors);
        }
        
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratersFile);
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + RaterDatabase.size());
        int numSimilarRaters = 10;
        String raterID = "168";
        int minRaters = 3;
        String genre = "Drama";
        Filter f1 = new GenreFilter(genre);
        int minMinutes = 80;
        int maxMinutes = 160;
        Filter f2 = new MinutesFilter(minMinutes, maxMinutes);
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> avgRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minRaters,f);       
        System.out.println(avgRatings.size() + " movies have " + minRaters + " or more ratings and satisfy the criteria");
        Collections.sort(avgRatings, new RatingComparator());
        for(int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            String movieID = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            String movieGenre = MovieDatabase.getGenres(movieID);
            int minutes = MovieDatabase.getMinutes(movieID);
            
            Double movieRating = rating.getValue();
            System.out.println(movieRating + "\t" + "Time : " + minutes + "\t" + movieTitle);
            System.out.println("\t" + movieGenre);
        }
        
    }
    public void printSimilarRatingsByYearAfterAndMinutes() {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratersFile);
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + RaterDatabase.size());
        int numSimilarRaters = 10;
        String raterID = "314";
        int minRaters = 5;
        int year = 1975;
        Filter f1 = new YearAfterFilter(year);
        int minMinutes = 70;
        int maxMinutes = 200;
        Filter f2 = new MinutesFilter(minMinutes, maxMinutes);
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> avgRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minRaters,f);
        System.out.println(avgRatings.size() + " movies have " + minRaters + " or more ratings and satisfy the criteria");
        Collections.sort(avgRatings, new RatingComparator());
        for(int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            String movieID = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            
            int minutes = MovieDatabase.getMinutes(movieID);
            int movieYear = MovieDatabase.getYear(movieID);
            Double movieRating = rating.getValue();
            System.out.println(movieRating + "\t" + movieYear + "\tTime : " + minutes + " minutes\t" + movieTitle);
            
        }
        
    }
   
    
}
