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

  public Drivetrain() {

    //initialize drivetrain simulation with parameters
    drivetrainSim = new DifferentialDrivetrainSim(Constants.Drivetrain.DRIVE_MOTOR,                      //Drive Motor ( Falcon500(2) )
                                                  Constants.Drivetrain.GEAR_RATIO,                       //Gear Ratio ( 10:1 )
                                                  Constants.Drivetrain.ROTATIONAL_INERTIA,               //Rotational Inertia ( 7.469 )
                                                  poundsToKg( Constants.Drivetrain.MASS_OF_ROBOT ),      //Mass of Robot in kg
                                                  inchesToMeters( Constants.Drivetrain.WHEEL_RADIUS ),   //Wheel Radius in Meters
                                                  inchesToMeters( Constants.Drivetrain.TRACK_WIDTH ),    //Track Width in Meters
                                                  null                                                   //Standard Deviations ( leave null for now )
                                                  ); 

  }  

  /**
   * converts pounds to kilograms
   * @param lbs    pounds
   * @return       kilograms
   */
  private double poundsToKg( double lbs ){
    return lbs/2.2;
  }

  private double inchesToMeters(double in){
    return Units.inchesToMeters(in);
  }

  public void drive(double speed){
    Hardware.Drivetrain.LEFT.set(ControlMode.PercentOutput, speed);
    Hardware.Drivetrain.RIGHT.set(ControlMode.PercentOutput, speed);
    
    totalDistance = drivetrainSim.getLeftPositionMeters();
  }

  public void stop(){
    Hardware.Drivetrain.LEFT.set(ControlMode.PercentOutput, 0);
    Hardware.Drivetrain.RIGHT.set(ControlMode.PercentOutput, 0);
  }

  // used in the commands
  public double compareToPosition( double target )
  {
    return target - drivetrainSim.getLeftPositionMeters() ;
  }

  public void simulationPeriodic() {

    //Call parent class's simulationPeriodic() method
    super.simulationPeriodic();

    //set drivetrain simulation inputs from left and right
    drivetrainSim.setInputs( Hardware.Drivetrain.LEFT.getMotorOutputVoltage(),  
                             Hardware.Drivetrain.RIGHT.getMotorOutputVoltage());
    
    SmartDashboard.putNumber("Left Motor Output", Hardware.Drivetrain.LEFT.getMotorOutputVoltage() / RobotController.getInputVoltage());
    SmartDashboard.putNumber("Right Motor Output", Hardware.Drivetrain.RIGHT.getMotorOutputVoltage() / RobotController.getInputVoltage());

    //Subtract totalDistance to ensure the distance displayed is from when the timer resets.
    SmartDashboard.putNumber("Motor Position (m)", drivetrainSim.getLeftPositionMeters());

    SmartDashboard.putBoolean("Dead Reckoning Button", Robot.oi.getDeadReckoningButton());
    SmartDashboard.putBoolean("Bang Bang Button", Robot.oi.getBangBangButton());
    
    //update drivetrain simulation
    drivetrainSim.update( 0.02 );
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
