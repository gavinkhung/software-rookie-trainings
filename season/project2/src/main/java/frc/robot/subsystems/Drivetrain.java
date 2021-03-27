// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Hardware;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */

  private DifferentialDrivetrainSim drivetrainSim;  

  public Drivetrain() {
    drivetrainSim = new DifferentialDrivetrainSim(Hardware.DRIVE_MOTOR, //Drive Motor ( Falcon500(2) )
                                                   Constants.gearRatio, //Gear Ratio ( 10:1 )
                                                   Constants.rotationalInertia, //Rotational Inertia ( 7.469 )
                                                   Constants.robotMass, //Mass of Robot in kg
                                                   Constants.wheelRadius, //Wheel Radius in Meters
                                                   Constants.trackWidth, //Track Width in Meters
                                                   null //Standard Deviations ( leave null for now )
                                                   );
    Hardware.leftFollower.follow(Hardware.leftLeader);
    Hardware.rightFollower.follow(Hardware.rightLeader);


    // configure the encoder to return gives distance travelled 
    Hardware.encoder.setDistancePerPulse(2 * Math.PI * Constants.wheelRadius / Constants.ticksPerRevolution  / Constants.gearRatio);
  }

  public void drive(double output){
    // everytime we set the speed of a talon, we also need to update the simulation, so it is accurate
    // Gives the drivetrain Sim the calculated motor ouput
    drivetrainSim.setInputs(output, output);
    Hardware.leftLeader.set(ControlMode.PercentOutput, output);
    Hardware.rightLeader.set(ControlMode.PercentOutput, output);
  }

  public void turn(double targetDegrees){
    // this is a simple p drive
    double error = targetDegrees - Hardware.gyro.getAngle();

    double output = 0.10 * error;
    Hardware.leftLeader.set(ControlMode.PercentOutput, output);
    Hardware.rightLeader.set(ControlMode.PercentOutput, -output);
    drivetrainSim.setInputs(-output, output);
  }

  public void stop(){
    // Gives the drivetrain Sim the calculated motor ouput
    drivetrainSim.setInputs(0, 0);
    Hardware.leftLeader.set(ControlMode.PercentOutput, 0);
    Hardware.rightLeader.set(ControlMode.PercentOutput, 0);
  }

  public void simulationPeriodic() {

    //Call parent class's simulationPeriodic() method
    super.simulationPeriodic();

    drivetrainSim.update(.02); // Simulates 0.02s of time. 0.02s is also how often the roborio/ these methods runs

    Hardware.gyroSim.setAngle(drivetrainSim.getHeading().getDegrees());

    Hardware.encoderSim.setDistance(drivetrainSim.getPose().getX());

    //Does some funky math to determine the current voltage of the battery based on the current pull of the drivetrain motors
    RoboRioSim.setVInVoltage(BatterySim.calculateDefaultBatteryLoadedVoltage(drivetrainSim.getCurrentDrawAmps()));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    log();
  }

  public void log(){
    SmartDashboard.putNumber("Drivetrain Right", Hardware.rightLeader.getMotorOutputPercent());
    SmartDashboard.putNumber("Drivetrain Left", Hardware.leftLeader.getMotorOutputPercent());
    SmartDashboard.putNumber("Gyro Angle", Hardware.gyro.getAngle());
  }
}
