package knapsack;
import java.util.*;
import java.util.stream.*;


public class Problem {

    private final List<Item> items;

    public Problem(int n, long seed, int lowerBound, int upperBound) {

        Random rnd = (seed == 0) ? new Random()
                : new Random(seed);

        List<Item> tmp = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            int value  = lowerBound + rnd.nextInt(upperBound - lowerBound + 1);
            int weight = lowerBound + rnd.nextInt(upperBound - lowerBound + 1);
            tmp.add(new Item(value, weight));
        }
        items = List.copyOf(tmp);
    }


    //Algorytm aproksymacyjny Dantzig
    public Result solve(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");

    // Sortowanie indeksów po malejącym współczynniku v/w
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) order.add(i);
        order.sort((a, b) -> {
            Item ia = items.get(a), ib = items.get(b);
            return Long.compare( (long)ib.value()*ia.weight(),
                    (long)ia.value()*ib.weight() );
        });

//        System.out.println("Sorted index list: ");
//        System.out.println(order);

        Result res = new Result();
        int remaining = capacity;

    // Zachłannie dokładamy przedmiot o największym v/w aż braknie miejsca
        for (int idx : order) {
            Item it = items.get(idx);
            while (it.weight() <= remaining) {
                res.add(idx, it);
                remaining -= it.weight();
            }
            if (remaining == 0) break;            // plecak zapełniony
        }
        return res;
    }
    
    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Item types:\n");
        int i = 1;
        for (Item it : items) {
            sb.append("%2d) value=%d, weight=%d%n".formatted(i++, it.value(), it.weight()));
        }
        return sb.toString();
    }
}
