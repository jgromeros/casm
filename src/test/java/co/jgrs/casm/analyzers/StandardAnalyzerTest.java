package co.jgrs.casm.analyzers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.jgrs.casm.analyzers.StandardAnalyzer;

public class StandardAnalyzerTest {

    private static StandardAnalyzer analyzer;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        analyzer = new StandardAnalyzer();
    }

    @Test
    public void testAnalyzeQuery() {
        String newQuery = analyzer.analyzeQuery("Query investigating the supported features of current analyzer");
        assertEquals("Queri investig support featur current analyz", newQuery);
    }

}
