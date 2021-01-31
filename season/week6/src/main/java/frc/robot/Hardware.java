// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.system.plant.DCMotor;

public final class Hardware {

    public final static class Drivetrain {
        public static final DCMotor DRIVE_MOTOR = DCMotor.getFalcon500(2);
        
        public static final Encoder encoder = new Encoder(0, 1);
  
        public static final EncoderSim encoderSim = new EncoderSim(encoder);
    }

}
