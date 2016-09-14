package co.jgrs.casm.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StopFilter implements AnalyzerFilter {

    private static final String ENGLISH_STOP_FILE = "src/main/resources/englishStopWords.txt";
    private static List<String> englishStopWordsList;

    public StopFilter() {
        try {
            loadStopWordList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStopWordList() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ENGLISH_STOP_FILE))) {
            englishStopWordsList = new ArrayList<String>();
            String line = br.readLine();
            while (line != null) {
                englishStopWordsList.add(line);
                line = br.readLine();
            }
        }
    }

    public List<String> filter(List<String> sentence) {
        List<String> filtered = new ArrayList<String>();
        for (String s : sentence) {
            if (!englishStopWordsList.contains(s.toLowerCase())) {
                filtered.add(s);
            }
        }
        return filtered;
    }

}
