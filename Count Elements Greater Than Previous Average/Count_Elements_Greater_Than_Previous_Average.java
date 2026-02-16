import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Count_Elements_Greater_Than_Previous_Average {
    public static void main(String[] args){

    }
}

class Result {

    /*
     * Complete the 'countResponseTimeRegressions' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY responseTimes as parameter.
     */
    public static int countResponseTimeRegressions(List<Integer> responseTimes) {
        int n = responseTimes.size();
        if (n <= 1) return 0;

        long sumPrev = responseTimes.get(0); // sum of elements [0..i-1]
        int result = 0;

        for (int i = 1; i < n; i++) {
            long cur = responseTimes.get(i);

            double avg = (double) sumPrev / i; // average of previous elements
            if (cur > avg) {
                result++;
            }

            sumPrev += cur;
        }

        return result;
    }
}