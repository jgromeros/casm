package co.jgrs.casm.analyzers;

import java.util.ArrayList;
import java.util.List;

import co.jgrs.casm.filters.AnalyzerFilter;
import co.jgrs.casm.filters.StemmerFilter;
import co.jgrs.casm.filters.StopFilter;
import co.jgrs.casm.filters.Tokenizer;

public class StandardAnalyzer {

    private List<AnalyzerFilter> filters;

    public StandardAnalyzer() {
        filters = new ArrayList<AnalyzerFilter>();
        filters.add(new StopFilter());
        filters.add(new StemmerFilter("english"));
    }

    public String analyzeQuery(String originalQuery) {
        List<String> tokens = Tokenizer.tokenize(originalQuery);
        StringBuilder newQuery = new StringBuilder();
        for (AnalyzerFilter filter : filters) {
            tokens = filter.filter(tokens);
        }
        for (String token : tokens) {
            newQuery = newQuery.append(token).append(" ");
        }
        return newQuery.toString().trim();
    }

}
