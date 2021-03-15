// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Hardware;

/*
This subsystem involes a roller that is used to intake balls into  the hopper
*/
public class Intake extends SubsystemBase {

  /**
   * In the constructor, we are going to initialize the used hardware
   * and configure them. For now, we will only use configFactoryDefault(),
   * but we will learn more in the future
  */
  public Intake() {
    Hardware.intakeRoller = new WPI_TalonSRX(5);

    Hardware.intakeRoller.configFactoryDefault();
  }

  /**
   * Will be called when the command to run the intake is executed
   */
  public void runIntake() {
    Hardware.intakeRoller.set(ControlMode.PercentOutput, 0.5);
  }

  /**
   * Will be called when the command to stop the intake is executed
   */
  public void stopIntake() {
    Hardware.intakeRoller.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // log data to smartdashboard
    log();
  }

  /**
   * Logs data about the hopper
   */
  public void log(){
    SmartDashboard.putNumber("Intake PercentOutput", Hardware.intakeRoller.getMotorOutputPercent());
  }
}
