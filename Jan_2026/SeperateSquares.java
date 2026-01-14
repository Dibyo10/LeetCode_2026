import java.util.*;

public class SeperateSquares {

    static class SegmentTree {

        private final int[] count;
        private final int[] covered;
        private final int[] xs;
        private final int n;

        SegmentTree(int[] xs) {
            this.xs = xs;
            this.n = xs.length - 1;
            this.count = new int[4 * n];
            this.covered = new int[4 * n];
        }

        void update(int ql, int qr, int delta) {
            update(ql, qr, delta, 0, n - 1, 0);
        }

        int queryCoveredLength() {
            return covered[0];
        }

        private void update(int ql, int qr, int delta, int l, int r, int pos) {
            if (xs[r + 1] <= ql || xs[l] >= qr) {
                return;
            }

            if (ql <= xs[l] && xs[r + 1] <= qr) {
                count[pos] += delta;
            } else {
                int mid = (l + r) / 2;
                update(ql, qr, delta, l, mid, pos * 2 + 1);
                update(ql, qr, delta, mid + 1, r, pos * 2 + 2);
            }

            if (count[pos] > 0) {
                covered[pos] = xs[r + 1] - xs[l];
            } else {
                covered[pos] = (l == r)
                    ? 0
                    : covered[pos * 2 + 1] + covered[pos * 2 + 2];
            }
        }
    }

    public static double separateSquares(int[][] squares) {
        List<int[]> events = new ArrayList<>();
        Set<Integer> xsSet = new TreeSet<>();

        for (int[] sq : squares) {
            int x = sq[0],
                y = sq[1],
                len = sq[2];
            int xr = x + len;
            events.add(new int[] { y, +1, x, xr });
            events.add(new int[] { y + len, -1, x, xr });
            xsSet.add(x);
            xsSet.add(xr);
        }

        events.sort(Comparator.comparingInt(a -> a[0]));
        int[] xs = xsSet
            .stream()
            .mapToInt(i -> i)
            .toArray();
        SegmentTree seg = new SegmentTree(xs);

        List<Long> prefixArea = new ArrayList<>();
        List<Integer> widths = new ArrayList<>();

        long totalArea = 0;
        int prevY = events.get(0)[0];

        for (int[] e : events) {
            int y = e[0];
            int coveredWidth = seg.queryCoveredLength();
            totalArea += (long) coveredWidth * (y - prevY);

            seg.update(e[2], e[3], e[1]);

            prefixArea.add(totalArea);
            widths.add(seg.queryCoveredLength());
            prevY = y;
        }

        long target = (totalArea + 1) / 2;
        int idx = lowerBound(prefixArea, target);

        long areaBefore = prefixArea.get(idx);
        int width = widths.get(idx);
        int baseY = events.get(idx)[0];

        return baseY + (target - areaBefore) / (double) width;
    }

    private static int lowerBound(List<Long> arr, long target) {
        int l = 0,
            r = arr.size() - 1,
            ans = r;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr.get(m) >= target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
