import java.util.ArrayList;
import java.util.Arrays;

public class CountingSort implements SortingAlgorithm {
  @Override
  public String toString() {
    return "Counting Sort";
  }
  @Override
  public void sort(ArrayList<Integer> array) {
    int size = array.size();
    int[] output = new int[size + 1];

    int max = array.get(0);
    for (int i = 1; i < size; i++) {
      if (array.get(i) > max) max = array.get(i);
    }

    int[] count = new int[max + 1];
    Arrays.fill(count, 0);
    for (Integer elem : array) {
      count[elem]++;
    }

    for (int i = 1; i <= max; i++) {
      count[i] += count[i - 1];
    }

    for (int i = size - 1; i >= 0; i--) {
      output[--count[array.get(i)]] = array.get(i);
    }

    for (int i = 0; i < size; i++) {
      array.set(i, output[i]);
    }
  }
}
