/*

In order to use the TalonSRX class, you must add the Phoenix library with
http://devsite.ctr-electronics.com/maven/release/com/ctre/phoenix/Phoenix-latest.json

1. type: Control + Shift + P
2. select: WPILIB: Manage Vendor Libraries
3. select: Install new library (online)
4. paste: http://devsite.ctr-electronics.com/maven/release/com/ctre/phoenix/Phoenix-latest.json

*/
package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private Joystick joystick;
  private JoystickButton motorForwardButton;
  private JoystickButton motorBackwardButton;
  private JoystickButton solenoidForwardButton;
  private JoystickButton solenoidBackwardButton;

  private TalonSRX right, left;

  private DoubleSolenoid solenoid;

  private DigitalInput sensor;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    joystick = new Joystick(0);
    motorForwardButton = new JoystickButton(joystick, 1);
    motorBackwardButton = new JoystickButton(joystick, 1);
    solenoidForwardButton = new JoystickButton(joystick, 1);
    solenoidBackwardButton = new JoystickButton(joystick, 1);

    right = new TalonSRX(0);
    left = new TalonSRX(0);

    solenoid = new DoubleSolenoid(0, 1, 2);

    sensor = new DigitalInput(1);
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

    if(motorForwardButton.get()){
      right.set(ControlMode.PercentOutput, 1);
      left.set(ControlMode.PercentOutput, 1);
    }
    else if(motorBackwardButton.get()){
      right.set(ControlMode.PercentOutput, -1);
      left.set(ControlMode.PercentOutput, -1);
    }
    else if(solenoidForwardButton.get()){
      // import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
      solenoid.set(Value.kForward);
    }
    else if(solenoidBackwardButton.get()){
      solenoid.set(Value.kReverse);
    }
    else if(sensor.get()){
      System.out.println("motion detected");
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
