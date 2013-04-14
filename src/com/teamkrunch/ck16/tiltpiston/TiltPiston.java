/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.tiltpiston;

import com.teamkrunch.ck16.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author sebastian
 */
public class TiltPiston extends Subsystem {
    
    public boolean isTiltedUp;
    public DoubleSolenoid tilt;
    
    public TiltPiston(){
        isTiltedUp = false;
        tilt = RobotMap.tiltPiston;
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
    }
}
