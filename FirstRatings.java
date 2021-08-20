import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * Prcoesses movie and ratings data to answer questions about it.
 * @Suhas (your name) 
 * @30/07/2020 (a version number or a date)
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovies (String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie> ();
        movies.clear();
        FileResource resource = new FileResource("data/" + filename);
        CSVParser parser = resource.getCSVParser();
        for(CSVRecord record : parser) {
            int movieid = Integer.parseInt(record.get("id"));
            String id = "";
            id = id + movieid;
            String title = record.get("title");
            String year = record.get("year");
            String genres = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            String poster = record.get("poster");
            int minutes = Integer.parseInt(record.get("minutes"));
            Movie movie = new Movie(id,title,year,genres,director,country,poster,minutes);
            movies.add(movie);
        }
        return movies;
    }
    
    public int countGenres (ArrayList<Movie> movies, String genre) {
        int countGenre = 0;
        for(Movie movie : movies) {
            String movieGenre = movie.getGenres();
            if(movieGenre.indexOf(genre) != -1) {
                countGenre++;
            }
        }
        return countGenre;
    }
    
    public int countMinutes (ArrayList<Movie> movies, int minutes) {
        int countMinutes = 0;
        for(Movie movie : movies) {
            int movieMinutes = movie.getMinutes();
            if(movieMinutes > minutes) {
                countMinutes++;
            }
        }
        return countMinutes;
    }
    
    public String[] splitDirectors (String director) {
        String[] directors = director.split(", ");
        return directors;
    }
    
    public HashMap<String,ArrayList<Movie>> buildDirectorMap(ArrayList<Movie> movies) {
        HashMap<String,ArrayList<Movie>> directorMap = new HashMap<String, ArrayList<Movie>>();
        directorMap.clear();
        for(Movie movie : movies) {
            String movieDirectors = movie.getDirector();
            String[] directors = splitDirectors(movieDirectors);
            for(String director : directors) {
                if(directorMap.containsKey(director)) {
                    ArrayList<Movie> directorMovies = directorMap.get(director);
                    directorMovies.add(movie);
                    directorMap.put(director,directorMovies);
                }
                else {
                    ArrayList<Movie> directorMovies = new ArrayList<Movie>();
                    directorMovies.clear();
                    directorMovies.add(movie);
                    directorMap.put(director,directorMovies);
                }
            }
        }
        return directorMap;
    }
    
    public int getMaxFromDirectorMap (HashMap<String,ArrayList<Movie>> directorMap) {
        int max = 0;
        for(String director : directorMap.keySet()) {
            ArrayList<Movie> movies = directorMap.get(director);
            int numMovies = movies.size();
            if(numMovies > max) {
                max = numMovies;
            }
        }
        return max;
    }
    
    public void printMaxDirectors (HashMap<String,ArrayList<Movie>> directorMap, int max) {
        System.out.println("Directors with max number of movies are:");
        for(String director : directorMap.keySet()) {
            ArrayList<Movie> movies = directorMap.get(director);
            int numMovies = movies.size();
            if(numMovies == max) {
                System.out.println(director + "\t:\t");
                for(Movie movie : movies) {
                    System.out.println(movie);
                }
                System.out.println();
            }
        }
        System.out.println("Number of max movies directed by the above directors are " + max);
    }
    
    public void printMovies (ArrayList<Movie> movies) {
        for(Movie movie : movies) {
            System.out.println(movie);
        }
    }
    
    public void testLoadMovies() {
        //String filename = "ratedmovies_short.csv";
        String filename = "ratedmoviesfull.csv";
        ArrayList<Movie> movies = loadMovies(filename);
        System.out.println("Number of movie in the file is: " + movies.size());
        String genre = "Comedy";
        int countGenre = countGenres(movies,genre);
        int minutes = 150;
        int countMinute = countMinutes(movies, minutes);
        HashMap<String,ArrayList<Movie>> directorMap = buildDirectorMap(movies);
        printMaxDirectors(directorMap, getMaxFromDirectorMap(directorMap));
        //printMovies(movies);
        System.out.println("Number of movies with the genre " + genre + " is " + countGenre);
        System.out.println("Number of movies with time duration over " + minutes + " minutes is " + countMinute);
            
    }
    
    public ArrayList<Rater> loadRaters (String filename) {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        raters.clear();
        FileResource resource = new FileResource("data/" + filename);
        CSVParser parser = resource.getCSVParser();
        HashSet<String> raterSet = new HashSet<String> ();
        raterSet.clear();
        for(CSVRecord record : parser) {
            String raterID = record.get("rater_id");
            
            int movID = Integer.parseInt(record.get("movie_id"));
            String movieID = "";
            movieID = movieID + movID;
            
            Double rating = Double.parseDouble(record.get("rating"));
            if(raterSet.contains(raterID)) {
                for(Rater rater : raters) {
                    String rID = rater.getID();
                    if(rID.equals(raterID)) {
                        rater.addRating(movieID, rating);
                        break;
                    }
                }
            }
            else {
                Rater rater = new EfficientRater(raterID);
                raterSet.add(raterID);
                rater.addRating(movieID, rating);
                raters.add(rater);
            }
        }
        return raters;
    }
    
    public void printRaters(ArrayList<Rater> raters) {
        for(Rater rater : raters) {
            System.out.println("Rater ID : " + rater.getID() + "\t Number of Ratings : " + rater.numRatings());
            ArrayList<String> moviesRated = rater.getItemsRated();
            for(String movie : moviesRated) {
                System.out.println("Movie ID : " + movie + "\t Rating : " + rater.getRating(movie));
            }
            System.out.println();
        }
    }
    
    public int numRatings(ArrayList<Rater> raters, String raterID) {
        int num = -1;
        for(Rater rater : raters) {
            String rID = rater.getID();
            if(rID.equals(raterID)) {
                num = rater.numRatings();
                break;
            }
        }
        return num;
    }
    
    public int getMaxRatings(ArrayList<Rater> raters) {
        int max = 0;
        for(Rater rater : raters) {
            int numRated = rater.numRatings();
            if(numRated > max) {
                max = numRated;
            }
        }
        return max;
    }
    
    public void printRatersWithMaxRatings(ArrayList<Rater> raters, int max) {
        int count = 0;
        System.out.println("Raters with maximum ratings with maximum ratings being " + max + " : ");
        for(Rater rater : raters) {
            int numRated  = rater.numRatings();
            if(numRated == max) {
                count++;
                System.out.println(rater.getID());
            }
        }
        System.out.println("Number of raters with maximum number of ratings is " + count);
    }
    
    public int numRaters (ArrayList<Rater> raters, String movieID) {
        int count = 0;
        
        for(Rater rater : raters) {
            if(rater.hasRating(movieID)) {
                count++;
            }
            
        }
        return count;
    }
    
    public int numMoviesRated (ArrayList<Rater> raters) {
        HashSet<String> movieIDs = new HashSet<String> ();
        movieIDs.clear();
        for(Rater rater : raters) {
            ArrayList<String> movies = rater.getItemsRated();
            for(String movie : movies) {
                movieIDs.add(movie);
            }
        }
        return movieIDs.size();
    }
    
    public void testLoadRaters() {
        //String filename = "ratings_short.csv";
        String filename = "ratings.csv";
        ArrayList<Rater> raters = loadRaters(filename);
        System.out.println("There are " + raters.size() + " raters in the file");
        //printRaters(raters);
        String raterID = "193";
        int num = numRatings(raters,raterID);
        System.out.println("Number of ratings by rater with Rater ID " + raterID + " is : " + num);
        int max = getMaxRatings(raters);
        printRatersWithMaxRatings(raters,max);
        String movieID = "1798709";
        int movieNumRaters = numRaters(raters,movieID);
        System.out.println("Number of ratings for movie with Movie ID " + movieID + " is : " + movieNumRaters);
        int moviesRated = numMoviesRated(raters);
        System.out.println("Number of movies rated by the raters in the file are " + moviesRated);
    }
}
