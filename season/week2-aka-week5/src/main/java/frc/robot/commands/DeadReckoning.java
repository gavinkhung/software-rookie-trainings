// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DeadReckoning extends CommandBase {
  /** Creates a new Drive. */

  private double seconds;
  private double speed;
  private double startTime;

  public DeadReckoning( double _seconds, double _speed ) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drivetrain);
    
    seconds = _seconds;
    speed = _speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }
  /**
   * When the method is called, the drivetrain is set to the speed and the time restarts 
   */
  public void reset()
  {
    Robot.drivetrain.drive( speed );
    startTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if button is pressed, then reset the timer
    if( Robot.oi.getDeadReckoningButton() )
      reset();

    // If current time - start time is more than or equal to the desired seconds, then stop.
    if( Timer.getFPGATimestamp() - startTime >= seconds ) 
    {
      Robot.drivetrain.stop();
      return;
    }

    //display the time while power is being given to the motors since the last time-reset
    SmartDashboard.putNumber("Time Since Button Pressed", Timer.getFPGATimestamp() - startTime );

  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
