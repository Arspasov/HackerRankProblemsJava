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

class Result {

    /*
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isValid(String s) {
        Map <Character,Integer> storage = new HashMap<>();

        for(int i =0;i<s.length(); i++){
            if(storage.containsKey(s.charAt(i))){
                int value = storage.get(s.charAt(i));
                storage.put(s.charAt(i),value + 1);
            }else{
                storage.put(s.charAt(i) , 1);
            }
        }

        List<Integer> mapValues  = new ArrayList<>(storage.values());
        Collections.sort(mapValues);

        int min =mapValues.get(0);
        int max= mapValues.get(mapValues.size() - 1);
        if(min == max)
        {
            return "YES";
        }
        int minCount = 0;
        int maxCount = 0;

        for(int value : mapValues)
        {
            if(min == value)
            {
                minCount++;
            }
            if(max == value)
            {
                maxCount++;
            }
        }

        if(max - min > 1 )
        {
            if(minCount != 1)
            {
                return "NO";
            }
        }
        return ((maxCount + minCount == mapValues.size() ) && (maxCount < 2 || minCount <2 )  ? "YES": "NO"  );
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}