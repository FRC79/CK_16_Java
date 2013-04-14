/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.controllers;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author sebastian
 */
public class BangBangController implements LiveWindowSendable{
    
    private CANJaguar motor;
    private boolean isEnabled, reversed;
    private double targetSpeed;
    
    public BangBangController(CANJaguar referenceMotor){
        motor = referenceMotor;
        isEnabled = false;
        reversed = false;
        targetSpeed = 0.0;
    }
    
    public void calculate()
    {
        if(!isEnabled){ return; }
        
        try
        {
            //If speed is < desired speed, then output = 1.0
            //If speed is anything else, output = 0.0 (STOP)
            if(motor.getSpeed() >= targetSpeed){
                motor.setX(0.0);
            } else {
                motor.setX(reversed ? -1.0 : 1.0);
            }
        }
        catch(CANTimeoutException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void setSetpoint(double targetSpeed){
        this.targetSpeed = targetSpeed;
    }
    
    public void enable(){
        isEnabled = true;
    }
    
    public void disable(){
        isEnabled = false;
    }
    
    public void reverse(boolean isReversed){
        reversed = isReversed;
    }

    private ITable table;
    
    public void initTable(ITable itable) {
        // LOOK AT PIDCONTROLLER.JAVA
    }
    
    public ITable getTable() {
        return table;
    }

    public String getSmartDashboardType() {
        return "BangBangController";
    }
    
    public void updateTable() {
    }

    public void startLiveWindowMode() {
    }

    public void stopLiveWindowMode() {
    }
}
