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
     * Complete the 'checkMagazine' function below.
     *
     * The function accepts following parameters:
     *  1. STRING_ARRAY magazine
     *  2. STRING_ARRAY note
     */

    public static void checkMagazine(List<String> magazine, List<String> note) {
        // Write your code here
        Map<String,Integer> library = new HashMap<>();
        for(int i = 0; i<magazine.size(); i++){
            if(library.containsKey(magazine.get(i))){
                int currentValue = library.get(magazine.get(i));
                library.put(magazine.get(i),currentValue+1);
            }else{
                library.put(magazine.get(i),1);
            }
        }


        for(int i = 0; i<note.size();i++){
            if(library.containsKey(note.get(i))){
                if(library.get(note.get(i))!=0){
                    library.put(note.get(i),library.get(note.get(i))-1);
                }else{
                    library.remove(note.get(i));
                    System.out.print("No");
                    return;
                }
            }else{
                System.out.print("No");
                return;
            }
        }
        System.out.print("Yes");
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        List<String> magazine = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .collect(toList());

        List<String> note = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .collect(toList());

        Result.checkMagazine(magazine, note);

        bufferedReader.close();
    }
}