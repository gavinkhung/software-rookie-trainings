// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.system.plant.DCMotor;

public final class Hardware {

    public final static class Drivetrain {
        public static final DCMotor DRIVE_MOTOR = DCMotor.getFalcon500(2);
                
        public static final AnalogGyro gyro = new AnalogGyro(1);
  
        public static final AnalogGyroSim gyroSim = new AnalogGyroSim(gyro);

        public static final WPI_TalonSRX left = new WPI_TalonSRX(1);
        public static final WPI_TalonSRX right = new WPI_TalonSRX(2);
    }

}
