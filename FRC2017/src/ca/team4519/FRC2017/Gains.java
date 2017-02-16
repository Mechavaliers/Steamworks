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
		public static double RPM_TARGET_WALL = 0.0;
		
		public static double TicksPerRev = 0.0;
		public static double RPM_Deadband = 0.0;
		
		public static double RPM_P = 0.0;
		public static double RPM_I = 0.0;
		public static double RPM_D = 0.0;
		public static double RPM_F = 0.0;
		
	}
	
	
	public static final class Hopper {
		private Hopper() {}
		
		public static double AGITATOR_PWM_SEED = 0.0;
		}
}
