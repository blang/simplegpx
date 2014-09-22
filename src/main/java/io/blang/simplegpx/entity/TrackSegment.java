package io.blang.simplegpx.entity;

import java.util.LinkedList;
import java.util.List;

public class TrackSegment {
    private LinkedList<Waypoint> trackPoints = new LinkedList<Waypoint>();

    public void addTrackPoint(Waypoint point) {
        trackPoints.add(point);
    }

    public void addTrackPoint(int position, Waypoint point) {
        trackPoints.add(position, point);
    }

    public List<Waypoint> getTrackPoints() {
        return trackPoints;
    }
}
