package io.blang.simplegpx;

import io.blang.simplegpx.entity.GPX;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderXSDFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class ParseTest {

    @Test
    public void testReadFixture() throws Exception {
        GPX gpx = GPXParser.parse(ParseTest.class.getClassLoader().getResourceAsStream("gpx.xsd"));
        assertEquals(1, gpx.getWaypoints().size());
        assertEquals(2, gpx.getTracks().size());
        assertEquals(2, gpx.getTracks().get(0).getTrackSegments().size());
        assertEquals(3, gpx.getTracks().get(0).getTrackSegments().get(0).getTrackPoints().size());
        assertEquals(1, gpx.getRoutes().size());
        assertEquals(2, gpx.getRoutes().get(0).getRoutePoints().size());
    }
}
