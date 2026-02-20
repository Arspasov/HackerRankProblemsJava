import java.util.HashMap;
import java.util.List;

public class Subarrays_With_Given_Sum_And_Bounded_Maximum {
    public static void main(String[] args) {

    }

    public static long countSubarraysWithSumAndMaxAtMost(List<Integer> nums, long k, long M) {
        // Write your code here
        if (nums == null || nums.isEmpty()) {
            return 0L;
        }

        long ans = 0L;
        long prev = 0L;

        HashMap<Long, Long> freq = new HashMap<>();
        freq.put(0L, 1L);

        for (int i = 0; i < nums.size(); i++) {
            long x = nums.get(i);

            if (x > M) {
                prev = 0L;
                freq.clear();
                freq.put(0L, 1L);
                continue;
            }

            prev += x;

            long need = prev - k;
            Long count = freq.get(need);

            if (count != null) {
                ans += count;
            }

            freq.put(prev, freq.getOrDefault(prev, 0L) + 1L);
        }

        return ans;
    }
}
