import java.util.Arrays;

public class MaximizeAreaOfSquareHole {

    public static void main(String[] args) {
        int[] hBars = { 2, 4, 3 };
        int[] vBars = { 4, 6, 7, 12, 10, 13, 2 };
        int ans = maximizeSquareHoleArea(3, 13, hBars, vBars);
        System.out.println(ans);
    }

    public static int maximizeSquareHoleArea(
        int n,
        int m,
        int[] hBars,
        int[] vBars
    ) {
        //int commonNums = 0;

        Arrays.sort(hBars);
        Arrays.sort(vBars);

        int hcontiguous = 0;
        int vcontiguous = 0;
        int hmax = 0;
        int vmax = 0;

        for (int i = 0; i < hBars.length - 1; i++) {
            if (hBars[i] == hBars[i + 1] - 1) {
                hcontiguous++;
            } else {
                hmax = Math.max(hcontiguous, hmax);
                hcontiguous = 0;
            }
        }

        hmax = Math.max(hmax, hcontiguous);

        for (int i = 0; i < vBars.length - 1; i++) {
            if (vBars[i] == vBars[i + 1] - 1) {
                vcontiguous++;
            } else {
                vmax = Math.max(vcontiguous, vmax);
                vcontiguous = 0;
            }
        }

        vmax = Math.max(vmax, vcontiguous);

        int side = Math.min(hmax + 1, vmax + 1) + 1;

        return side * side;
    }
}
