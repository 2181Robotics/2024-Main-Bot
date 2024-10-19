package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Colors;

public class Blinkin extends SubsystemBase {
    public static final Spark blinkin = new Spark(1);
    public static final Colors m_colors = new Colors();

    public static Command setBlack() {
        return new InstantCommand(() -> blinkin.set(m_colors.black));
    }

    public static Command setRed() {
        return new InstantCommand(() -> blinkin.set(m_colors.red));
    }

    public static Command setHotPink() {
        return new InstantCommand(() -> blinkin.set(m_colors.hotPink));
    }

    public static Command setPat1LarScan() {
        return new InstantCommand(() -> blinkin.set(m_colors.pat1_larscan));
    }

    public static Command setPat2LarScan() {
        return new InstantCommand(() -> blinkin.set(m_colors.pat2_larScan));
    }

    public static Command setFixRain() {
        return new InstantCommand(() -> blinkin.set(m_colors.fix_rain));
    }

    public static Command setBlue() {
        return new InstantCommand(() -> blinkin.set(m_colors.blue));
    }

    public static Command setGreen() {
        return new InstantCommand(() -> blinkin.set(m_colors.green));
    }

public static Command setOceanWaves() {
        return new InstantCommand(() -> blinkin.set(m_colors.OceanWaves));
    }

public static Command setRedChase() {
        return new InstantCommand(() -> blinkin.set(m_colors.RedChase));
    }


    @Override
    public void periodic() {
    }

    
}


