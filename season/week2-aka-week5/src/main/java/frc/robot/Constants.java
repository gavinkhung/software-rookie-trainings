package frc.robot;

import edu.wpi.first.wpilibj.system.plant.DCMotor;

public final class Constants 
{
    public final static class Drivetrain{
        // these constants are used for the drivetrain simulation class
        public static final DCMotor DRIVE_MOTOR = DCMotor.getFalcon500(2);
        public static final double GEAR_RATIO = 10;                   
        public static final double ROTATIONAL_INERTIA = 7.469;        // kg * m^2
        public static final double MASS_OF_ROBOT = 150.0;             // pounds
        public static final double WHEEL_RADIUS = 3.0;                // inches
        public static final double TRACK_WIDTH = 20.0;                // inches
    }
}
