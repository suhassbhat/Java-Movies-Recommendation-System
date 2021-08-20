
/**
 * Write a description of DirectorsFilter here.
 * Filters movies based on directors that directed the movies
 * @Suhas (your name) 
 * @01/08/2021 (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    String[] directors;
    public DirectorsFilter (String director) {
        directors = director.split(",");
    }
    
    public boolean satisfies (String movieID) {
        String director = MovieDatabase.getDirector(movieID);
        for(int i = 0; i < directors.length; i++) {
            if(director.indexOf(directors[i]) != -1) {
                return true;
            }
        }
        return false;
    }
}
