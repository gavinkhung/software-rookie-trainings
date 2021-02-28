// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AlignCommand extends CommandBase {
  /** Creates a new Drive. */

  public AlignCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }
  /**
   * When the method is called, the drivetrain is set to the speed and the time restarts 
   */
  public void reset(){
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.drivetrain.voltageToRotation(90);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.stop();
    Robot.drivetrain.resetSimulation();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
   // return !Robot.oi.getAlignButton();
  }
}
