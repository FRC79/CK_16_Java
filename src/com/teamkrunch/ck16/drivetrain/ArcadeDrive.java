/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.drivetrain;

import com.teamkrunch.ck16.CommandBase;
import com.teamkrunch.ck16.util.TeleopHelper;

/**
 *
 * @author sebastian
 */
public class ArcadeDrive extends CommandBase {
    
    public ArcadeDrive() {
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double moveOutput = TeleopHelper.mapJoystickToPowerOutput(
                oi.getDriverJoystick().getRawAxis(2));
        double rotOutput = TeleopHelper.mapJoystickToPowerOutput(
                oi.getDriverJoystick().getRawAxis(1));
        
        drivetrain.arcadeDrive((float)moveOutput, (float)rotOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
