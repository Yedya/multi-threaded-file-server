package test.configuration.parser;

import main.client.configuration.Parser.ParseException;
import main.client.configuration.Parser.XMLParser;
import org.junit.jupiter.api.Test;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class XMLParserTest {
    @Test
    public void testValidXMLMapCreation() throws ParseException {
        XMLParser p = new XMLParser("config.xml");
        Map<String, String> map = p.parseToMap();
        assertTrue(map.size() == 4);
        assertTrue(map.containsKey("username"));
        assertTrue(map.get("username").equals("gmit-sw2016"));
    }

    @Test
    public void testMapDoesntGetCreatedForInvalidXMLFile() {
        XMLParser p = new XMLParser("src/test/configuration/parser/invalid-file.xml");

        try {
            p.parseToMap();
            fail("Successfully parsed invalid file");
        } catch (ParseException e) {

        }

    }
}