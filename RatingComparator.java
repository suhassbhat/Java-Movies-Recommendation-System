import java.util.*;
/**
 * Write a description of RatingComparator here.
 * Comparator class to sort array list of ratings in ascending order.
 * @Suhas (your name) 
 * @31/07/2021 (a version number or a date)
 */
public class RatingComparator implements Comparator <Rating> {
    public int compare (Rating r1, Rating r2) {
        return Double.compare(r1.getValue(), r2.getValue());
    }
}
