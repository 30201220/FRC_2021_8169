/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Lolipop;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
  private Shooter m_shooter;
  private Lolipop m_lolipop;
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-catch");
  private boolean shooterMode = false;
  private boolean oldStatus = false, status = false, output = false;
  public Shoot(Shooter shooter, Lolipop lolipop) {
    m_shooter = shooter;
    m_lolipop = lolipop;
    addRequirements(m_shooter);
    addRequirements(m_lolipop);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_lolipop.setLolipopSpeed(0.5);
    m_shooter.setDeleverSpeed(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x = table.getEntry("tx").getDouble(0.0);
    double y = table.getEntry("ty").getDouble(0.0);
    double dis = Constants.HIGH_SHOOTER_TO_TARGET / Math.tan((y + 24) * Math.PI / 180);
    SmartDashboard.putNumber("dis", dis);
    //shooterMode = dis > 5;
    status = Robot.m_robotContainer.getDriverButton(Constants.BUTTON_Y);
    shooterMode = status == oldStatus ? output : status ? !output : output;
    //
    m_shooter.setShooterSolenoid(shooterMode ? 1 : -1);
    double targetTurnSpeed = shooterMode ? 0.9888 * (-3.4907 * Math.pow(dis, 2) + 191.95 * dis + 4036.7) - 143.91 : 0.9888 * (-3.4907 * Math.pow(dis, 2) + 191.95 * dis + 4036.7) - 143.91;
    double shooterSpeed = m_shooter.getShooterSpeed();
    SmartDashboard.putNumber("shooterSpeed", shooterSpeed);
    SmartDashboard.putNumber("targetSpeed", targetTurnSpeed);
    m_shooter.setShooterSpeed(targetTurnSpeed + (targetTurnSpeed - shooterSpeed) * 1 / Constants.TALONFX_MAX_SPEED);
    m_shooter.setHeaderSpeed((x < 0 ? -Math.pow(-x, 0.5) : Math.pow(x, 0.5)) / 5);
    oldStatus = status;
  }

  // Called once the command ends or is interrupt8ed.
  @Override
  public void end(boolean interrupted) {
    m_shooter.setShooterSolenoid(1);
    m_shooter.setShooterSpeed(0);
    m_lolipop.setLolipopSpeed(0);
    m_shooter.setHeaderSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.m_robotContainer.getOperatorButton(Constants.BUTTON_BACK);
  }
}
