package ca.team4519.lib;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class MechaRobotBase extends RobotBase {
  private boolean m_disabledInitialized;
  private boolean m_autonomousInitialized;
  private boolean m_teleopInitialized;
  private boolean m_testInitialized;

  public MechaRobotBase() {

    m_disabledInitialized = false;
    m_autonomousInitialized = false;
    m_teleopInitialized = false;
    m_testInitialized = false;
  }


  public void startCompetition() {
    HAL.report(tResourceType.kResourceType_Framework,
                                   tInstances.kFramework_Iterative);

    robotInit();

    // Tell the DS that the robot is ready to be enabled
    HAL.observeUserProgramStarting();

    // loop forever, calling the appropriate mode-dependent function
    LiveWindow.setEnabled(false);
    while (true) {
    	allPeriodic();
      // Wait for new data to arrive
      m_ds.waitForData();
      // Call the appropriate function depending upon the current robot mode
      if (isDisabled()) {
        // call DisabledInit() if we are now just entering disabled mode from
        // either a different mode or from power-on
        if (!m_disabledInitialized) {
          LiveWindow.setEnabled(false);
          disabledInit();
          m_disabledInitialized = true;
          // reset the initialization flags for the other modes
          m_autonomousInitialized = false;
          m_teleopInitialized = false;
          m_testInitialized = false;
        }
        HAL.observeUserProgramDisabled();
        disabledPeriodic();
      } else if (isTest()) {
        // call TestInit() if we are now just entering test mode from either
        // a different mode or from power-on
        if (!m_testInitialized) {
          LiveWindow.setEnabled(true);
          testInit();
          m_testInitialized = true;
          m_autonomousInitialized = false;
          m_teleopInitialized = false;
          m_disabledInitialized = false;
        }
        HAL.observeUserProgramTest();
        testPeriodic();
      } else if (isAutonomous()) {
        // call Autonomous_Init() if this is the first time
        // we've entered autonomous_mode
        if (!m_autonomousInitialized) {
          LiveWindow.setEnabled(false);
          // KBS NOTE: old code reset all PWMs and relays to "safe values"
          // whenever entering autonomous mode, before calling
          // "Autonomous_Init()"
          autonomousInit();
          m_autonomousInitialized = true;
          m_testInitialized = false;
          m_teleopInitialized = false;
          m_disabledInitialized = false;
        }
        HAL.observeUserProgramAutonomous();
        autonomousPeriodic();
      } else {
        // call Teleop_Init() if this is the first time
        // we've entered teleop_mode
        if (!m_teleopInitialized) {
          LiveWindow.setEnabled(false);
          teleopInit();
          m_teleopInitialized = true;
          m_testInitialized = false;
          m_autonomousInitialized = false;
          m_disabledInitialized = false;
        }
        HAL.observeUserProgramTeleop();
        teleopPeriodic();
      }
      robotPeriodic();
    }
  }

  public void robotInit() {
    System.out.println("Default MechaRobotBase.robotInit() method... Overload me!");
  }

  public void disabledInit() {
    System.out.println("Default MechaRobotBase.disabledInit() method... Overload me!");
  }

  public void autonomousInit() {
    System.out.println("Default MechaRobotBase.autonomousInit() method... Overload me!");
  }

  public void teleopInit() {
    System.out.println("Default MechaRobotBase.teleopInit() method... Overload me!");
  }


  public void testInit() {
    System.out.println("Default MechaRobotBase.testInit() method... Overload me!");
  }


  private boolean m_rpFirstRun = true;

  public void robotPeriodic() {
    if (m_rpFirstRun) {
      System.out.println("Default MechaRobotBase.robotPeriodic() method... Overload me!");
      m_rpFirstRun = false;
    }
  }

  private boolean m_dpFirstRun = true;


  public void disabledPeriodic() {
    if (m_dpFirstRun) {
      System.out.println("Default MechaRobotBase.disabledPeriodic() method... Overload me!");
      m_dpFirstRun = false;
    }
  }

  private boolean m_apFirstRun = true;


  public void autonomousPeriodic() {
    if (m_apFirstRun) {
      System.out.println("Default MechaRobotBase.autonomousPeriodic() method... Overload me!");
      m_apFirstRun = false;
    }
  }

  private boolean m_tpFirstRun = true;

  public void teleopPeriodic() {
    if (m_tpFirstRun) {
      System.out.println("Default MechaRobotBase.teleopPeriodic() method... Overload me!");
      m_tpFirstRun = false;
    }
  }

  public void allPeriodic(){
	  
  }
  private boolean m_tmpFirstRun = true;

  public void testPeriodic() {
    if (m_tmpFirstRun) {
      System.out.println("Default MechaRobotBase.testPeriodic() method... Overload me!");
      m_tmpFirstRun = false;
    }
  }
}
