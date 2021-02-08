package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeCommand;

public class OI {
    
    // Joystick joy;
    // JoystickButton xButton;

    public OI(){
        // joy = new Joystick(0);
       // xButton = new JoystickButton(joy, 3);

        //xButton.whenPressed(new IntakeCommand());

        SmartDashboard.putData("Run Intake Command", new IntakeCommand());

    }
}
