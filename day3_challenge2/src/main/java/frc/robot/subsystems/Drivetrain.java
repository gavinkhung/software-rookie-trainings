/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Drivetrain extends SubsystemBase {

  private TalonSRX motor;

  public Drivetrain() {
    motor = new TalonSRX(0);
  }

  public void move(double speed){
    motor.set(ControlMode.PercentOutput, Robot.oi.getX());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
