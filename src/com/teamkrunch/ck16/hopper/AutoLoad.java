/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamkrunch.ck16.hopper;

import com.teamkrunch.ck16.loadpiston.PunchDisc;
import com.teamkrunch.ck16.rollers.RunRollersWithBeams;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author sebastian
 */
public class AutoLoad extends CommandGroup {
    
    public AutoLoad() {
        addParallel(new RunRollersWithBeams());
        addParallel(new PunchDisc());
    }
}
