package knapsack;
import java.util.LinkedHashMap;
import java.util.Map;

public class Result {
    /** ile razy dany indeks przedmiotu trafił do plecaka */
    private final Map<Integer, Integer> counts = new LinkedHashMap<>();
    private int totalValue = 0;
    private int totalWeight = 0;

    void add(int index, Item item) {              // wywoływane z Problem.solve
        counts.merge(index, 1, Integer::sum);
        totalValue  += item.value();
        totalWeight += item.weight();
    }

    public int getTotalValue()  { return totalValue; }
    public int getTotalWeight() { return totalWeight; }
    public Map<Integer, Integer> getCounts() { return counts; }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        counts.forEach((idx, k) ->
                sb.append("No: ").append(idx)
                        .append(" × ").append(k).append('\n'));
        sb.append("Weight: ").append(totalWeight).append('\n');
        sb.append("Value:  ").append(totalValue);
        return sb.toString();
    }

}
