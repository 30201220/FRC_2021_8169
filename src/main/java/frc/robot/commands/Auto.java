/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lolipop;
import frc.robot.subsystems.Shooter;

public class Auto extends CommandBase {
  private DriveTrain m_driveTrain;
  private Intake m_intake;
  private Lolipop m_lolipop;
  private Shooter m_shooter;
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-catch");
  public Auto(DriveTrain driveTrain, Intake intake, Lolipop lolipop, Shooter shooter) {
    m_driveTrain = driveTrain;
    m_intake = intake;
    m_lolipop = lolipop;
    m_shooter = shooter;
    addRequirements(m_driveTrain);
    addRequirements(m_intake);
    addRequirements(m_lolipop);
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrain.setChassisSpeed(0, 0, 0, 0, 0, 0, 0, 0);
    m_intake.setIntakeSolenoid(1);
    m_intake.setIntakeSpeed(0.4);
    m_lolipop.setLolipopSpeed(0.5);
    m_shooter.setDeleverSpeed(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x = table.getEntry("x").getDouble(0.0);
    double y = table.getEntry("y").getDouble(0.0);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setChassisSpeed(0, 0, 0, 0, 0, 0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
