
package com.teamkrunch.ck16;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public Joystick driverJoystick, shooterGamepad;
    public JoystickButton buttonInvertTiltJoy1, buttonInvertTiltJoy2,
            buttonInvertHangPiston, buttonExtendFirePiston, buttonToggleShooterWheels,
            buttonForwardRollers, buttonReverseRollers, buttonManualLoadPiston,
            buttonToggleAutoLoad;
    
    public OI()
    {
        // Init Joystick and Gamepad
        driverJoystick = new Joystick(1);
        shooterGamepad = new Joystick(2);
        
        // Init Buttons
        buttonInvertTiltJoy1 = new JoystickButton(driverJoystick, 8); // Tilt shooter on joystick
        buttonInvertTiltJoy2 = new JoystickButton(shooterGamepad, 5); // Tilt shooter on gamepad
        buttonInvertHangPiston = new JoystickButton(driverJoystick, 6); // Invert hang position (timeout needed)
        buttonExtendFirePiston = new JoystickButton(shooterGamepad, 6); // Extend fire piston
        buttonToggleShooterWheels = new JoystickButton(shooterGamepad, 2); // Toggle wheel spinning
        buttonForwardRollers = new JoystickButton(shooterGamepad, 3); // Manually run rollers forward
        buttonReverseRollers = new JoystickButton(shooterGamepad, 7); // Manually run rollers in reverse
        buttonManualLoadPiston = new JoystickButton(shooterGamepad, 1); // Manually control load piston
        buttonToggleAutoLoad = new JoystickButton(shooterGamepad, 8); // Toggle AutoLoad
    }

    public Joystick getDriverJoystick() {
        return driverJoystick;
    }

    public Joystick getShooterGamepad() {
        return shooterGamepad;
    }
    
}

