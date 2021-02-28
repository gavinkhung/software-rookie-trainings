package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AlignCommand;

public class OI {
  
    public static final Joystick joystick = new Joystick(0);
    public static final JoystickButton alignButton = new JoystickButton(joystick, 1); // PD Button

    public OI(){
        alignButton.whenPressed(new AlignCommand());
        SmartDashboard.putData("Run PD Loop", new AlignCommand());
    }

    public static boolean getAlignButton(){
        return alignButton.get();
    }
    
}
