import java.util.*;
/**
 * Write a description of RecommendationRunner here.
 * Links the project to show the required results on the webpage
 * @Suhas (your name) 
 * @02/08/2021 (a version number or a date)
 */
public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        Random myRandom = new Random();
        ArrayList<String> moviesShort = new ArrayList<String>();
        moviesShort.clear();
        int size = 15;
        for(int i = 0; i < size; i++) {
            int index = myRandom.nextInt(movies.size());
            moviesShort.add(movies.get(index));
        }
        return moviesShort;
    }
    
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fr = new FourthRatings();
        int minRaters = 2;
        int numSimilarRaters = 10;
        String raterID = webRaterID;
        ArrayList<Rating> avgRatings = fr.getSimilarRatings(raterID, numSimilarRaters, minRaters);
        
        
        Collections.sort(avgRatings, new RatingComparator1());
        int size = avgRatings.size();
        if(size == 0) {
            System.out.println("<p><b>" + avgRatings.size() + " movies are recommended based on your preferences</p></b>");
        }
        else if(size > 20) {
            size = 20;
            System.out.println("<p><b>Top 20 recommended movies based on your preferences are : </p></b>");
        }
        else {
            System.out.println("<p><b>Top " + size + " recommended movies based on your preferences are : </p></b>");
        }
        System.out.println("<style>th { \n background-color :  #99ccff; \n color :  #000066; \n text-align: center; \n font-size: 22pt; \n} \n td { \n background-color : #000066; \n color :  #ddddff; \n text-align: center; \n font-size: 17pt; \n} \n </style>");
        if(size > 0) {
            System.out.println("<table><tr><th>Movie ID</th><th>Movie Poster</th><th>Movie Title</th><th>Weighted Ratings</th><th>Movie Genre</th><th>Movie Year</th><th>Movie Director</th><th>Movie Runtime (in minutes)</th></tr>");
            for(int i = 0; i < size; i++) {
                Rating rating = avgRatings.get(i);
                String movieID = rating.getItem();
                String movieTitle = MovieDatabase.getTitle(movieID);
                String movieGenre = MovieDatabase.getGenres(movieID);
                int minutes = MovieDatabase.getMinutes(movieID);
                String movieDirectors = MovieDatabase.getDirector(movieID);
                int movieYear = MovieDatabase.getYear(movieID);
                String moviePoster = MovieDatabase.getPoster(movieID);
                double movieRating = rating.getValue();
                System.out.println("<tr><td>" + movieID + "</td><td><img src=\"" + moviePoster + "\" width=\"150px\" height=\"300px\"></td><td>" + movieTitle + "</td><td>" + movieRating + "</td><td>" + movieGenre + "</td><td>" + movieYear + "</td><td>" + movieDirectors + "</td><td>" + minutes + "</td></tr>");
            }
            System.out.println("</table>");
        }
    }
    
}
