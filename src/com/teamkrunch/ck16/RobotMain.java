/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.teamkrunch.ck16;

import com.teamkrunch.ck16.autonomous.SimpleBackPyrShoot;
import com.teamkrunch.ck16.autonomous.SimpleFrontPyrShoot;
import com.teamkrunch.ck16.compressor.RunCompressor;
import com.teamkrunch.ck16.drivetrain.ArcadeDrive;
import com.teamkrunch.ck16.firepiston.ExtendFirePiston;
import com.teamkrunch.ck16.hanger.InvertHangerState;
import com.teamkrunch.ck16.hopper.AutoLoad;
import com.teamkrunch.ck16.loadpiston.ExtendLoadPiston;
import com.teamkrunch.ck16.loadpiston.RetractLoadPiston;
import com.teamkrunch.ck16.misc.CancelCommand;
import com.teamkrunch.ck16.misc.DoNothing;
import com.teamkrunch.ck16.misc.Wait;
import com.teamkrunch.ck16.rollers.RollDiscIn;
import com.teamkrunch.ck16.rollers.RollDiscOut;
import com.teamkrunch.ck16.shooterwheels.SpinShooterWheels;
import com.teamkrunch.ck16.tiltpiston.InvertTiltState;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot {
    SendableChooser autonChooser;
    Command tiltCommand, shooterWheelsCommand, autoLoadCommand, 
            arcadeDriveCommand, compressorCommand, autonCommand;
    long disabledPeriodicLoops;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // Initialize all components and subsystems
        RobotMap.init();
        CommandBase.init();
        
        // Add LiveWindow components
        LiveWindow.addSensor("ShooterWheels", "Front Encoder", RobotMap.shooterFrontEncoder);
        LiveWindow.addSensor("ShooterWheels", "Back Encoder", RobotMap.shooterBackEncoder);
        
        // Initialize autonomous selector on the SmartDashboard
        autonCommand = new DoNothing();
        autonChooser = new SendableChooser();
        autonChooser.addDefault("Shoot 3 from Back of Pyramid", new SimpleBackPyrShoot());
        autonChooser.addObject("Shoot 2 from Front of Pyramid", new SimpleFrontPyrShoot());
        SmartDashboard.putData("Autonomous Modes", autonChooser); // Send chooser to SmartDashboard
        
        // Initialize Commands
        compressorCommand = new RunCompressor();
        arcadeDriveCommand = new ArcadeDrive();
        autoLoadCommand = new AutoLoad();
        tiltCommand = new InvertTiltState();
        shooterWheelsCommand = new SpinShooterWheels(-RobotMap.SHOOTER_POWER, true);
        
        // Initialize JoystickButtons
        CommandBase.oi.buttonInvertTiltJoy1.whenPressed(!tiltCommand.isRunning() ? tiltCommand : new DoNothing());
        CommandBase.oi.buttonInvertTiltJoy2.whenPressed(!tiltCommand.isRunning() ? tiltCommand : new DoNothing());
        CommandBase.oi.buttonInvertHangPiston.whenPressed(new InvertHangerState());
        CommandBase.oi.buttonToggleAutoLoad.whenPressed(!autoLoadCommand.isRunning() ? 
                autoLoadCommand : new CancelCommand(autoLoadCommand));
        CommandBase.oi.buttonExtendFirePiston.whileHeld(new ExtendFirePiston(true));
        CommandBase.oi.buttonExtendFirePiston.whenReleased(new Wait(RobotMap.AUTOLOAD_START_DELAY, 
                CommandBase.firePiston));
        CommandBase.oi.buttonForwardRollers.whileHeld(new RollDiscIn(true));
        CommandBase.oi.buttonReverseRollers.whileHeld(new RollDiscOut(true));
        CommandBase.oi.buttonManualLoadPiston.whenPressed(new CancelCommand(autoLoadCommand));
        CommandBase.oi.buttonManualLoadPiston.whileHeld(new ExtendLoadPiston(true));
        CommandBase.oi.buttonManualLoadPiston.whenReleased(new RetractLoadPiston());
        CommandBase.oi.buttonToggleShooterWheels.whenPressed(!shooterWheelsCommand.isRunning() ? 
                shooterWheelsCommand : new CancelCommand(shooterWheelsCommand));
        
        System.out.println("RobotInit() completed.");
        System.out.println("TEAM 79 FOR THE WIN!");
    }

    public void autonomousInit() {
        // Start parallel commands for auto
        compressorCommand.start();
        // Dynamically load chosen autonomous mode.
        autonCommand = (Command)autonChooser.getSelected();
        autonCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // Start parallel commands for teleop
        compressorCommand.start();
        arcadeDriveCommand.start();
        autoLoadCommand.start();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    public void testInit() {
        
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }

    public void disabledInit() {
        // Add periodic loops later.
        
        // Cancel anything when the robot is disabled here.
    }
    
}
