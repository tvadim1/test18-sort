import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[5];
        Random rnd = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextInt(20) + 1;
        }

        array = new int[]{16, 2, 5, 4, 16, 11};

        //Arrays.sort(array); - условие размера: быстрая, вставками, пирамидальная

        //System.out.println(Arrays.toString(array));           //оптимальный, работает
        //Arrays.asList(array).stream().forEach(System.out::println);   //не работает
        //Stream.of(array).forEach(System.out::println);        //не работает
        //Arrays.stream(array).forEach(System.out::println);    //неоптимальный, работает

        System.out.println("Input = " + Arrays.toString(array));
        //selectSort(array);
        //bubbleSort(array);
        //insertionSort(array);
        quickSort(array);
        System.out.println("Output = " + Arrays.toString(array));
    }

    // сортировка выбором
    // лучшее время, например, даже пусть все отсортировано заранее - O(N^2)
    // среднее/худшее - O(N^2)
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            boolean wasSwapped = false;

            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                    wasSwapped = true;
                }
            }

            if (wasSwapped) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }

            //System.out.println(Arrays.toString(array));
        }
    }

    // улучшение-сортировка шейкером, двунаправленная, выталкивающая min элемент влево
    public static void bubbleSort(int[] array) {
        // оптимизирован, т.к. следит за перестановками,
        // если их не было, то значит уже отсортирован и проходы больше не нужны
        // лучшее время, когда все отсортировано заранее, 1 проход - O(N)
        // среднее/худшее - O(N^2)

        // количество проходов
        for (int i = 0; i < array.length - 1; i++) {
            boolean wasSwapped = false;
            // проход по текущему массиву
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    wasSwapped = true;
                } else {
                    wasSwapped = false;
                }

                //System.out.println(Arrays.toString(array));
            }

            //System.out.println("wasSwapped = " + wasSwapped);

            if (!wasSwapped) {
                return;
            }
        }
    }

    // Сортировка вставками
    // Сложность О(N^2), лучшая, если ни одной перестановки - O(N)
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int sorted = i - 1;
            while (sorted >= 0 && array[sorted] > array[sorted + 1]) {
                int temp = array[sorted];
                array[sorted] = array[sorted + 1];
                array[sorted + 1] = temp;
                sorted--;
            }
        }
    }

    // Сортировка быстрая Хоара
    // Сложность О(nlogN), лучшая, если ни одной перестановки - O(N^2)
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int start, int end) {
        if (start >= end) return;

        int rightStart = partOfQuickSort(array, start, end);

        quickSort(array, start, rightStart - 1);
        quickSort(array, rightStart, end);
    }

    private static int partOfQuickSort(int[] array, int left, int right) {
        int pivot = array[(left + right) / 2];

        while (left <= right) {
            while (array[left] < pivot) left++;
            while (array[right] > pivot) right--;

            if (left <= right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }

        return left;
    }
}