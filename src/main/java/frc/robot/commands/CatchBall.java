/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lolipop;

public class CatchBall extends CommandBase {
  private Intake m_intake;
  private Lolipop m_lolipop;
  private boolean oldStatusX = false, statusX = false, outputX = false;
  private boolean oldStatusB = false, statusB = false, outputB = false;
  public CatchBall(Intake intake, Lolipop lolipop) {
    m_intake = intake;
    m_lolipop = lolipop;
    addRequirements(m_intake);
    addRequirements(m_lolipop);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    statusB = Robot.m_robotContainer.getDriverButton(Constants.BUTTON_B);
    statusX = Robot.m_robotContainer.getDriverButton(Constants.BUTTON_X);
    outputB = statusB == oldStatusB ? outputB : statusB ? !outputB : outputB;
    outputX = statusX == oldStatusX ? outputX : statusX ? !outputX : outputX;
    m_intake.setIntakeSpeed(outputB ? 0.4 : 0);
    m_lolipop.setLolipopSpeed(outputB ? 0.5 : 0);
    SmartDashboard.putBoolean("intakeMotor", outputB);
    m_intake.setIntakeSolenoid(outputX ? 1 : -1);
    oldStatusB = statusB;
    oldStatusX = statusX;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
