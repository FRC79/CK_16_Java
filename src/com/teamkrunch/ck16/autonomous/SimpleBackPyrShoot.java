/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.autonomous;

import com.teamkrunch.ck16.CommandBase;
import com.teamkrunch.ck16.RobotMap;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author sebastian
 */
public class SimpleBackPyrShoot extends CommandBase {
    
    private boolean isFinished;
    
    public SimpleBackPyrShoot() {
        requires(firePiston);
        requires(shooterWheels);
        requires(rollers);
        requires(loadPiston);
        requires(hanger);
        requires(tiltPiston);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        tiltPiston.TiltDown(); // We want to start tilted down.
        hanger.retract(); // We want to start with the hanger retracted.
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Timer.delay(0.1);
        
        // Spin up wheels and wait for them to spin up.
        shooterWheels.setBothWheels(-RobotMap.SHOOTER_POWER);
        Timer.delay(4.0);
        
        // Fire Disc 1
        firePiston.fireThenRetract();
        
        // Load Disc 2
        loadPiston.fireThenRetract();
        
        // Fire Disc 2
        firePiston.fireThenRetract();
        
        // Roll Disc 3 in place
        rollers.set(RobotMap.ROLLER_POWER);
        Timer.delay(3.0);
        rollers.stop();
        
        // Load Disc 3
        loadPiston.fireThenRetract();
        
        // Fire Disc 3
        firePiston.fireThenRetract();
        
        // Stop spinning shooter wheels
        shooterWheels.stop();
        
        // Finish the task.
        isFinished = true;
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
