package co.jgrs.casm.filters;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import co.jgrs.casm.filters.StemmerFilter;

public class StemmerFilterTest {

    private static StemmerFilter stemmer;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        stemmer = new StemmerFilter("english");
    }

    @Test
    public void testFilter() {
        List<String> sentence = new ArrayList(Arrays.asList("This", "sentence", "has", "several", "words",
                "designed", "to", "test", "capacity", "of", "the", "snowball", "stemmer", "for", "stemming"));
        List<String> stems = stemmer.filter(sentence);
        for (String s : stems) {
            System.out.println(s);
        }
        assertEquals("stem", stems.get(14));
    }

}
