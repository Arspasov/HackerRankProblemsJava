import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        long triplets = 0;
        HashMap<Long, Long> helper = new HashMap<>();
        HashMap<Long, Long> combinations = new HashMap<>();

        for(int i = 0; i < arr.size(); ++i) {
            if(combinations.containsKey(arr.get(i))) {
                triplets+=combinations.get(arr.get(i));
            }

            if(helper.containsKey(arr.get(i))) {
                combinations.put(arr.get(i) * r, combinations.getOrDefault(arr.get(i) * r, 0L)+helper.get(arr.get(i)));
            }

            helper.put(arr.get(i) * r, helper.getOrDefault(arr.get(i) * r, 0L)+1);
        }
        return triplets;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}