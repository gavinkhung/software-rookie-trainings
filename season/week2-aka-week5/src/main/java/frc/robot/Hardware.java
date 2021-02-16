// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public final class Hardware {

    public final static class Drivetrain {
        public static final WPI_TalonSRX LEFT = new WPI_TalonSRX(1);
        public static final WPI_TalonSRX RIGHT = new WPI_TalonSRX(2);
    }

}
