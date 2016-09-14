package co.jgrs.casm.filters;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.SnowballStemmer;

public class StemmerFilter implements AnalyzerFilter {

    private SnowballStemmer stemmer;

    public StemmerFilter(String algorithm) {
        try {
            Class<?> stemClass = Class.forName("org.tartarus.snowball.ext." + algorithm + "Stemmer");
            stemmer = (SnowballStemmer) stemClass.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> filter(List<String> sentence) {
        List<String> stems = new ArrayList<String>();
        for (String s : sentence) {
            stemmer.setCurrent(s);
            stemmer.stem();
            stems.add(stemmer.getCurrent());
        }
        return stems;
    }

}
