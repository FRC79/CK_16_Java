package com.teamkrunch.ck16;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.teamkrunch.ck16.OI;
import com.teamkrunch.ck16.drivetrain.Drivetrain;
import com.teamkrunch.ck16.hanger.Hanger;
import com.teamkrunch.ck16.hopper.HopperState;
import com.teamkrunch.ck16.firepiston.FirePiston;
import com.teamkrunch.ck16.loadpiston.LoadPiston;
import com.teamkrunch.ck16.rollers.Rollers;
import com.teamkrunch.ck16.shooterwheels.ShooterWheels;
import com.teamkrunch.ck16.tiltpiston.TiltPiston;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    
    // Create a single static instance of all of your subsystems
    public static Hanger hanger;
    public static FirePiston firePiston;
    public static ShooterWheels shooterWheels;
    public static TiltPiston tiltPiston;
    public static Drivetrain drivetrain;
    public static LoadPiston loadPiston;
    public static Rollers rollers;
    public static HopperState hopperState;
    
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        
        // Show what command your subsystem is running on the SmartDashboard
        hanger = new Hanger();
        firePiston = new FirePiston();
        shooterWheels = new ShooterWheels();
        tiltPiston = new TiltPiston();
        drivetrain = new Drivetrain();
        loadPiston = new LoadPiston();
        rollers = new Rollers();
        hopperState = new HopperState();
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
