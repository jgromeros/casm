package co.jgrs.casm.filters;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import co.jgrs.casm.filters.Tokenizer;

public class TokenizerTest {

    @Test
    public void testNormalSentenceTokenize() {
        List<String> tokens = Tokenizer.tokenize("This is my first attempt to tokenize");
        assertEquals(7, tokens.size());
    }

    @Test
    public void testDoubleWhitespaceTokenize() {
        List<String> tokens = Tokenizer.tokenize("This is  my  first attempt to tokenize");
        assertEquals(7, tokens.size());
    }

    @Test
    public void testTabTokenize() {
        List<String> tokens = Tokenizer.tokenize("This is my    first   attempt to tokenize");
        assertEquals(7, tokens.size());
    }

    @Test
    public void testDoubleTabTokenize() {
        List<String> tokens = Tokenizer.tokenize("This is my        first   attempt to tokenize");
        assertEquals(7, tokens.size());
    }

    @Test
    public void testInitialWhitespaceTokenize() {
        List<String> tokens = Tokenizer.tokenize(" This is my    first   attempt to tokenize");
        assertEquals(7, tokens.size());
    }

    @Test
    public void testFinalWhitespaceTokenize() {
        List<String> tokens = Tokenizer.tokenize("This is my    first   attempt to tokenize ");
        assertEquals(7, tokens.size());
    }

}
