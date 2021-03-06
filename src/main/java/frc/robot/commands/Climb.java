/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {
  private Climber m_climber;
  private boolean oldStatus = false, status = false, output = false;
  private double climb;
  public Climb(Climber climber) {
    m_climber = climber;
    addRequirements(m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    status = Robot.m_robotContainer.getDriverButton(Constants.BUTTON_A);
    climb = Robot.m_robotContainer.getDriverPOV();
    output = status == oldStatus ? output : status ? !output : output;
    m_climber.setClimberSolenoid(output ? 1 : -1);
    m_climber.setClimberSpeed(climb == 0 ? 0.5 : climb == 180 ? -0.3 : 0);
    oldStatus = status; 
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
