// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Hardware;


/**
 * The hopper is used to load cargo balls before heading to the flywheel
 * 
 * The commands used with the hopper will run the motors until the 
 * breakbeam returns true.
 */
public class Hopper extends SubsystemBase {

  /**
   * In the constructor, we are going to initialize the used hardware
   * and configure them. For now, we will only use configFactoryDefault(),
   * but we will learn more in the future
  */
  public Hopper() {
    Hardware.hopperTopRoller = new WPI_TalonSRX(6);
    Hardware.hopperBottomRoller = new WPI_TalonSRX(7);

    Hardware.hopperTopRoller.configFactoryDefault();
    Hardware.hopperBottomRoller.configFactoryDefault();

    Hardware.hopperBreakbream = new DigitalInput(3);
  }

  /**
   * Will be called when the commands to run the hoppers start
   */
  public void runHopper() {
    Hardware.hopperTopRoller.set(ControlMode.PercentOutput, 0.5);
    Hardware.hopperBottomRoller.set(ControlMode.PercentOutput, 0.5);
  }

  /**
   * Will be called when the commands to run the hoppers finish
   */
  public void stopHopper() {
    Hardware.hopperTopRoller.set(ControlMode.PercentOutput, 0.0);
    Hardware.hopperBottomRoller.set(ControlMode.PercentOutput, 0.0);
  }

  /**
   * This will be used to tell the commands to run the hoppers to stop
   * @return breakbeam reading
   */
  public boolean getBreakbeam(){
    return Hardware.hopperBreakbream.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    log();
  }

  /**
   * Logs data about the hopper
   */
  public void log(){
    SmartDashboard.putNumber("Hopper PercentOutput", Hardware.hopperTopRoller.getMotorOutputPercent());
    SmartDashboard.putBoolean("Hopper breakbeam", Hardware.hopperBreakbream.get());
  }
}
