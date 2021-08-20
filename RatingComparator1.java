import java.util.*;
/**
 * Write a description of RatingComparator1 here.
 * omparator class to sort array list of ratings in descending order.
 * @Suhas (your name) 
 * @02/08/2021 (a version number or a date)
 */
public class RatingComparator1 implements Comparator <Rating> {
    public int compare (Rating r1, Rating r2) {
        return Double.compare(r2.getValue(), r1.getValue());
    }
}
