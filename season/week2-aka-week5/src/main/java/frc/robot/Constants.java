package frc.robot;

import edu.wpi.first.wpilibj.system.plant.DCMotor;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    
    public static final DCMotor     DRIVE_MOTOR = DCMotor.getFalcon500(2);
    public static final double      GEAR_RATIO = 10;                   
    public static final double      ROTATIONAL_INERTIA = 7.469;        // kg * m^2
    public static final double      MASS_OF_ROBOT = 150.0;             // pounds
    public static final double      WHEEL_RADIUS = 3.0;                // inches
    public static final double      TRACK_WIDTH = 20.0;                // inches

}
