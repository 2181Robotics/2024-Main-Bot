package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartdashboardItem {
private final String m_module;

    public SmartdashboardItem(String module){
        m_module = module;
    }

    public void setBoolean(boolean value){
        SmartDashboard.putBoolean(m_module, value);
    }

    public void setNumber(double value){
        SmartDashboard.putNumber(m_module, value);
    }

    public double getNumber(){
        double numberValue;
        numberValue = SmartDashboard.getNumber(m_module, 0);
        return numberValue;
    }
}
