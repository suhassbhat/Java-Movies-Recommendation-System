import java.util.*;
/**
 * Write a description of FourthRatings here.
 * Class to generate weighted ratings according to one's preference.
 * @Suhas (your name) 
 * @02/08/2021 (a version number or a date)
 */
public class FourthRatings {
    public int numRaters (ArrayList<Rater> raters, String movieID) {
        int count = 0;
        
        for(Rater rater : raters) {
            if(rater.hasRating(movieID)) {
                count++;
            }
            
        }
        return count;
    }
    
    private double getAverageByID (String movieID, int minimalRaters) {
        
        int movieRaters = numRaters(RaterDatabase.getRaters(), movieID);
        
        
        
        double avg = 0;
        if(movieRaters < minimalRaters) {
            return 0.0;
        }
        for(Rater rater : RaterDatabase.getRaters()) {
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
    
    private double dotProduct (Rater me, Rater r) {
        double dotProduct = 0;
        ArrayList<String> itemsRated = me.getItemsRated();
        
        for(String item : itemsRated) {
            if(r.hasRating(item)) {
                double meRating = me.getRating(item) - 5;
                double rRating = r.getRating(item) - 5;
                dotProduct = dotProduct + meRating*rRating;
            }
        }
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities (String id) {
        
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        ArrayList<Rating> similarityRatings = new ArrayList<Rating>();
        similarityRatings.clear();
        Rater me = RaterDatabase.getRater(id);
        for(Rater rater : raters) {
            
            if(!rater.getID().equals(id)) {
                
                double ratingsDotProduct = dotProduct(me,rater);
                if(ratingsDotProduct > 0) {
                    similarityRatings.add(new Rating(rater.getID(), ratingsDotProduct));
                }
            }
        }
        Collections.sort(similarityRatings, new RatingComparator1());
        return similarityRatings;
    }
    
    public ArrayList<Rating> getSimilarRatings (String raterID, int numSimilarRaters, int minimalRaters) {
        
        ArrayList<Rating> ratings = new ArrayList<Rating> ();
        ratings.clear();
        ArrayList<Rater> raters = new ArrayList<Rater>();
        raters.clear();
        ArrayList<Rating> similarRatings = getSimilarities(raterID);
        if(similarRatings.size() < numSimilarRaters || numSimilarRaters < minimalRaters) {
            return ratings;
        }
        for(int i = 0; i < numSimilarRaters; i++) {
            Rater rater = RaterDatabase.getRater(similarRatings.get(i).getItem());
            raters.add(rater);
        }
        
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movie : movies) {
            int movieRaters = numRaters(raters, movie);
            if(movieRaters >= minimalRaters) {
                double rating = 0;
                for(int i = 0; i < numSimilarRaters; i++) {
                    double simValue = similarRatings.get(i).getValue();
                    double raterRating = raters.get(i).getRating(movie);
                    if(raterRating != -1) {
                        rating = rating + simValue*raterRating;
                    }
                }
                rating = rating/movieRaters;
                Rating movieRating = new Rating(movie,rating);
                ratings.add(movieRating);
            }
        }
        return ratings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter (String raterID, int numSimilarRaters, int minimalRaters, Filter f) {
        ArrayList<Rating> ratings = new ArrayList<Rating> ();
        ratings.clear();
        ArrayList<Rater> raters = new ArrayList<Rater>();
        raters.clear();
        ArrayList<Rating> similarRatings = getSimilarities(raterID);
        if(similarRatings.size() < numSimilarRaters || numSimilarRaters < minimalRaters) {
            return ratings;
        }
        for(int i = 0; i < numSimilarRaters; i++) {
            Rater rater = RaterDatabase.getRater(similarRatings.get(i).getItem());
            raters.add(rater);
        }
        
        ArrayList<String> movies = MovieDatabase.filterBy(f);
        for(String movie : movies) {
            int movieRaters = numRaters(raters, movie);
            if(movieRaters >= minimalRaters) {
                double rating = 0;
                for(int i = 0; i < numSimilarRaters; i++) {
                    double simValue = similarRatings.get(i).getValue();
                    double raterRating = raters.get(i).getRating(movie);
                    if(raterRating != -1) {
                        rating = rating + simValue*raterRating;
                    }
                }
                rating = rating/movieRaters;
                Rating movieRating = new Rating(movie,rating);
                ratings.add(movieRating);
            }
        }
        Collections.sort(ratings,new RatingComparator());
        return ratings;
    }
}
