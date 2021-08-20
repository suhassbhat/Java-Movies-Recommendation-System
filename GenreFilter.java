
/**
 * Write a description of GenreFilter here.
 * Filters movies according to genres.
 * @Suhas (your name) 
 * @01/08/2021 (a version number or a date)
 */
public class GenreFilter implements Filter{
    private String myGenre;
    
    public GenreFilter (String genre) {
        myGenre = genre;
    }
    
    public boolean satisfies (String id) {
        String genre = MovieDatabase.getGenres(id);
        int index = genre.indexOf(myGenre);
        if(index == -1) {
            return false;
        }
        return true;
    }
}
