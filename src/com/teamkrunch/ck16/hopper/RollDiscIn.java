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
public class RollDiscIn extends CommandBase {
    
    private boolean isFinished, runsForever;
    
    public RollDiscIn() {
        
    }
    
    public RollDiscIn(boolean runContinously){
        requires(hopper);
        runsForever = runContinously;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Run this infinitely so that something else has to stop it.
        hopper.setRoller(RobotMap.ROLLER_POWER);
        
        if(!runsForever){
            isFinished = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
