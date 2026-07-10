import java.util.*;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();

        for (int[] b : buildings) {
            // building start: height negative rakho (marker ke liye)
            events.add(new int[]{b[0], -b[2]});
            // building end: height positive rakho
            events.add(new int[]{b[1], b[2]});
        }

        // x ke hisaab se sort, aur same x pe starts (negative) pehle, phir taller pehle
        events.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        List<List<Integer>> result = new ArrayList<>();

        // heights ko count ke sath store karega (multiset jaisa), max height sabse upar
        TreeMap<Integer, Integer> heights = new TreeMap<>(Collections.reverseOrder());
        heights.put(0, 1); // ground level

        int prevMax = 0;

        for (int[] event : events) {
            int x = event[0];
            int h = event[1];

            if (h < 0) {
                // building start -> height add karo
                heights.merge(-h, 1, Integer::sum);
            } else {
                // building end -> height remove karo
                heights.merge(h, -1, (a, b) -> (a + b == 0) ? null : a + b);
            }

            int currMax = heights.firstKey();

            if (currMax != prevMax) {
                result.add(Arrays.asList(x, currMax));
                prevMax = currMax;
            }
        }

        return result;
    }
}