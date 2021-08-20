
/**
 * Write a description of MinutesFilter here.
 * Filters movies based on runtime duration being between minMinutes and maxMinutes.
 * @Suhas (your name) 
 * @01/08/2021 (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int minMinutes;
    private int maxMinutes;
    
    public MinutesFilter (int min, int max) {
        minMinutes = min;
        maxMinutes = max;
    }
    
    public boolean satisfies (String id) {
        int minutes = MovieDatabase.getMinutes(id);
        if(minutes >= minMinutes && minutes <= maxMinutes) {
            return true;
        }
        return false;
    }
}
