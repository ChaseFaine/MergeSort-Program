/**
 * OutOfOrder.java 
 *
 */
 
/**
 * Counting out-of-order pairs in an array.
 * 
 * This class implements two different algorithms for
 * computing the number of out-of-order pairs in an int array.
 *
 * @author Chase Faine  
 * @version Fall 2020 
 */
    
import java.util.Arrays;

public class OutOfOrder
{

    /**
     * Computes number out-of-order by checking every pair.
     * 
     * @param a    A list of integers
     * @return     The number of pairs out of order
     *             in the list 
     */
    public static int countOutOfOrder1(int [] a)
    {
        // put your code here
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Computes number out-of-order while doing a mergesort.
     * 
     * @param a    A list of integers
     * @return     The number of pairs out of order
     *             in the list 
     */
    public static int countOutOfOrder2(int [] a)
    {
        // put your code here
        return mergeSort(a, 0, a.length - 1);
    }

    public static int mergeSort(int[] a, int left, int right) {
        int count = 0;
        if (left < right) {
            int center = (left + right) / 2;
            count += mergeSort(a, left, center);
            count += mergeSort(a, center + 1, right);
            count += merge(a, left, center, right);
            return count;
        }
        return count;
    }

    public static int merge(int[] a, int left, int center, int right) {
        int count = 0;
        int n1 = center - left + 1;
        int n2 = right - center;
        int l[] = new int[n1];
        int r[] = new int[n2];
        for (int i = 0; i < n1; i++) {
            l[i] = a[left + i];
        }
        for (int j = 0; j < n2; j++) {
            r[j] = a[center + 1 + j];
        }
        int i = 0;
        int j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (l[i] <= r[j]) {
                a[k] = l[i];
                i++;
            }
            else {
                a[k] = r[j];
                j++;
                count += (center + 1) - (left + i);
            }
            k++;
        }
        while (i < n1) {
            a[k] = l[i];
            i++;
            k++;
        }
        while (j < n2) {
            a[k] = r[j];
            j++;
            k++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = {5, 1, -3, 8, 2, 10, 4, 3, 2, 16};
        System.out.println("Count1: " + countOutOfOrder1(a));
        System.out.println("Count2: " + countOutOfOrder2(a));
    }

}


