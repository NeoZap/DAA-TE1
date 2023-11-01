import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BCIS implements SortingAlgorithm {
    @Override
    public String toString() {
        return "BCIS";
    }

    private static int isEqual(List<Integer> array, int sl, int sr) {
        for (int k = sl + 1; k < sr; k++) {
            if (!array.get(k).equals(array.get(sl))) {
                swap(array, k, sl);
                return k;
            }
        }
        return -1;
    }

    private static void insRight(List<Integer> array, int currentItem, int sr, int right) {
        int j = sr;
        while (j <= right && currentItem > array.get(j)) {
            array.set(j - 1, array.get(j));
            j++;
        }
        array.set(j - 1, currentItem);
    }

    private static void insLeft(List<Integer> array, int currentItem, int sl, int left) {
        int j = sl;
        while (j >= left && currentItem < array.get(j)) {
            array.set(j + 1, array.get(j));
            j--;
        }
        array.set(j + 1, currentItem);
    }

    private static void swap(List<Integer> array, int i, int j) {
        Collections.swap(array, i, j);
    }

    @Override
    public void sort(ArrayList<Integer> array) {
        int left = 0;
        int right = array.size() - 1;
        int sl = left;
        int sr = right;
        while (sl < sr) {
            swap(array, sr, sl + (sr - sl) / 2);

            if (array.get(sl).equals(array.get(sr)) && isEqual(array, sl, sr) == -1) {
                return;
            }

            if (array.get(sl) > array.get(sr)) {
                swap(array, sl, sr);
            }

            int i = sl + 1;
            if (sr - sl >= 100) {
                for (; i <= Math.sqrt(1.0 * sr - sl); i++) {
                    if (array.get(sr) < array.get(i)) {
                        swap(array, sr, i);
                    } else if (array.get(sl) > array.get(i)) {
                        swap(array, sl, i);
                    }
                }
            }

            int lc = array.get(sl);
            int rc = array.get(sr);

            while (i < sr) {
                int currentItem = array.get(i);
                if (currentItem >= rc) {
                    array.set(i, array.get(sr - 1));
                    insRight(array, currentItem, sr, right);
                    sr--;
                } else if (currentItem <= lc) {
                    array.set(i, array.get(sl + 1));
                    insLeft(array, currentItem, sl, left);
                    sl++;
                    i++;
                } else {
                    i++;
                }
            }

            sl++;
            sr--;
        }
    }
}
