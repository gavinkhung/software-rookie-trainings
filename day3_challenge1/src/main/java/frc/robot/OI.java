/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ExtendCommand;;

/**
 * Add your docs here.
 */
public class OI {

    private Joystick joystick;
    private JoystickButton startButton, endButton;

    public OI(){
        joystick = new Joystick(0);
        startButton = new JoystickButton(joystick, 1);

        startButton.whenPressed(new ExtendCommand());
    }

    public boolean endButtonIsPressed(){
        return endButton.get();
    }
}
