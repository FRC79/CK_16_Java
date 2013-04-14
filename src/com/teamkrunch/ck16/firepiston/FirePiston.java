/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.firepiston;

import com.teamkrunch.ck16.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author sebastian
 */
public class FirePiston extends Subsystem {
    private boolean isFirePistonExtended;
    private Solenoid fire;
    
    public FirePiston(){
        isFirePistonExtended = false;
        fire = RobotMap.firePiston;
    }
    
    public void set(boolean state){
        fire.set(state);
        isFirePistonExtended = state;
    }
    
    public boolean isExtended(){
        return isFirePistonExtended;
    }
    
    public void extend(){
        set(true);
    }
    
    public void retract(){
        set(false);
    }
    
    public void invertCurrentState(){
        set(!isExtended());
    }
    
    public void fireThenRetract(){
        extend();
        Timer.delay(RobotMap.PISTON_DELAY);
        retract();
        Timer.delay(RobotMap.PISTON_DELAY);
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
