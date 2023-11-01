import java.io.*;
import java.util.*;

public class DatasetUtil {
    private static final Random rng = new Random();

    private DatasetUtil() {}

    public static List<Integer> generateRandom(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(rng.nextInt(n));
        }
        return list;
    }

    public static List<Integer> generateSorted(int n) {
        List<Integer> list = generateRandom(n);
        Collections.sort(list);
        return list;
    }

    public static List<Integer> generateReversed(int n) {
        List<Integer> list = generateSorted(n);
        Collections.reverse(list);
        return list;
    }

    public static void generateDatasets() {
        int[] nValues = {500, 5000, 50000};

        for (int n : nValues) {
            List<Integer> random = generateRandom(n);
            List<Integer> sorted = generateSorted(n);
            List<Integer> reversed = generateReversed(n);

            writeArrayListToFile(random, "./datasets/random" + n + ".txt");
            writeArrayListToFile(sorted, "./datasets/sorted" + n + ".txt");
            writeArrayListToFile(reversed, "./datasets/reversed" + n + ".txt");
        }
    }

    public static Map<String, ArrayList<Integer>> loadDatasets() {
        String[] fileNames = {
                "random500.txt", "sorted500.txt", "reversed500.txt",
                "random5000.txt", "sorted5000.txt", "reversed5000.txt",
                "random50000.txt", "sorted50000.txt", "reversed50000.txt"
        };

        Map<String, ArrayList<Integer>> datasetMap = new TreeMap<>();
        for (String x : fileNames) {
            datasetMap.put(x, readArrayListFromFile("./datasets/" + x));
        }
        return datasetMap;
    }

    private static void createFileIfNotExists(File file) {
        try {
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                if (fileCreated) {
                    System.out.println("File created: " + file.getPath());
                } else {
                    System.out.println("File already exists: " + file.getPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeArrayListToFile(List<Integer> list, String filePath) {
        File file = new File(filePath);
        createFileIfNotExists(file);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Integer item : list) {
                writer.write(item.toString());
                writer.newLine();
            }
            System.out.println("ArrayList saved to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Integer> readArrayListFromFile(String filePath) {
        ArrayList<Integer> readList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)) ) {
            String line;
            while ((line = reader.readLine()) != null) {
                readList.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readList;
    }
}
