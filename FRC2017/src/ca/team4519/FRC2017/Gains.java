package ca.team4519.FRC2017;

public class Gains {

	public static final class Drive {
		private Drive() {}
			
		public static double TicksPerFoot = 0.0;
		
		public static double LeftDistPID_P = 0.0;
		public static double LeftDistPID_I = 0.0;
		public static double LeftDistPID_D = 0.0;
		public static double LeftDistPID_F = 0.0;
		public static double LeftVelPID_P = 0.0;
		public static double LeftVelPID_I = 0.0;
		public static double LeftVelPID_D = 0.0;
		public static double LeftVelPID_F = 0.0;
		
		public static double RightDistPID_P = 0.0;
		public static double RightDistPID_I = 0.0;
		public static double RightDistPID_D = 0.0;
		public static double RightDistPID_F = 0.0;
		public static double RightVelPID_P = 0.0;
		public static double RightVelPID_I = 0.0;
		public static double RightVelPID_D = 0.0;
		public static double RightVelPID_F = 0.0;
		
			
		
	}
	
	public static final class Flywheel {
		private Flywheel() {}
		
		public static double LeftTicksPerRev = 0.0;
		public static double RightTicksPerRev = 0.0;
		public static double RPM_Deadband = 0.0;
		
		public static double LeftRPM_P = 0.0;
		public static double LeftRPM_I = 0.0;
		public static double LeftRPM_D = 0.0;
		public static double LeftRPM_F = 0.0;
		
		public static double RightRPM_P = 0.0;
		public static double RightRPM_I = 0.0;
		public static double RightRPM_D = 0.0;
		public static double RightRPM_F = 0.0;
		
		
	}
	
}
