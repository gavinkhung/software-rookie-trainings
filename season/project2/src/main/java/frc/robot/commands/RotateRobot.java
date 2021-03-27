// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Hardware;
import frc.robot.subsystems.Drivetrain;

public class RotateRobot extends CommandBase {

  private Drivetrain drivetrain;
  private double degrees;

  /** Creates a new RotateRobot. */
  public RotateRobot(Drivetrain drivetrain, double degrees) {
    this.drivetrain = drivetrain;
    this.degrees = degrees;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.turn(degrees);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // the range of possible angles is target - 3, target + 3
    double marginOfError = 0.1;
    double error = Math.abs(degrees - Hardware.gyro.getAngle()); 
    return error < marginOfError;
  }
}
