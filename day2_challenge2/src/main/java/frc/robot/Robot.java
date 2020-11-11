/*

In order to use the TalonSRX class, you must add the Phoenix library with
http://devsite.ctr-electronics.com/maven/release/com/ctre/phoenix/Phoenix-latest.json

1. type: Control + Shift + P
2. select: WPILIB: Manage Vendor Libraries
3. select: Install new library (online)
4. paste: http://devsite.ctr-electronics.com/maven/release/com/ctre/phoenix/Phoenix-latest.json

*/
package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private Joystick joystick;
  private JoystickButton extendButton;

  private DoubleSolenoid solenoid;
  private DigitalInput breakbeam;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    joystick = new Joystick(0);
    extendButton = new JoystickButton(joystick, 2);

    solenoid = new DoubleSolenoid(3, 0, 1);
    breakbeam = new DigitalInput(9);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    if(extendButton.get()){
      solenoid.set(Value.kForward);
    }
    else if(breakbeam.get()){
      solenoid.set(Value.kReverse);
    }
  }

  @Override
  public void disabledInit() {
  }
  @Override
  public void disabledPeriodic() {
  }
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }
  @Override
  public void autonomousPeriodic() {
  }
  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }
  @Override
  public void teleopPeriodic() {
  }
  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }
  @Override
  public void testPeriodic() {
  }
}
