package co.jgrs.casm.filters;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import co.jgrs.casm.filters.StopFilter;

public class StopFilterTest {

    private static StopFilter stopFilter;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        stopFilter = new StopFilter();
    }

    @Test
    public void testFilter() {
        List<String> sentence = new ArrayList(Arrays.asList("this", "sentence", "has", "two", "literals", "with", "stop", "words"));
        List<String> filtered = stopFilter.filter(sentence);
        assertEquals(6, filtered.size());
    }

}
