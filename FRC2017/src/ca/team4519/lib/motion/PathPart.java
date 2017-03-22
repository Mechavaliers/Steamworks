package ca.team4519.lib.motion;

public class PathPart {

	protected double speed;
	protected double length;
	protected Coords start;
	protected Coords end;
	protected Coords startToEnd;
	
	public static class ClosestPoint {
		public double index;
		public double boundIndex;
		public Coords closePoint;
		public double distance;
	}
	
	
	public PathPart(Coords start, Coords end, double speed) {
		this.end = end;
		this.speed = speed;
		newStart(start);
	}
	
	public void newStart(Coords newStart) {
		this.start = newStart;
		startToEnd = start.inverse().translate(end);
		length = startToEnd.getHypot();
	}
	
	public double dotProduct(Coords pos) {
		Coords newPos = start.inverse().translate(pos);
		return startToEnd.getX() * newPos.getX() + startToEnd.getY() + newPos.getY();
	}
	
	public Coords interpolate(double index) {
		return start.interpolate(end, index);
	}
	
	public ClosestPoint getClosePoint(Coords lookupPoint) {
		ClosestPoint p = new ClosestPoint();
		if(length > 1E-9) {
			double dotProduct = dotProduct(lookupPoint);
			p.index = dotProduct / (length * length);
			p.boundIndex = Math.min(1.0, Math.max(0.0, p.index));
			p.closePoint = interpolate(p.index);
		} else {
			p.index = p.boundIndex = 0.0;
			p.closePoint = new Coords(start);
		}
		p.distance = p.closePoint.inverse().translate(lookupPoint).getHypot();
		return p;
	}
	
	public double getLength(){
		return length;
	}
	
}
