import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Merge_And_Sort_Intervals {
    public static void main(String[] args) {
        List<List<Integer>> list = List.of(
                List.of(5, 9), List.of(7, 10), List.of(1, 3),
                List.of(2, 4), List.of(11, 15)
        );

        mergeHighDefinitionIntervals(list);

        System.out.println("Hello");
    }

    public static List<List<Integer>> mergeHighDefinitionIntervals(List<List<Integer>> intervals) {
        if(intervals.size() <= 1){
            return intervals;
        }

        List<List<Integer>> list = new ArrayList<>(intervals);
        list.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return Integer.compare(o1.get(0), o2.get(0));
            }
        });

        List<List<Integer>> result = new ArrayList<>();
        int start = list.get(0).get(0);
        int end = list.get(0).get(1);

        for(int i = 1; i < list.size(); i++){
            int currStart = list.get(i).get(0);
            int currEnd = list.get(i).get(1);

            if(currStart <= end){
                end = Math.max(currEnd, end);
            }else {
                result.add(List.of(start,end));
                start = currStart;
                end = currEnd;
            }
        }

        result.add(List.of(start, end));
        return result;
    }
}
