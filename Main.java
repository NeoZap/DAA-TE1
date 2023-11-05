import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        DatasetUtil.generateDatasets();

        Map<String, ArrayList<Integer>> bcisDatasets = DatasetUtil.loadDatasets();
        performSorting(bcisDatasets, new BCIS());

        Map<String, ArrayList<Integer>> countingSortDatasets = DatasetUtil.loadDatasets();
        performSorting(countingSortDatasets, new CountingSort());

        System.out.println("[+] Done! All sorting algorithms have been measured and verified.");
    }

    private static void performSorting(Map<String, ArrayList<Integer>> datasets, SortingAlgorithm sortingAlgorithm) {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-= Sorting with " + sortingAlgorithm + " =-=-=-=-=-=-=-=-=-=-=-=");

        for (Map.Entry<String, ArrayList<Integer>> dataset : datasets.entrySet()) {
            String fileName = dataset.getKey();
            ArrayList<Integer> array = dataset.getValue();

            Runtime.getRuntime().gc();

            long startTime = System.nanoTime();
            long memoryBefore = Runtime.getRuntime().freeMemory();
            sortingAlgorithm.sort(array);
            long memoryAfter = Runtime.getRuntime().freeMemory();
            long endTime = System.nanoTime();

            if (!isSorted(array)) {
                throw new RuntimeException("[-] " + fileName + " not sorted!");
            }

            System.out.println("[+] " + fileName + " sorted successfully!");
            System.out.println("\tTime: " + (endTime - startTime) * 1e-6 + " ms");
            System.out.println("\tMemory: " + (memoryBefore - memoryAfter) + " bytes");
        }
    }

    private static boolean isSorted(ArrayList<Integer> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i) > array.get(i + 1)) {
                System.out.println(i + " " + array.get(i) + " " + array.get(i + 1));
                return false;
            }
        }
        return true;
    }
}
