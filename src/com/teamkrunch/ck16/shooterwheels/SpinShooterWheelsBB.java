/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.shooterwheels;

import com.teamkrunch.ck16.CommandBase;
import com.teamkrunch.ck16.controllers.BangBangController;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author sebastian
 */
public class SpinShooterWheelsBB extends CommandBase {
    
    public static final double GEAR_RATIO = 1.0 / 2.5;
    private BangBangController frontCtrl, backCtrl;
    private double frontSpeed, backSpeed;
    
    public SpinShooterWheelsBB(double front_rpms) {
        this(front_rpms, front_rpms * GEAR_RATIO);
    }
    
    public SpinShooterWheelsBB(double front_rpms, double back_rpms){
        requires(shooterWheels);
        frontSpeed = front_rpms;
        backSpeed = back_rpms;
        frontCtrl = shooterWheels.getFrontBBController();
        backCtrl = shooterWheels.getBackBBController();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        frontCtrl.setSetpoint(frontSpeed);
        backCtrl.setSetpoint(backSpeed);
        frontCtrl.enable();
        backCtrl.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Timer.delay(0.05); // Timeout delay
        frontCtrl.calculate();
        backCtrl.calculate();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        frontCtrl.disable();
        backCtrl.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
