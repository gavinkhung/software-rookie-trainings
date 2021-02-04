// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

  WPI_TalonSRX leftIntake, rightIntake;
  DigitalInput breakbream;

  public Intake() {
    leftIntake = new WPI_TalonSRX(0);
    rightIntake = new WPI_TalonSRX(1);

    leftIntake.configFactoryDefault();
    rightIntake.configFactoryDefault();
    leftIntake.follow(rightIntake);

    breakbream = new DigitalInput(3);
  }


  public void runIntake(double speed){
    rightIntake.set(ControlMode.PercentOutput, speed);
    SmartDashboard.putNumber("Motor Output", speed);

  }

  public void stopIntake(){
    rightIntake.set(ControlMode.PercentOutput, 0);
    SmartDashboard.putNumber("Motor Output", 0);
  }

  public boolean getBreakbream() {
    return breakbream.get();
  }


  @Override
  public void periodic() {
        SmartDashboard.putBoolean("Breakbeam Reading", breakbream.get());
  }
}
