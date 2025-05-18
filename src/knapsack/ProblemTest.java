package knapsack;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ProblemTest {

    private Problem sampleProblem() {
        return new Problem(10, 1, 1, 10);
    }



    //Sprawdzenie, czy jeśli co najmniej jeden przedmiot spełnia ograniczenia, to zwrócono co naj-
    //mniej jeden element.
    @Test
    void atLeastOneItemFits() {
        Problem p   = sampleProblem();
        Result  r   = p.solve(3);
        assertFalse(r.getCounts().isEmpty(),
                "There should be at least one item");
    }




  //Sprawdzenie, czy jeśli żaden przedmiot nie spełnia ograniczeń, to zwrócono puste rozwiązanie.
    @Test
    void noneFitsReturnEmpty() {
        Problem p = sampleProblem();
        int minW  = p.getItems().stream().mapToInt(Item::weight).min().orElseThrow();
        Result r  = p.solve(minW - 1);                // pojemność mniejsza niż najlżejszy przedmiot
        assertEquals(0, r.getCounts().size(),
                "Map should be empty when capacity < min(weight)");
    }




    // Sprawdzenie, czy waga i wartość wszystkich przedmiotów z listy mieści się w założonym prze-
    //dziale.
    @Test
    void generatedItemsWithinRange() {
        Problem p = sampleProblem();
        assertAll("Range 1-10",
                p.getItems().stream().map(it ->
                        () -> assertTrue(
                                it.value()  >= 1 && it.value()  <= 10 &&
                                        it.weight() >= 1 && it.weight() <= 10,
                                () -> "Out of range: " + it)));
    }




    // Sprawdzenie poprawności wyniku (sumy wag i wartości w plecaku) dla konkretnej instancji.
    @Test
    void aggregationCorrect() {
        Problem p = new Problem(3, 2, 5, 10);
        Result  r = p.solve(20);
        int w = 0, v = 0;
        for (Map.Entry<Integer,Integer> e : r.getCounts().entrySet()) {
            Item it = p.getItems().get(e.getKey());
            int k   = e.getValue();
            w += it.weight() * k;
            v += it.value()  * k;
        }
        assertEquals(w, r.getTotalWeight(), "Błędna suma wag");
        assertEquals(v, r.getTotalValue(),  "Błędna suma wartości");
    }
}

