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

        HashSet<Integer> hM = new HashSet<>();
        HashSet<Integer> vM = new HashSet<>();

        for (int i = 0; i < allHFences.length; i++) {
            for (int j = i + 1; j < allHFences.length; j++) {
                hM.add(allHFences[j] - allHFences[i]);
            }
        }

        int maxSide = -1;

        for (int i = 0; i < allVFences.length; i++) {
            for (int j = i + 1; j < allVFences.length; j++) {
                int d = allVFences[j] - allVFences[i];
                if (hM.contains(d)) {
                    maxSide = Math.max(maxSide, d);
                }
            }
        }

        if (maxSide == -1) {
            return -1;
        }

        long area = (long) maxSide * maxSide;
        return (int) (area % 1000000007);
    }
}
