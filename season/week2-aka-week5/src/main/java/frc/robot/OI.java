package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {
  
    public static final Joystick         JOYSTICK = new Joystick(0);
    public static final JoystickButton   DEAD_RECKONING_BUTTON = new JoystickButton( JOYSTICK, 1 ); // Dead Reckoning Button
    public static final JoystickButton   BUTTON2 = new JoystickButton( JOYSTICK, 2 );

    public boolean getDeadReckoningButton()
    {
        return DEAD_RECKONING_BUTTON.get();
    }
    
}
