import java.util.*;

public class MaximumSquareAreaByRemovingFences {

    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[] hFences = { 2, 3 };
        int[] vFences = { 2 };

        int area = maximizeSquareArea(m, n, hFences, vFences);
        System.out.println(area);
    }

    public static int maximizeSquareArea(
        int m,
        int n,
        int[] hFences,
        int[] vFences
    ) {
        int[] allHFences = new int[hFences.length + 2];
        int[] allVFences = new int[vFences.length + 2];

        allHFences[0] = 1;
        allVFences[0] = 1;

        allHFences[hFences.length + 1] = m;
        allVFences[vFences.length + 1] = n;
        int Hidx = 1;
        int Vidx = 1;
        for (int i = 0; i < hFences.length; i++) {
            allHFences[Hidx++] = hFences[i];
        }
        for (int i = 0; i < vFences.length; i++) {
            allVFences[Vidx++] = vFences[i];
        }

        Arrays.sort(allHFences);
        Arrays.sort(allVFences);

        HashMap<Integer, Integer> hM = new HashMap<>();
        HashMap<Integer, Integer> vM = new HashMap<>();

        for (int i = 0; i < allHFences.length; i++) {
            int maxdist = 0;
            for (int j = 0; j < allHFences.length; j++) {
                if (i != j) {
                    if (
                        Math.max(maxdist, allHFences[i] - allHFences[j]) >
                        maxdist
                    ) {
                        maxdist = allHFences[i] - allHFences[j];
                        hM.put(allHFences[i], maxdist);
                    }
                }
            }
        }
        for (int i = 0; i < allVFences.length; i++) {
            int maxdist = 0;
            for (int j = 0; j < allVFences.length; j++) {
                if (i != j) {
                    if (
                        Math.max(maxdist, allVFences[i] - allVFences[j]) >
                        maxdist
                    ) {
                        maxdist = allVFences[i] - allVFences[j];
                        vM.put(allVFences[i], maxdist);
                    }
                }
            }
        }
        int area = -1;
        for (Integer i : hM.values()) {
            if (vM.containsValue(i)) {
                area = Math.max(area, i * i);
            }
        }

        return area;
    }
}
