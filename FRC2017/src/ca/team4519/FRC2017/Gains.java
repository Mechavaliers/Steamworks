package ca.team4519.FRC2017;

public class Gains {

	public static final class Drive {
		private Drive() {}
			
		public static double CONTROL_LOOP_TIME = 0.01;
		
		public static double HANDLING_MODIFIER = 1.0;
		public static double PATH_TOLLERANCE = 0.25;
		
		public static double ROBOT_MAX_VELOCITY= 100.0; //TODO Update this at Mac
		public static double ROBOT_MAX_ACCELERATION = 80.0;
		public static double ROBOT_MAX_ROTATIONAL_VELOCITY = 250.0;
		public static double ROBOT_MAX_ROTATIONAL_ACCELERATION = 250.0; 
		public static double Wheelbase_Width = 35.25;
		public static double Wheelbase_Length = 39.25; 
		public static double EncoderDegsPerRev = 360.0;
		public static double WheelSize_Inches = 4.0;
		
		public static double Dist_P = 0.1;
		public static double Dist_I = 0.0;
		public static double Dist_D = 0.0;
		public static double Dist_V = 0.01;
		public static double Dist_A = 0.0005;
		public static double DistTurn_P = 0.0;//0.04
		public static double DistTurn_I = 0.0;
		public static double DistTurn_D = 0.0;
		public static double Dist_Tollerance = 0.75;

		public static double Turn_P = 0.1; //0.0825
		public static double Turn_I = 0.0; 
		public static double Turn_D = 0.00; // 0.015
		public static double Turn_V = 0.00725; //0.05
		public static double Turn_A = 0.001; //0.00225
		public static double Turn_Tollerance = 0.0225; //0.75
		
	}	
	
	public static final class Hopper {
		private Hopper() {}
		
		public static double AGITATOR_PWM_SEED = 0.0;
		}
	
	public static final class GearBox {
		private GearBox() {}
		
		public static double Left_Open = 25; //+ is open
		public static double Left_Closed = 0;
		public static double Left_Hug = 0;
		public static double Right_Open = 95;
		public static double Right_Closed = 118; // - is open
		public static double Right_Hug = 123;
		public static double Ejector_Open = 102.5;
		public static double Ejector_Closed = 0;
	}
}
