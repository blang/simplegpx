package io.blang.simplegpx.entity;

import java.util.LinkedList;
import java.util.List;

public class Track {
    private LinkedList<TrackSegment> trackSegments = new LinkedList<TrackSegment>();

    public void addTrackSegment(TrackSegment segment) {
        trackSegments.add(segment);
    }

    public void addTrackSegment(int position, TrackSegment segment) {
        trackSegments.add(position, segment);
    }

    public List<TrackSegment> getTrackSegments() {
        return trackSegments;
    }
}
