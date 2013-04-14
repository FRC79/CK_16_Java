/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.hopper;

import com.teamkrunch.ck16.CommandBase;
import com.teamkrunch.ck16.RobotMap;

/**
 *
 * @author sebastian
 */
public class RunRollersWithBeams extends CommandBase {
    
    public RunRollersWithBeams() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        // Roll the rollers until the hopper is full
        if(!hopper.isFull())
        {
            hopper.setRoller(RobotMap.ROLLER_POWER);
        }
        else
        {
            // When it's full, stop the rollers.
            hopper.stopRoller();
        }
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
