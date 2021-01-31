// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class BangBang extends CommandBase {
  /** Creates a new BangBang. */

  double target;
  double speed;

  public BangBang( double _target, double _speed ) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements( Robot.drivetrain );
    target = _target;
    speed = _speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double marginoferror = 0.1; 
    if( Robot.drivetrain.compareToPosition( target ) > marginoferror  )
    {
      Robot.drivetrain.drive(-speed);
      System.out.println("greater");
    }
    else if( Robot.drivetrain.compareToPosition( target ) < -marginoferror )
    {
      Robot.drivetrain.drive(speed);
      System.out.println("less");
    }
    else
    {
      Robot.drivetrain.stop();
    }



  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
