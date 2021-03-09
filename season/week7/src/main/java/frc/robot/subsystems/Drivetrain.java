// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
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

  double totalError = 0;

  double lastOutput = 0;

  public Drivetrain() {
    drivetrainSim = new DifferentialDrivetrainSim(Hardware.Drivetrain.DRIVE_MOTOR,
      Constants.Drivetrain.GEAR_RATIO,
      Constants.Drivetrain.ROTATIONAL_INERTIA,
      poundsToKg(Constants.Drivetrain.MASS_OF_ROBOT ),
      inchesToMeters(Constants.Drivetrain.WHEEL_RADIUS),
      inchesToMeters(Constants.Drivetrain.TRACK_WIDTH),
      null
    );
  }

  public void voltageToRotation(double target){
    double error = target - Hardware.Drivetrain.gyro.getAngle();
    double dt = Timer.getFPGATimestamp() - lastTime;

    double errorRate = (error - lastError)/ dt;

    // Integral range
    // presents totalError from being too large.
    // 1 is an arbitrary number and you can adjust it to be another number
    if(Math.abs(error) < 1 ){
      totalError += error * dt;
    }

    // if you want to do PID
    lastOutput = Constants.Drivetrain.kP * error + Constants.Drivetrain.kI * totalError + Constants.Drivetrain.kD * errorRate;

    if(lastOutput > 0)
      lastOutput = Math.min(1, lastOutput);
    
    else if(lastOutput < 1)
      lastOutput = Math.max(-1, lastOutput);
    
    lastError = error;
    lastTime = Timer.getFPGATimestamp();
    
    // the right and left have opposite signs, because you want to turn
    Hardware.Drivetrain.right.set(ControlMode.PercentOutput, lastOutput);
    Hardware.Drivetrain.left.set(ControlMode.PercentOutput, -lastOutput);
    drivetrainSim.setInputs(-lastOutput, lastOutput);
  }

  public void stop(){
    drivetrainSim.setInputs(0, 0);
    Hardware.Drivetrain.left.set(ControlMode.PercentOutput, 0);
    Hardware.Drivetrain.right.set(ControlMode.PercentOutput, 0);
  }

  public void resetSimulation(){
    drivetrainSim.setPose(new Pose2d(0, 0, new Rotation2d(0)));
    Hardware.Drivetrain.gyroSim.setAngle(0);
  }

  /**
   * converts pounds to kilograms
   * @param lbs    pounds
   * @return       kilograms
   */
  private double poundsToKg( double lbs ){
    return lbs/2.2;
  }
  
  /**
   * converts inches to meters
   * @param in    inches
   * @return      meters
   */
  private double inchesToMeters( double in ){
    return Units.inchesToMeters( in );
  }
  
  private void updateDashboard(){
    //divide by RobotController.getInputVoltage() to get a number between within 0 and 1;
    SmartDashboard.putNumber("Last Error", lastError);
    SmartDashboard.putNumber("Motor Output [-1 to 1]", Hardware.Drivetrain.left.getMotorOutputPercent());
    SmartDashboard.putBoolean("PID Button", Robot.oi.getAlignButton());
    SmartDashboard.putNumber("DrivetrainSim Angle", drivetrainSim.getHeading().getDegrees());
    SmartDashboard.putNumber("GyroSim Angle", Hardware.Drivetrain.gyroSim.getAngle());
  }

  public void simulationPeriodic() {

    //Call parent class's simulationPeriodic() method
    super.simulationPeriodic();

    drivetrainSim.update(.02); // Simulates 0.02s of time. 0.02s is also how often the roborio/ these methods runs
    updateDashboard();
    Hardware.Drivetrain.gyroSim.setAngle(drivetrainSim.getHeading().getDegrees());
  

    //Does some funky math to determine the current voltage of the battery based on the current pull of the drivetrain motors
    RoboRioSim.setVInVoltage(BatterySim.calculateDefaultBatteryLoadedVoltage(drivetrainSim.getCurrentDrawAmps()));
    System.out.println("voltage: "+lastOutput+" errpr: "+lastError);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
