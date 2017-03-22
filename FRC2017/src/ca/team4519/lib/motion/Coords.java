package ca.team4519.lib.motion;

import java.text.DecimalFormat;

public class Coords {

	double x_;
	double y_;
	
	public Coords() {
		x_ = 0;
		y_ = 0;
	}
	
	public Coords(double x, double y) {
		x_ = x;
		y_ = y;
	}
	
	public Coords(Coords pos) {
		x_ = pos.x_;
		y_ = pos.y_;
	}
	
	public double getHypot() {
		return Math.hypot(x_, y_);
	}
	
	public double getAngle() {
		return Math.toDegrees(Math.atan2(y_, x_));
	}
	
	public Coords inverse() {
		return new Coords(-x_, -y_);
	}
	
	public Coords translate(Coords otherPos){
		return new Coords(x_ + otherPos.x_, y_ + otherPos.y_);
	}
	
	public double getX() {
		return x_;
	}
	
	public double getY() {
		return y_;
	}

	public Coords interpolate(Coords point, double x) {
		if(x <= 0) {
			return new Coords(this);
		}else if(x >= 1) {
			return new Coords(point);
		}
		return extrapolate(point, x);
	}
	
	public Coords extrapolate(Coords point, double x) {
		return new Coords(x * (point.x_ - x_) + x_, x * (point.y_ - y_) + y_);
	}
	
	@Override
	public String toString() {
		final DecimalFormat format = new DecimalFormat("#0.000");
		return "(" + format.format(x_) + "," + format.format(y_) + ")";
	}
	
}
