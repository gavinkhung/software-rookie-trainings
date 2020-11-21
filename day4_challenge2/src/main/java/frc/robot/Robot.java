/*
In order to use the TalonSRX class, you must add the Phoenix library with
http://devsite.ctr-electronics.com/maven/release/com/ctre/phoenix/Phoenix-latest.json
1. type: Control + Shift + P
2. select: WPILIB: Manage Vendor Libraries
3. select: Install new library (online)
4. paste: http://devsite.ctr-electronics.com/maven/release/com/ctre/phoenix/Phoenix-latest.json

5: repeat steps 1-3
6: paste: https://www.kauailabs.com/dist/frc/2020/navx_frc.json
*/
package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class Robot extends TimedRobot {

  private TalonSRX left, right;
  private AHRS navX;
  
  private double error;


  public void robotInit() {
    left = new TalonSRX(0);
    right = new TalonSRX(1);

    navX = new AHRS(SerialPort.Port.kMXP);
  }

  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    // get the current angle
    error = 90-navX.getYaw();

    // multiply by a constant
    double motorOutput = 0.1 * error;

    // check to see if the angle is equal to 90
    // otherwise, turn the robot

    left.set(ControlMode.PercentOutput, motorOutput);
    right.set(ControlMode.PercentOutput, -motorOutput);
  }

  
  public void disabledInit() {
  }


  public void disabledPeriodic() {
  }

  
  public void autonomousInit() {
   
  }

 
  public void autonomousPeriodic() {
  }

  
  public void teleopInit() {
    
  }

  
  public void teleopPeriodic() {

  }


  public void testInit() {
   
    CommandScheduler.getInstance().cancelAll();
  }

  
  public void testPeriodic() {
  }
}
