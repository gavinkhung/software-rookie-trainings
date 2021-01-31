// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Hardware;
import frc.robot.Robot;


public class Drivetrain extends SubsystemBase {

  private DifferentialDrivetrainSim drivetrainSim;

  double lastTime = 0;
  double lastError = 0;

  // double totalError = 0;

  double lastOutput = 0;

  public Drivetrain() {

    //initialize drivetrain simulation with parameters
    drivetrainSim = new DifferentialDrivetrainSim(Hardware.Drivetrain.DRIVE_MOTOR,                     //Drive Motor ( Falcon500(2) )
                                                   Constants.Drivetrain.GEAR_RATIO,                       //Gear Ratio ( 10:1 )
                                                   Constants.Drivetrain.ROTATIONAL_INERTIA,               //Rotational Inertia ( 7.469 )
                                                   poundsToKg(Constants.Drivetrain.MASS_OF_ROBOT ),      //Mass of Robot in kg
                                                   inchesToMeters(Constants.Drivetrain.WHEEL_RADIUS),   //Wheel Radius in Meters
                                                   inchesToMeters(Constants.Drivetrain.TRACK_WIDTH),    //Track Width in Meters
                                                   null                                                   //Standard Deviations ( leave null for now )
                                                   );
    Hardware.Drivetrain.encoder.setDistancePerPulse(2 * Math.PI * Constants.Drivetrain.WHEEL_RADIUS / Constants.Drivetrain.TICKS_PER_REVOLUTION  / Constants.Drivetrain.GEAR_RATIO);   // Encoder Reading * Distance Per Pulse gives distance travelled 

  }

  public double voltageToDistance(double target){
    double error = target - Hardware.Drivetrain.encoder.getDistance();
    double dt = Timer.getFPGATimestamp() - lastTime;

    double errorRate = (error - lastError)/ dt;

    // if you want to PID
    // if(error < 1 ){
    //   totalError += error * dt;
    // }

    // if you want to do PID
    // motorOutput = Constants.Drivetrain.kP * error + Constants.Drivetrain.kI * errorSum + Constants.Drivetrain.kD * errorRate;

    // if you want to do PD
    lastOutput = Constants.Drivetrain.kP * error + Constants.Drivetrain.kD * errorRate;

    if(lastOutput > 0)
      lastOutput = Math.min(1, lastOutput);
    
    else if(lastOutput < 1)
      lastOutput = Math.max(-1, lastOutput);
    
    lastError = error;
    lastTime = Timer.getFPGATimestamp();

    return lastOutput;
  }

  public void drive(double output){
    drivetrainSim.setInputs(output, output); // Gives the drivetrain Sim the calculated motor ouput
  }

  public void stop(){
    drivetrainSim.setInputs(0, 0); // Gives the drivetrain Sim the calculated motor ouput
  }
  
  private void updateDashboard()
  {
    //divide by RobotController.getInputVoltage() to get a number between within 0 and 1;
    SmartDashboard.putNumber("Last Error", lastError);
    SmartDashboard.putNumber("Motor Output [-1 to 1]", lastOutput);
    SmartDashboard.putNumber("Encoder Reading", Hardware.Drivetrain.encoder.getDistance());

    SmartDashboard.putBoolean("PID Button", Robot.oi.getPDButton() );    
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

    drivetrainSim.update(.02); // Simulates 0.02s of time. 0.02s is also how often the roborio/ these methods runs
    updateDashboard();

    Hardware.Drivetrain.encoderSim.setDistance(drivetrainSim.getPose().getX());

    //Does some funky math to determine the current voltage of the battery based on the current pull of the drivetrain motors
    RoboRioSim.setVInVoltage(BatterySim.calculateDefaultBatteryLoadedVoltage(drivetrainSim.getCurrentDrawAmps()));
    System.out.println(lastOutput+" "+Robot.oi.getPDButton());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
