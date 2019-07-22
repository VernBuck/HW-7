import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Vernon Buck
 * @version 1.0
 */
public class Sorting {

    /**
     * Your implementation of bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("null arr or comparator");
        }
        int length = arr.length;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < length - 1; i++) {
                if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                    T temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                }
            }
            length = length - 1;
        }
    }
    
    /**
     * Your implementation of insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("null arr or comparator");
        }
        for (int i = 1; i < arr.length - 1; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                T temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j = j - 1;
            }
        }
    }

    /**
     * Your implementation of selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("null arr or comparator");
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                T temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }

    /**
     * Your implementation of quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     * 
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("parameter null");
        }
        quickHelper(arr, comparator, rand, 0, arr.length - 1);
    }

    /**
     * sorts unsorted list
     * @param arr array passed
     * @param comparator method of index comparison
     * @param rand random pivot generator
     * @param start index to start increment
     * @param end index to start decrement
     * @param <T> generic
     */
    private static <T> void quickHelper(T[] arr, Comparator<T> comparator,
                                        Random rand, int start, int end) {
        if (start >= end) {
            return;
        } else {
            int pivot = randomGenerator(rand, start, end);
            int partition = partition(arr, comparator, pivot, start, end);
            quickHelper(arr, comparator, rand, start, partition - 1);
            quickHelper(arr, comparator, rand, partition, end);
        }
    }

    /**
     * creates the random number
     * @param rand generator
     * @param start starting point of array sort
     * @param end ending point of array sot
     * @return random value
     */
    private static int randomGenerator(Random rand, int start, int end) {
        return rand.nextInt(end - start) + start;
    }

    /**
     * Method of traversing array and swapping values
     * @param arr array to traverse
     * @param comparator compares elements
     * @param pivot compared to start and end
     * @param start starting point before inc.
     * @param end ending point before dec.
     * @param <T> generic
     * @return sends back new start
     */
    private static <T> int partition(T[] arr, Comparator<T> comparator,
                                     int pivot, int start, int end) {
        while (start <= end) {
            while (start <= end && arr[start] != arr[pivot]
                    && comparator.compare(arr[start], arr[pivot]) < 0) {
                start++;
            }
            while (start <= end && arr[end] != arr[pivot]
                    && comparator.compare(arr[end], arr[pivot]) > 0) {
                end--;
            }
            if (start <= end) {
                T temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
                start++;
                end--;
            }
        }
        return start;
    }

    /**
     * Your implementation of merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("null arr or comparator");
        }
        int n = arr.length;
        int j = 0;
        int middle = n / 2;
        T[] arr1 = (T[]) new Object[n / 2];
        for (; j < middle; j++) {
            arr1[j] = arr[j];
        }
        T[] arr2 = (T[]) new Object[n - arr1.length];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = arr[i + j];
        }
        if (n > 1) {
            mergeSort(arr1, comparator);
            mergeSort(arr2, comparator);
        }
        int x = 0;
        int y = 0;
        if (arr.length != 0) {
            for (int i = 0; i < arr.length; i++) {
                if (x != arr1.length && y != arr2.length) {
                    if (comparator.compare(arr1[x], arr2[y]) < 0) {
                        arr[i] = arr1[x];
                        x++;
                    } else {
                        arr[i] = arr2[y];
                        y++;
                    }
                } else {
                    if (x == arr1.length && y != arr2.length) {
                        for (int z = y; z < arr2.length; z++) {
                            arr[i] = arr2[z];
                            i++;
                        }
                    } else {
                        for (int w = x; w < arr1.length; w++) {
                            arr[i] = arr1[w];
                            i++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Your implementation of LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("null arr");
        }
        return arr;
    }
    
    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     * 
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int result = pow(base, exp / 2);
        if (exp % 2 == 1) {
            return result * result * base;
        } else {
            return result * result;
        }
    }
}
