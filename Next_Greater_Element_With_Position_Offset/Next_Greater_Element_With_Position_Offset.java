import java.util.*;

public class Next_Greater_Element_With_Position_Offset {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> findNextGreaterElementsWithDistance(List<Integer> readings) {
        if (readings == null || readings.isEmpty()) return new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>(readings.size());
        for (int i = 0; i < readings.size(); i++) {
            result.add(Arrays.asList(-1, -1));
        }

        PriorityQueue<Entry> unvisited =
                new PriorityQueue<>(Comparator.comparingInt(Entry::getValue));

        unvisited.add(new Entry(0, readings.get(0)));

        for (int i = 1; i < readings.size(); i++) {
            int value = readings.get(i);

            while (!unvisited.isEmpty() && unvisited.peek().getValue() < value) {
                Entry e = unvisited.poll();
                result.set(e.getIndex(), Arrays.asList(value, i - e.getIndex()));
            }

            unvisited.add(new Entry(i, value));
        }

        return result;
    }

    static class Entry {
        int index, value;

        Entry(int index, int value) {
            this.index = index;
            this.value = value;
        }

        int getIndex() {
            return index;
        }

        int getValue() {
            return value;
        }
    }
}