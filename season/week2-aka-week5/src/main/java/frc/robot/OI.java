package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {
  
    public static Joystick joystick;
    public static JoystickButton deadReckoningButton;
    public static JoystickButton bangBangButton;

    public OI(){
        joystick = new Joystick(0);
        deadReckoningButton = new JoystickButton(joystick, 1);
        bangBangButton = new JoystickButton(joystick, 2);
    }

    public boolean getDeadReckoningButton(){        
        return deadReckoningButton.get();
    }
    public boolean getBangBangButton(){
        return bangBangButton.get();
    }
    
}
