import java.io.*;
import java.util.*;

public class Max_Area_Under_Histogram_After_Removing_One_Area {
    class Result {
        private static final class DSU {
            final int[] parent;
            final int[] size;
            final int[] minIdx;
            final int[] maxIdx;

            DSU(int n) {
                parent = new int[n];
                size = new int[n];
                minIdx = new int[n];
                maxIdx = new int[n];
                Arrays.fill(parent, -1);
            }

            void makeSet(int x) {
                parent[x] = x;
                size[x] = 1;
                minIdx[x] = x;
                maxIdx[x] = x;
            }

            int find(int x) {
                int p = parent[x];
                while (p != parent[p]) {
                    parent[p] = parent[parent[p]];
                    p = parent[p];
                }
                int cur = x;
                while (parent[cur] != p) {
                    int nxt = parent[cur];
                    parent[cur] = p;
                    cur = nxt;
                }
                return p;
            }

            void union(int a, int b) {
                int ra = find(a), rb = find(b);
                if (ra == rb) return;

                if (size[ra] < size[rb]) {
                    int tmp = ra;
                    ra = rb;
                    rb = tmp;
                }
                parent[rb] = ra;
                size[ra] += size[rb];
                minIdx[ra] = Math.min(minIdx[ra], minIdx[rb]);
                maxIdx[ra] = Math.max(maxIdx[ra], maxIdx[rb]);
            }
        }

        private static final class BridgeEntry {
            final long value;
            final int pos;
            final int version;

            BridgeEntry(long value, int pos, int version) {
                this.value = value;
                this.pos = pos;
                this.version = version;
            }
        }

        private static void updateBridge(
                int p,
                boolean[] active,
                DSU dsu,
                long[] bridgeVal,
                int[] bridgeVer,
                PriorityQueue<BridgeEntry> bridgeHeap
        ) {
            int n = active.length;
            if (p < 0 || p >= n) return;
            if (active[p]) return;

            long left = 0, right = 0;
            if (p - 1 >= 0 && active[p - 1]) left = dsu.size[dsu.find(p - 1)];
            if (p + 1 < n && active[p + 1]) right = dsu.size[dsu.find(p + 1)];

            bridgeVal[p] = left + right;

            int ver = ++bridgeVer[p];
            bridgeHeap.add(new BridgeEntry(bridgeVal[p], p, ver));
        }

        private static long bestBridge(
                boolean[] active,
                long[] bridgeVal,
                int[] bridgeVer,
                PriorityQueue<BridgeEntry> bridgeHeap
        ) {
            while (!bridgeHeap.isEmpty()) {
                BridgeEntry e = bridgeHeap.peek();
                if (active[e.pos] || bridgeVer[e.pos] != e.version || bridgeVal[e.pos] != e.value) {
                    bridgeHeap.poll(); // stale
                    continue;
                }
                return e.value;
            }
            return 0L;
        }

        public static long computeMaxRectangleAreaWithOneRemoval(List<Integer> heights) {
            int n = heights.size();
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) arr[i] = heights.get(i);
            return computeMaxRectangleAreaWithOneRemoval(arr);
        }

        public static long computeMaxRectangleAreaWithOneRemoval(long[] heights) {
            int n = heights.length;

            if (n == 0) return -1L;
            if (n == 1) return heights[0];

            final int SHIFT = 20;
            final long INDEX_MASK = (1L << SHIFT) - 1;

            long[] packed = new long[n];
            for (int i = 0; i < n; i++) {
                packed[i] = (heights[i] << SHIFT) | (long) i;
            }
            Arrays.sort(packed);

            boolean[] active = new boolean[n];
            DSU dsu = new DSU(n);

            long[] bridgeVal = new long[n];
            int[] bridgeVer = new int[n];

            PriorityQueue<BridgeEntry> bridgeHeap =
                    new PriorityQueue<>((a, b) -> Long.compare(b.value, a.value));

            long bestArea = 0L;
            long maxComponentSize = 0L;

            int ptr = n - 1;
            while (ptr >= 0) {
                long H = packed[ptr] >>> SHIFT;

                while (ptr >= 0 && (packed[ptr] >>> SHIFT) == H) {
                    int idx = (int) (packed[ptr] & INDEX_MASK);
                    ptr--;

                    active[idx] = true;
                    dsu.makeSet(idx);

                    if (idx - 1 >= 0 && active[idx - 1]) dsu.union(idx, idx - 1);
                    if (idx + 1 < n && active[idx + 1]) dsu.union(idx, idx + 1);

                    int root = dsu.find(idx);
                    maxComponentSize = Math.max(maxComponentSize, (long) dsu.size[root]);

                    updateBridge(idx - 1, active, dsu, bridgeVal, bridgeVer, bridgeHeap);
                    updateBridge(idx + 1, active, dsu, bridgeVal, bridgeVer, bridgeHeap);

                    root = dsu.find(idx);
                    updateBridge(dsu.minIdx[root] - 1, active, dsu, bridgeVal, bridgeVer, bridgeHeap);
                    updateBridge(dsu.maxIdx[root] + 1, active, dsu, bridgeVal, bridgeVer, bridgeHeap);
                }

                long width = Math.max(maxComponentSize, bestBridge(active, bridgeVal, bridgeVer, bridgeHeap));
                bestArea = Math.max(bestArea, H * width);
            }

            return bestArea;
        }
    }
}