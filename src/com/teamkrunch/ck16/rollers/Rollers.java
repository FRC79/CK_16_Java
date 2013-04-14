/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.rollers;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author sebastian
 */
public class Rollers extends Subsystem {

    public CANJaguar roller;

    public void set(float power) {
        try {
            roller.setX(power);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void stop() {
        set(0.0f);
    }
    
    public void initDefaultCommand() {
        // Stop rollers when nothing is happening
        setDefaultCommand(new StopRollers(true));
    }
}
