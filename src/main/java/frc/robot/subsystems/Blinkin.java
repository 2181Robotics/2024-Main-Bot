package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Colors;

public class Blinkin extends SubsystemBase {
    public static final Spark blinkin = new Spark(1);
    public static final Colors m_colors = new Colors();


    public Blinkin () {

    }


    public Command setBlack() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the new color
            () -> {
              blinkin.set(m_colors.black);
             
            },
            // When the command stops, return to default color
            () -> {
              stop();
            });
      }


    public Command setRed() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the new color
            () -> {
              blinkin.set(m_colors.red);
             
            },
            // When the command stops, return to default color
            () -> {
              stop();
            });
      }

  
    public Command setHotPink() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the new color
            () -> {
              blinkin.set(m_colors.hotPink);
             
            },
            // When the command stops, return to default color
            () -> {
              stop();
            });
      }


    public Command setPat1LarScan() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the new color
            () -> {
              blinkin.set(m_colors.pat1_larscan);
             
            },
            // When the command stops, return to default color
            () -> {
              stop();
            });
      }


    public Command setPat2LarScan() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the new color
            () -> {
              blinkin.set(m_colors.pat2_larScan);
             
            },
            // When the command stops, return to default color
            () -> {
              stop();
            });
      }


    public Command setFixRain() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the new color
            () -> {
              blinkin.set(m_colors.fix_rain);
             
            },
            // When the command stops, return to default color
            () -> {
              stop();
            });
      }


    public Command setBlue() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the new color
            () -> {
              blinkin.set(m_colors.blue);
             
            },
            // When the command stops, return to default color
            () -> {
              stop();
            });
      }


    public Command setGreen() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the new color
            () -> {
              blinkin.set(m_colors.green);
             
            },
            // When the command stops, return to default color
            () -> {
              stop();
            });
      }


    public Command setOceanWaves() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the new color
            () -> {
              blinkin.set(m_colors.OceanWaves);
             
            },
            // When the command stops, return to default color
            () -> {
              stop();
            });
      }


      public Command setRedChase() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the new color
            () -> {
              blinkin.set(m_colors.RedChase);
             
            },
            // When the command stops, return to default color
            () -> {
              stop();
            });
      }    


      private int AllianceColorSet() {

        var alliance = DriverStation.getAlliance();
                            if (alliance.isPresent()) {
                                if(alliance.get() == DriverStation.Alliance.Red){
                                  return 1;
                                }else{
                                  return 2;
                                }
                            }
                            return 3;

        }



//Sets default color after each command
      public void stop() {
        if (AllianceColorSet() == 1){
            blinkin.set(m_colors.red);
            }else{if (AllianceColorSet() == 2){
                blinkin.set(m_colors.blue);
                }else{if (AllianceColorSet() == 3){
                    blinkin.set(m_colors.OceanWaves);
                        }else{
                            blinkin.set(m_colors.purple);
                        }
        }
      }
    }

    @Override
    public void periodic() {
    }

    
}