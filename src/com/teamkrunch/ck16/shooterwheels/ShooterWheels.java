/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.shooterwheels;

import com.teamkrunch.ck16.RobotMap;
import com.teamkrunch.ck16.controllers.BangBangController;
import com.teamkrunch.ck16.util.CANJagQuadEncoder;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author sebastian
 */
public class ShooterWheels extends Subsystem {

    private CANJaguar frontWheel, backWheel;
    private BangBangController frontBB, backBB;
    
    public ShooterWheels(){
        frontWheel = RobotMap.shooterFrontWheel;
        backWheel = RobotMap.shooterBackWheel;
        frontBB = new BangBangController(frontWheel);
        backBB = new BangBangController(backWheel);
    }
    
    public BangBangController getFrontBBController(){
        return frontBB;
    }
    
    public BangBangController getBackBBController(){
        return backBB;
    }
    
    public double getFrontWheelSpeed(){
        try {
            return frontWheel.getSpeed();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0.0;
    }
    
    public double getBackWheelSpeed(){
        try {
            return backWheel.getSpeed();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0.0;
    }
    
    public void setFrontWheel(float power){
        try {
            frontWheel.setX(power);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setBackWheel(float power){
        try {
            backWheel.setX(power);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setFrontAndBackWheels(float powerFront, float powerBack){
        setFrontWheel(powerFront);
        setBackWheel(powerBack);
    }
    
    public void setBothWheels(float power){
        setFrontAndBackWheels(power, power);
    }
    
    public void stop(){
        setBothWheels(0.0f);
    }
    
    public void initDefaultCommand() {
        /* We want the wheels to defaultly stop after we stop spinning and
         * so that we don't get CAN timeout errors. */
        setDefaultCommand(new StopShooterWheels()); // May need to add false to run continuously
    }
}
