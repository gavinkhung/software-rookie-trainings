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
  
  private double angle;


  public void robotInit() {
    left = new TalonSRX(0);
    right = new TalonSRX(1);

    navX = new AHRS(SerialPort.Port.kMXP);
  }

  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    // get the current angle
    angle = navX.getYaw();

    // multiply by a constant
    double amount = 0.1 * angle;

    // check to see if the angle is equal to 90
    // otherwise, turn the robot

    if(angle>90){
      // turn left

      left.set(ControlMode.PercentOutput, -amount);
      right.set(ControlMode.PercentOutput, amount);
    }
    else if(angle<90){
      // turn right

      left.set(ControlMode.PercentOutput, amount);
      right.set(ControlMode.PercentOutput, -amount);
    }
    else {
      // do not move
      left.set(ControlMode.PercentOutput, 0);
      right.set(ControlMode.PercentOutput, 0);
    }
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