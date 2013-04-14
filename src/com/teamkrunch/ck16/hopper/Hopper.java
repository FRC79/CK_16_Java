/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.hopper;

import com.teamkrunch.ck16.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author sebastian
 */
public class Hopper extends Subsystem {

    public static final boolean BROKEN = false, SOLID = true;
    
    private boolean isLoadPistonExtended, isLoadPistonLocked;
    private CANJaguar roller;
    private Solenoid loadPiston;
    private DigitalInput rollerBeam, loadBeam, fireBeam;
    
    public Hopper(){
        isLoadPistonExtended = false;
        roller = RobotMap.rollerMotor;
        this.loadPiston = RobotMap.loadPiston;
        this.rollerBeam = RobotMap.rollerBeam;
        this.loadBeam = RobotMap.loadBeam;
        this.fireBeam = RobotMap.fireBeam;
    }
    
    public void setRoller(float power){
        try {
            roller.setX(power);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void stopRoller(){
        setRoller(0.0f);
    }
    
    public boolean isLoadPistonLocked(){
        return isLoadPistonLocked;
    }
    
    public void setLoadPistonLocked(boolean isLocked){
        isLoadPistonLocked = isLocked;
    }
    
    public void setLoadPiston(boolean state){
        loadPiston.set(state);
        isLoadPistonExtended = state;
    }
    
    public boolean isLoadPistonExtended(){
        return isLoadPistonExtended;
    }
    
    public void extendLoadPiston(){
        setLoadPiston(true);
    }
    
    public void retractLoadPiston(){
        setLoadPiston(false);
    }
    
    public void invertCurrentLoadPistonState(){
        setLoadPiston(!isLoadPistonExtended());
    }
    
    public void fireThenRetractLoadPiston(){
        extendLoadPiston();
        Timer.delay(RobotMap.PISTON_DELAY);
        retractLoadPiston();
        Timer.delay(RobotMap.PISTON_DELAY);
    }
    
    public boolean isDiscUnderneathRollers(){
        return (rollerBeam.get() == BROKEN);
    }
    
    public boolean isDiscReadyToLoad(){
        return (loadBeam.get() == BROKEN);
    }
    
    public boolean isDiscInChamber(){
        return (fireBeam.get() == BROKEN);
    }
    
    public boolean isFull(){
        return (isDiscUnderneathRollers() && isDiscReadyToLoad());
    }
    
    public void initDefaultCommand() {}
}
