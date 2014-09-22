package io.blang.simplegpx;

import io.blang.simplegpx.entity.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderXSDFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GPXParser {
    public static GPX parse(InputStream inputStream) throws JDOMException, IOException, ParseException {
        XMLReaderXSDFactory schemafac = new XMLReaderXSDFactory(GPXParser.class.getClassLoader().getResource("gpx.xsd"));
        SAXBuilder sax = new SAXBuilder(schemafac);
        Document doc = sax.build(GPXParser.class.getClassLoader().getResourceAsStream("fixtures/simple.gpx"));
        Element root = doc.getRootElement();
        if (root.getName() != GPXConstants.NODE_GPX) {
            throw new ParseException("Could not find valid root element");
        }

        GPX gpx = new GPX();

        parseTracks(gpx, root.getChildren(GPXConstants.NODE_TRACK, root.getNamespace()), root.getNamespace());

        parseWaypoints(gpx, root.getChildren(GPXConstants.NODE_WAYPOINT, root.getNamespace()), root.getNamespace());

        parseRoutes(gpx, root.getChildren(GPXConstants.NODE_ROUTE, root.getNamespace()), root.getNamespace());

        return gpx;
    }

    private static void parseWaypoints(GPX gpx, List<Element> nodesWaypoint, Namespace namespace) throws ParseException, DataConversionException {
        for (Element nodeWaypoint : nodesWaypoint) {
            gpx.addWaypoint(parseWaypoint(nodeWaypoint, namespace));
        }
    }

    private static void parseRoutes(GPX gpx, List<Element> nodesRoute, Namespace namespace) throws ParseException, DataConversionException {
        for (Element nodeRoute : nodesRoute) {
            Route route = new Route();
            List<Element> nodesRoutePoint = nodeRoute.getChildren(GPXConstants.NODE_ROUTE_POINT, namespace);
            for (Element nodeRoutePoint : nodesRoutePoint) {
                route.addRoutePoint(parseWaypoint(nodeRoutePoint, namespace));
            }
            gpx.addRoute(route);
        }
    }

    private static void parseTracks(GPX gpx, List<Element> nodesTrack, Namespace namespace) throws ParseException, DataConversionException {
        for (Element nodeTrack : nodesTrack) {
            Track track = new Track();
            List<Element> nodesTrackSeg = nodeTrack.getChildren(GPXConstants.NODE_TRACK_SEGMENT, namespace);
            for (Element nodeTrackSeg : nodesTrackSeg) {
                TrackSegment trackSegment = new TrackSegment();
                List<Element> nodesTrackPoint = nodeTrackSeg.getChildren(GPXConstants.NODE_TRACK_POINT, namespace);
                for (Element nodeTrackPoint : nodesTrackPoint) {
                    trackSegment.addTrackPoint(parseWaypoint(nodeTrackPoint, namespace));
                }
                track.addTrackSegment(trackSegment);
            }
            gpx.addTrack(track);
        }
    }

    private static Waypoint parseWaypoint(Element nodeWaypoint, Namespace namespace) throws ParseException, DataConversionException {
        Attribute attrLat = nodeWaypoint.getAttribute(GPXConstants.ATT_WAYPOINT_LATITUDE);
        if (attrLat == null) {
            throw new ParseException("Trackpoint has no latitude attribute");
        }

        double lat = attrLat.getDoubleValue();

        Attribute attrLon = nodeWaypoint.getAttribute(GPXConstants.ATT_WAYPOINT_LONGITUDE);
        if (attrLon == null) {
            throw new ParseException("Trackpoint has no longitude attribute");
        }

        double lon = attrLon.getDoubleValue();
        return new Waypoint(lat, lon);
    }


}
