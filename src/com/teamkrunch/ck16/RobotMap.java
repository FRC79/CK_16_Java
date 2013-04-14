package com.teamkrunch.ck16;

import com.teamkrunch.ck16.util.CANJagQuadEncoder;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
 
    public static final int TICS_PER_REV = 250;
    public static final float SHOOTER_POWER = 0.60f;
    public static final float ROLLER_POWER = 0.50f;
    public static final double PISTON_DELAY = 0.15;
    public static final double ROLLER_TO_PUNCH_ZONE_DELAY = 0.5;
    public static final double AUTOLOAD_START_DELAY = 0.5;
    
    public static DoubleSolenoid hangPistonA, hangPistonB;
    public static DoubleSolenoid tiltPiston;
    public static Solenoid firePiston;
    public static Solenoid loadPiston;
    public static DigitalInput rollerBeam, loadBeam, fireBeam;
    public static CANJaguar rollerMotor;
    public static CANJaguar shooterFrontWheel, shooterBackWheel;
    public static CANJagQuadEncoder shooterFrontEncoder, shooterBackEncoder;
    public static CANJaguar frontLeftWheel, frontRightWheel, rearLeftWheel,
            rearRightWheel;
    public static RobotDrive robotDrive;
    public static Relay compressor;
    public static DigitalInput pressureSwitch;
    
    public static void init(){
        loadComponents(true); // Used to init CSVs and all components
    }
    
    public static void reload(){
        loadComponents(false); // Reload CSV values without reiniting CSVReaders.
    }
    
    private static void loadComponents(boolean initCSVs)
    {
        // Init Solenoids
        hangPistonA = new DoubleSolenoid(1, 2);
        hangPistonB = new DoubleSolenoid(5, 6);
        tiltPiston = new DoubleSolenoid(3, 4);
        firePiston = new Solenoid(7);
        loadPiston = new Solenoid(8);
        
        // Init Beam Sensors
        rollerBeam = new DigitalInput(3);
        loadBeam = new DigitalInput(4);
        fireBeam = new DigitalInput(2);
        
        // Init Roller Motor
        try {
            rollerMotor = new CANJaguar(8);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        
        // Init Shooter Wheels
        try {
            shooterFrontWheel = new CANJaguar(4);
            shooterBackWheel = new CANJaguar(5);
            shooterFrontEncoder = new CANJagQuadEncoder(shooterFrontWheel, 
                    CANJagQuadEncoder.ControlMode.kSpeed, TICS_PER_REV);
            shooterBackEncoder = new CANJagQuadEncoder(shooterBackWheel, 
                    CANJagQuadEncoder.ControlMode.kSpeed, TICS_PER_REV);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        
        // Init Drive Wheels
        try {
            frontLeftWheel = new CANJaguar(2);
            frontRightWheel = new CANJaguar(6);
            rearLeftWheel = new CANJaguar(3);
            rearRightWheel = new CANJaguar(7);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        
        // Init RobotDrive
        robotDrive = new RobotDrive(frontLeftWheel, rearLeftWheel, 
                frontRightWheel, rearRightWheel);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        
        // Init compressor and pressure switch
        compressor = new Relay(1);
        pressureSwitch = new DigitalInput(1);
    }
}
