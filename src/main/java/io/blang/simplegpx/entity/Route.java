package io.blang.simplegpx.entity;

import java.util.LinkedList;
import java.util.List;

public class Route {
    private LinkedList<Waypoint> routePoints = new LinkedList<Waypoint>();

    public void addRoutePoint(Waypoint point) {
        routePoints.add(point);
    }

    public void addRoutePoint(int position, Waypoint point) {
        routePoints.add(position, point);
    }

    public List<Waypoint> getRoutePoints() {
        return routePoints;
    }
}
