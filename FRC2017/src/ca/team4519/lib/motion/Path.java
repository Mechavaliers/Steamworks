package ca.team4519.lib.motion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Path {

	protected List<Waypoint> waypoints;
	protected List<PathPart> parts;
	protected Set<String> posNames_Passed;

	
	
	public Path(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
		parts = new ArrayList<PathPart>();
		for(int i = 0; i < waypoints.size() - 1; ++i) {
			parts.add(
					new PathPart(waypoints.get(i).position, waypoints.get(i+1).position, waypoints.get(i).speed));
		}
		
		if(waypoints.size() > 0) {
			Waypoint first_point = waypoints.get(0);
			if(first_point.posName.isPresent()) {
				posNames_Passed.add(first_point.posName.get());
			}
			waypoints.remove(0);
		}
		
		
	}
	
	public distanceAndAngle update(Coords pos) {

		double dist = 0.0;

		for(Iterator<PathPart> it = parts.iterator(); it.hasNext();) {
			PathPart part = it.next();
			PathPart.ClosestPoint closePoint = part.getClosePoint(pos);
			if(closePoint.index >= 0.99) {
				it.remove();
				if(waypoints.size() > 0 ) {
					Waypoint waypoint = waypoints.get(0);
					if(waypoint.posName.isPresent()) {
						posNames_Passed.add(waypoint.posName.get());
					}
					waypoints.remove(0);
				}
			}else{
				if(closePoint.index > 0.0) {
					part.newStart(closePoint.closePoint);
				}
				dist = closePoint.distance;
				if(it.hasNext()) {
					PathPart nextPart = it.next();
					PathPart.ClosestPoint nextClosePart = nextPart.getClosePoint(pos);
					if(nextClosePart.index > 0 && nextClosePart.index < 0.99 && nextClosePart.distance < dist) {
						nextPart.newStart(nextClosePart.closePoint);
						dist = nextClosePart.distance;
						parts.remove(0);
						if(waypoints.size() > 0) {
							Waypoint waypoint = waypoints.get(0);
							if (waypoint.posName.isPresent()){
								posNames_Passed.add(waypoint.posName.get());
							}
							waypoints.remove(0);
						}
					}
				}
				break;
			}
		}
		return new distanceAndAngle(dist, waypoints.get(0).getAngle());
	}
	
	
	public Set<String> getPositionsPassed() {
		return posNames_Passed;
	}
	
	public double getRemainingLength() {
		double length = 0.0;
		for (int i = 0; i < parts.size(); ++i) {
			length += parts.get(i).getLength();
		}
		return length;
	}
	
}
