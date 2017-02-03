package ca.team4519.FRC2017;

public class Gains {

	public static final class Drive {
		private Drive() {}
			
		public static double CONTROL_LOOP_TIME = 0.005;
		
		public static double ROBOT_MAX_VELOCITY= 0.0;
		public static double ROBOT_MAX_ROTATIONAL_VELOCITY = 0.0;
		public static double ROBOT_MAX_ACCELERATION = 0.0;
		public static double ROBOT_MAX_ROTATIONAL_ACCELERATION = 0.0;
		public static double Wheelbase_Width = 0.0;
		public static double EncoderTicksPerRev = 256.0;
		public static double WheelSize_Inches = 4.0;
		
		public static double Dist_P = 0.0;
		public static double Dist_I = 0.0;
		public static double Dist_D = 0.0;
		public static double Dist_V = 0.0;
		public static double Dist_A = 0.0;
		public static double DistTurn_P = 0.0;
		public static double DistTurn_I = 0.0;
		public static double DistTurn_D = 0.0;
		public static double Dist_Tollerance = 0.0;

		public static double Turn_P = 0.0;
		public static double Turn_I = 0.0;
		public static double Turn_D = 0.0;
		public static double Turn_V = 0.0;
		public static double Turn_A = 0.0;
		public static double Turn_Tollerance = 0.0;
		
	}
	
	public static final class Flywheel {
		private Flywheel() {}
		
		public static double RPM_TARGET_KEY = 0.0;
		public static double RPM_TARGET_AIRSHIP = 0.0;
		
		public static double LeftTicksPerRev = 0.0;
		public static double RightTicksPerRev = 0.0;
		public static double RPM_Deadband = 0.0;
		
		public static double LeftRPM_P = 0.0;
		public static double LeftRPM_I = 0.0;
		public static double LeftRPM_D = 0.0;
		public static double LeftRPM_F = 0.0;
		public static boolean Left_Inverted = false;
		
		public static double RightRPM_P = 0.0;
		public static double RightRPM_I = 0.0;
		public static double RightRPM_D = 0.0;
		public static double RightRPM_F = 0.0;
		public static boolean Right_Inverted = false;
		
		
	}
	
}
