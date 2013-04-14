/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.misc;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author sebastian
 */
public class Wait extends Command {
    
    public Wait(double timeInSeconds) {
        setTimeout(timeInSeconds); // Time to wait.
    }
    
    /* Wait will also interupting a subsystem. */
    public Wait(double timeInSeconds, Subsystem s){
        requires(s); // Subsystem to interupt.
        setTimeout(timeInSeconds); // Time to wait.
        setInterruptible(false); // Button pressed won't override this.
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut(); // Wait for time to expire to finish task.
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
