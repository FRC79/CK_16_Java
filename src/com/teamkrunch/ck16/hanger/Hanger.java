/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.hanger;

import com.teamkrunch.ck16.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author sebastian
 */
public class Hanger extends Subsystem {

    private boolean isExtended;
    private DoubleSolenoid hangA, hangB;
    
    public Hanger(){
        isExtended = false;
        hangA = RobotMap.hangPistonA;
        hangB = RobotMap.hangPistonB;
    }
    
    public void set(boolean state){
        hangA.set(state ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
        hangB.set(!state ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
        isExtended = state;
    }
    
    public boolean isExtended(){
        return isExtended;
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
    
    /* We don't want it to retract on its own because we're going to
     * be inverting it most of the time. */
    public void initDefaultCommand() {}
}
