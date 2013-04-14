/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.shooterpistons;

import com.teamkrunch.ck16.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author sebastian
 */
public class ShooterPistons extends Subsystem {

    private boolean isTiltedUp, isFirePistonExtended;
    private DoubleSolenoid tilt;
    private Solenoid fire;
    
    public ShooterPistons(){
        isTiltedUp = false;
        isFirePistonExtended = false;
        fire = RobotMap.firePiston;
        tilt = RobotMap.tiltPiston;
    }
    
    public void setFirePiston(boolean state){
        fire.set(state);
        isFirePistonExtended = state;
    }
    
    public boolean isFirePistonExtended(){
        return isFirePistonExtended;
    }
    
    public void extendFirePiston(){
        setFirePiston(true);
    }
    
    public void retractFirePiston(){
        setFirePiston(false);
    }
    
    public void invertCurrentFirePistonState(){
        setFirePiston(!isFirePistonExtended());
    }
    
    public void fireThenRetractFirePiston(){
        extendFirePiston();
        Timer.delay(RobotMap.PISTON_DELAY);
        retractFirePiston();
        Timer.delay(RobotMap.PISTON_DELAY);
    }
    
    public void setTilt(boolean state){
        tilt.set(state ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
        isTiltedUp = state;
    }
    
    public boolean isTiltedUp(){
        return isTiltedUp;
    }
    
    public void TiltUp(){
        setTilt(true);
    }
    
    public void TiltDown(){
        setTilt(false);
    }
    
    public void invertCurrentTiltState(){
        setTilt(!isTiltedUp());
    }
    
    public void initDefaultCommand() {
        /* When the shooter isn't doing anything, we want to retract the fire
         * piston so that we don't have to toggle a button for extending and
         * retracting. We just have to call extend to temporarily override
         * retract. It is important that nothing in shooter uses requires()
         * except RetractFirePiston and ExtendFirePiston. */
        setDefaultCommand(new RetractFirePiston()); // May need to add false to run continuously
    }
}
