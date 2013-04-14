/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.loadpiston;

import com.teamkrunch.ck16.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author sebastian
 */
public class LoadPiston extends Subsystem {
    
    boolean isExtended, isLocked;
    public Solenoid loadPiston;

    public boolean isLocked() {
        return isLocked;
    }
    
    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }
    
    public boolean isExtended() {
        return isExtended;
    }
    
    public void set(boolean state) {
        loadPiston.set(state);
        isExtended = state;
    }

    public void extend() {
        set(true);
    }

    public void retract() {
        set(false);
    }

    public void fireThenRetract() {
        extend();
        Timer.delay(RobotMap.PISTON_DELAY);
        retract();
        Timer.delay(RobotMap.PISTON_DELAY);
    }

    public void invertCurrentState() {
        set(!isExtended());
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new RetractLoadPiston(true));
    }
}
