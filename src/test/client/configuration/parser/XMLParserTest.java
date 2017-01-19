package test.client.configuration.parser;

import main.client.configuration.Configuration;
import main.client.configuration.Parser.ParseException;
import main.client.configuration.Parser.XMLParser;
import org.junit.jupiter.api.Test;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class XMLParserTest {
    @Test
    public void testXMLParserReturnsConfigurationObject() throws ParseException {
        XMLParser p = new XMLParser("src/test/client/assets/config.xml");
        Configuration configuration = p.parse();
        assertTrue(configuration != null);
    }

    @Test
    public void testConfigurationDoesntGetCreatedForInvalidXMLFile() {
        XMLParser p = new XMLParser("src/test/configuration/parser/invalid-file.xml");
        try {
            p.parse();
            fail("Successfully parsed invalid file");
        } catch (ParseException e) {}
    }

    @Test
    public void testConfigurationHasCorrectValues() throws ParseException{
        XMLParser p = new XMLParser("src/test/client/assets/config.xml");
        Configuration configuration = p.parse();
        assertTrue(configuration.getServerHost().equals("127.0.0.1"));
        assertTrue(configuration.getDownloadDirectory().equals("./downloads/"));
        assertTrue(configuration.getServerPort() == 7777);
        assertTrue(configuration.getUsername().equals("gmit-sw2016"));
    }
}
