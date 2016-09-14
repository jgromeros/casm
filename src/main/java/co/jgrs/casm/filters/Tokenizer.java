package co.jgrs.casm.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {

    public static List<String> tokenize(String sentence) {
        sentence = sentence.trim();
        String[] splitted = sentence.split(" +");
        List<String> tokens = new ArrayList<String>(Arrays.asList(splitted));
        return tokens;
    }

}
