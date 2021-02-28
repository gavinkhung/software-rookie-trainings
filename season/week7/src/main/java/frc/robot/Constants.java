package frc.robot;

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
public final class Constants 
{
    public final static class Drivetrain{
        public static final double GEAR_RATIO = 10;                   
        public static final double ROTATIONAL_INERTIA = 7.469;        // kg * m^2
        public static final double MASS_OF_ROBOT = 150.0;             // pounds
        public static final double WHEEL_RADIUS = 3.0;                // inches
        public static final double TRACK_WIDTH = 20.0;                // inches
        public static final double TICKS_PER_REVOLUTION = 4096;
        
        public static final double kP = 0.7;
        public static final double kI = 0.2;
        public static final double kD = 0.1;
    }
}
