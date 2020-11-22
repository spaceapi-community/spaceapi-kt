package io.spaceapi;

import io.spaceapi.types.Status;
import org.junit.Assert;
import org.junit.Test;

public class ParserTestJava {
    private static final String minimalV14Data = "{\n" +
        "\"api_compatibility\": [\"14\"],\n" +
        "\"space\": \"Coredump\",\n" +
        "\"logo\": \"https://www.coredump.ch/wp-content/uploads/2016/11/logo.png\",\n" +
        "\"url\": \"https://www.coredump.ch/\",\n" +
        "\"location\": {\"lon\": 47.2251, \"lat\": 8.8339},\n" +
        "\"contact\": {}\n" +
    "}";

    /**
     * This test is here to test the Java API generated from Kotlin code.
     */
    @Test
    public void testApi() throws ParseError {
        final Status statusFromString = SpaceApiParser.parseString(minimalV14Data);
        Assert.assertEquals("Coredump", statusFromString.space);
    }
}
