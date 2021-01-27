// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Hardware;
import frc.robot.Robot;


public class Drivetrain extends SubsystemBase {

  private DifferentialDrivetrainSim drivetrainSim;

  double lastTime = 0;
  double totalDistance = 0;
  /** Creates a new Drivetrain. */
  public Drivetrain() {

    //initialize drivetrain simulation with parameters
    drivetrainSim = new DifferentialDrivetrainSim( Constants.DRIVE_MOTOR,                      //Drive Motor ( Falcon500(2) )
                                                   Constants.GEAR_RATIO,                       //Gear Ratio ( 10:1 )
                                                   Constants.ROTATIONAL_INERTIA,               //Rotational Inertia ( 7.469 )
                                                   poundsToKg( Constants.MASS_OF_ROBOT ),      //Mass of Robot in kg
                                                   inchesToMeters( Constants.WHEEL_RADIUS ),   //Wheel Radius in Meters
                                                   inchesToMeters( Constants.TRACK_WIDTH ),    //Track Width in Meters
                                                   null                                        //Standard Deviations ( leave null for now )
                                                   ); 

  }  

  public void drive( double speed )
  {
    Hardware.LEFT.set(ControlMode.PercentOutput, speed );
    Hardware.RIGHT.set(ControlMode.PercentOutput, speed );
    
    totalDistance = drivetrainSim.getLeftPositionMeters();
  }

  public void stop( )
  {
    Hardware.LEFT.set(ControlMode.PercentOutput, 0 );
    Hardware.RIGHT.set(ControlMode.PercentOutput, 0 );
  }
  
  private void updateDashboard()
  {
    //divide by RobotController.getInputVoltage() to get a number between within 0 and 1;
    SmartDashboard.putNumber("Left Motor Speed", Hardware.LEFT.getMotorOutputVoltage() / RobotController.getInputVoltage() );
    SmartDashboard.putNumber("Right Motor Speed", Hardware.RIGHT.getMotorOutputVoltage() / RobotController.getInputVoltage() );

    //Subtract totalDistance to ensure the distance displayed is from when the timer resets.
    SmartDashboard.putNumber("Position (m)", drivetrainSim.getLeftPositionMeters() - totalDistance );


    SmartDashboard.putBoolean("Dead Reckoning Button", Robot.oi.getDeadReckoningButton() );    
  }

  /**
   * converts pounds to kilograms
   * @param lbs    pounds
   * @return       kilograms
   */
  private double poundsToKg( double lbs )
  {
    return lbs/2.2;
  }
  
  /**
   * converts inches to meters
   * @param in    inches
   * @return      meters
   */
  private double inchesToMeters( double in )
  {
    return Units.inchesToMeters( in );
  }

  public void simulationPeriodic() {

    //Call parent class's simulationPeriodic() method
    super.simulationPeriodic();

    //set drivetrain simulation inputs from left and right
    drivetrainSim.setInputs( Hardware.LEFT.getMotorOutputVoltage(), Hardware.RIGHT.getMotorOutputVoltage() );    
    updateDashboard();
    
    //update drivetrain simulation
    drivetrainSim.update( 0.02 );
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
