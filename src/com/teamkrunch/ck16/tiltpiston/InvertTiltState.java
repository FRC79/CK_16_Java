/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.tiltpiston;

import com.teamkrunch.ck16.CommandBase;
import com.teamkrunch.ck16.RobotMap;

/**
 *
 * @author sebastian
 */
public class InvertTiltState extends CommandBase {
    
    private boolean isFinished;
    
    public InvertTiltState() {
        requires(tiltPiston); 
        setTimeout(RobotMap.PISTON_DELAY); // Wait time to finish task.
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!isFinished){
            tiltPiston.invertCurrentTiltState();
            isFinished = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut(); // Wait for the timeout in order to finish task.
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
