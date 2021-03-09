// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.system.plant.DCMotor;

/** Add your docs here. */
public class Hardware {
    // required to create an instance of the drivetrain sim
    public static DCMotor DRIVE_MOTOR = DCMotor.getFalcon500(2);

    // encoder will be used for a keep track of distance traveled
    public static Encoder encoder = new Encoder(0, 1);
    public static EncoderSim encoderSim = new EncoderSim(encoder);

    // these will be used for the drivetrain
    public static WPI_TalonSRX leftLeader = new WPI_TalonSRX(1);
    public static WPI_TalonSRX rightLeader = new WPI_TalonSRX(2);
    public static WPI_TalonSRX leftFollower = new WPI_TalonSRX(3);
    public static WPI_TalonSRX rightFollower = new WPI_TalonSRX(4);
}
