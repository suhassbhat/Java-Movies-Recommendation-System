import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * Finds average Rating of Movies using different filters
 * @Suhas (your name) 
 * @01/08/2021 (a version number or a date)
 */
public class MovieRunnerWithFilters {
    public void printAverageRatings () {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratersFile);
        MovieDatabase.initialize(moviesFile);
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + tr.getRaterSize());
        int minRaters = 35;
        ArrayList<Rating> avgRatings = tr.getAverageRatings(minRaters);
        System.out.println(avgRatings.size() + " movies have " + minRaters + " or more ratings");
        Collections.sort(avgRatings, new RatingComparator());
        for(int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            String movieID = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            Double movieRating = rating.getValue();
            System.out.println(movieRating + "\t" + movieTitle);
        }
        
    }
    
    public void printAverageRatingsByYear() {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratersFile);
        MovieDatabase.initialize(moviesFile);
        
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + tr.getRaterSize());
        
        int minRaters = 20;
        int year = 2000;
        Filter f = new YearAfterFilter(year);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minRaters,f);
        System.out.println(avgRatings.size() + " movies have " + minRaters + " or more ratings and satisfy the criteria");
        Collections.sort(avgRatings, new RatingComparator());
        for(int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            String movieID = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            int movieYear = MovieDatabase.getYear(movieID);
            Double movieRating = rating.getValue();
            System.out.println(movieRating + "\t" + movieYear + "\t" + movieTitle);
        }
        
    }
    
    public void printAverageRatingsByGenre() {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratersFile);
        MovieDatabase.initialize(moviesFile);
        
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + tr.getRaterSize());
        
        int minRaters = 20;
        String genre = "Comedy";
        Filter f = new GenreFilter(genre);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minRaters,f);
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
    
    public void printAverageRatingsByMinutes() {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratersFile);
        MovieDatabase.initialize(moviesFile);
        
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + tr.getRaterSize());
        
        int minRaters = 5;
        int minMinutes = 105;
        int maxMinutes = 135;
        Filter f = new MinutesFilter(minMinutes, maxMinutes);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minRaters,f);
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
    
    public void printAverageRatingsByDirectors() {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratersFile);
        MovieDatabase.initialize(moviesFile);
        
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + tr.getRaterSize());
        
        int minRaters = 4;
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        Filter f = new DirectorsFilter(directors);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minRaters,f);
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
    
    public void printAverageRatingsByYearAfterAndGenre() {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratersFile);
        MovieDatabase.initialize(moviesFile);
        
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + tr.getRaterSize());
        
        int minRaters = 8;
        String genre = "Drama";
        Filter f1 = new GenreFilter(genre);
        int year = 1990;
        Filter f2 = new YearAfterFilter(year);
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minRaters,f);
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
    public void printAverageRatingsByDirectorsAndMinutes() {
        //String moviesFile = "ratedmovies_short.csv";
        String moviesFile = "ratedmoviesfull.csv";
        //String ratersFile = "ratings_short.csv";
        String ratersFile = "ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratersFile);
        MovieDatabase.initialize(moviesFile);
        
        System.out.println("Number of movies in " + moviesFile + " are " + MovieDatabase.size());
        System.out.println("Number of raters in " + ratersFile + " are " + tr.getRaterSize());
        
        int minRaters = 3;
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        Filter f1 = new DirectorsFilter(directors);
        int minMinutes = 90;
        int maxMinutes = 180;
        Filter f2 = new MinutesFilter(minMinutes, maxMinutes);
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minRaters,f);
        System.out.println(avgRatings.size() + " movies have " + minRaters + " or more ratings and satisfy the criteria");
        Collections.sort(avgRatings, new RatingComparator());
        for(int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            String movieID = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            String movieDirectors = MovieDatabase.getDirector(movieID);
            int minutes = MovieDatabase.getMinutes(movieID);
            Double movieRating = rating.getValue();
            System.out.println(movieRating + "\t" + "Time : " + minutes + " minutes\t" + movieTitle);
            System.out.println("\t" + movieDirectors);
        }
        
    }
}
