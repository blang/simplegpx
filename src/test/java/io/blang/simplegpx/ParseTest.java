package io.blang.simplegpx;

import io.blang.simplegpx.entity.GPX;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParseTest {

    @Test
    public void testReadFixture() throws Exception {
        GPX gpx = GPXParser.parse(ParseTest.class.getClassLoader().getResourceAsStream("fixtures/simple.gpx"), ParseTest.class.getClassLoader().getResourceAsStream("gpx.xsd"));
        assertEquals(1, gpx.getWaypoints().size());
        assertEquals(2, gpx.getTracks().size());
        assertEquals(2, gpx.getTracks().get(0).getTrackSegments().size());
        assertEquals(3, gpx.getTracks().get(0).getTrackSegments().get(0).getTrackPoints().size());
        assertEquals(1, gpx.getRoutes().size());
        assertEquals(2, gpx.getRoutes().get(0).getRoutePoints().size());
    }
}
