/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.misc;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author sebastian
 */
public class CancelCommand extends Command {
    
    private boolean isFinished;
    private Command commandToCancel;
    
    public CancelCommand(Command command) {
        commandToCancel = command;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!isFinished){
            commandToCancel.cancel();
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
