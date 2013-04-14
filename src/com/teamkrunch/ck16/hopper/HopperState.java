/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.hopper;

import com.teamkrunch.ck16.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author sebastian
 */
public class HopperState {

    public static final boolean BROKEN = false, SOLID = true;
    private DigitalInput rollerBeam, loadBeam, fireBeam;
    
    public HopperState(){
        this.rollerBeam = RobotMap.rollerBeam;
        this.loadBeam = RobotMap.loadBeam;
        this.fireBeam = RobotMap.fireBeam;
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
    
}
