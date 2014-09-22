package io.blang.simplegpx.entity;

import java.util.LinkedList;
import java.util.List;

public class GPX {
    private LinkedList<Waypoint> waypoints = new LinkedList<Waypoint>();
    private LinkedList<Route> routes = new LinkedList<Route>();
    private LinkedList<Track> tracks = new LinkedList<Track>();

    public void addWaypoint(Waypoint waypoint) {
        waypoints.add(waypoint);
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
