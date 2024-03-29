/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.loadpiston;

import com.teamkrunch.ck16.CommandBase;
import com.teamkrunch.ck16.RobotMap;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author sebastian
 */
public class PunchDisc extends CommandBase {
    
    public PunchDisc() {
        requires(loadPiston);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        if(hopperState.isDiscReadyToLoad())
        {
            /* If the fire piston isn't locked, punch the next disc into 
             * the fire chamber. */
            if(!loadPiston.isLocked() && !hopperState.isDiscInChamber())
            {
                // Wait for disc to transfer completely to punch zone.
                Timer.delay(RobotMap.ROLLER_TO_PUNCH_ZONE_DELAY);
                
                loadPiston.extend(); // Punch down to load.
                Timer.delay(RobotMap.PISTON_DELAY); // Wait for the piston to go down.
                loadPiston.retract(); // Pull piston back up.
                Timer.delay(RobotMap.PISTON_DELAY); // Wait for the piston to come up.
            }
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
