/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.shooterpistons;

import com.teamkrunch.ck16.CommandBase;

/**
 *
 * @author sebastian
 */
public class ExtendFirePiston extends CommandBase {
    
    private boolean isFinished, runsForever;
    
    public ExtendFirePiston() {
        this(false);
    }
    
    public ExtendFirePiston(boolean runContinuously){
        requires(shooterPistons);
        runsForever = runContinuously;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Run this infinitely to wait for something else to stop it.
        shooterPistons.extendFirePiston();
        hopper.setLoadPistonLocked(true); // Lock the load piston from punching down.
        
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
