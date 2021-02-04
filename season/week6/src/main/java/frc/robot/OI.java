package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.PDCommand;

public class OI {
  
    public static final Joystick joystick = new Joystick(0);
    public static final JoystickButton pdButton = new JoystickButton(joystick, 1); // PD Button

    public OI(){
        pdButton.whenPressed(new PDCommand());
        SmartDashboard.putData("Run PD Loop", new PDCommand());
    }

    public static boolean getPDButton(){
        return pdButton.get();
    }
    
}
