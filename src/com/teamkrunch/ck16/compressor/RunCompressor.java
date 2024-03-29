/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.compressor;

import com.teamkrunch.ck16.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author sebastian
 */
public class RunCompressor extends Command {
    
    private Relay compressor;
    private DigitalInput pressureSwitch;
    
    public RunCompressor() {
        this.compressor = RobotMap.compressor;
        this.pressureSwitch = RobotMap.pressureSwitch;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        compressor.set(!pressureSwitch.get() ? Relay.Value.kForward : Relay.Value.kOff);
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
