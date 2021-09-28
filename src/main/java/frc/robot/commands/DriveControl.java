/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class DriveControl extends CommandBase {
  private DriveTrain m_driveTrain;
  private AHRS m_ahrs;
  double length = 1.0;
  double width = 1.0;
  double radius = Math.hypot(length, width);
  double kLengthComponent = length / radius;
  double kWidthComponent = width / radius;
  double kGyroRateCorrection = 0;
  public DriveControl(DriveTrain driveTrain, AHRS ahrs) {
    m_driveTrain = driveTrain;
    m_ahrs = ahrs;
    addRequirements(m_driveTrain);
  }

  @Override
  public void initialize() {
    m_driveTrain.setChassisSpeed(0, 0, 0, 0, 0, 0, 0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double forward = -Robot.m_robotContainer.getDriverRawAxis(Constants.LEFT_STICK_Y);
    double strafe = -Robot.m_robotContainer.getDriverRawAxis(Constants.LEFT_STICK_X);
    double azimuth = -Robot.m_robotContainer.getDriverRawAxis(Constants.RIGHT_STICK_X);
    double angle = -m_ahrs.getAngle();
    angle = Math.IEEEremainder(angle, 360.0);
    angle = Math.toRadians(angle);
    double temp = forward * Math.cos(angle) + strafe * Math.sin(angle);
    strafe = strafe * Math.cos(angle) - forward * Math.sin(angle);
    forward = temp;
    double a = strafe - azimuth * kLengthComponent;
    double b = strafe + azimuth * kLengthComponent;
    double c = forward - azimuth * kWidthComponent;
    double d = forward + azimuth * kWidthComponent;
    double angle1 = Math.atan2(b, c) * 0.5 / Math.PI ;
    double angle2 = Math.atan2(b, d) * 0.5 / Math.PI ;
    double angle3 = Math.atan2(a, d) * 0.5 / Math.PI ;
    double angle4 = Math.atan2(a, c) * 0.5 / Math.PI ;
    double s1 = Math.hypot(b, c);
    double s2 = Math.hypot(b, d);
    double s3 = Math.hypot(a, d);
    double s4 = Math.hypot(a, c);
    double s5 = angle1 - m_driveTrain.getAngle5() < -0.5 ? (angle1 - m_driveTrain.getAngle5() + 1) : angle1 - m_driveTrain.getAngle5() > 0.5 ? angle1 - m_driveTrain.getAngle5() - 1 : angle1 - m_driveTrain.getAngle5();
    double s6 = angle2 - m_driveTrain.getAngle6() < -0.5 ? (angle2 - m_driveTrain.getAngle6() + 1) : angle2 - m_driveTrain.getAngle6() > 0.5 ? angle2 - m_driveTrain.getAngle6() - 1 : angle2 - m_driveTrain.getAngle6();
    double s7 = angle3 - m_driveTrain.getAngle7() < -0.5 ? (angle3 - m_driveTrain.getAngle7() + 1) : angle3 - m_driveTrain.getAngle7() > 0.5 ? angle3 - m_driveTrain.getAngle7() - 1 : angle3 - m_driveTrain.getAngle7();
    double s8 = angle4 - m_driveTrain.getAngle8() < -0.5 ? (angle4 - m_driveTrain.getAngle8() + 1) : angle4 - m_driveTrain.getAngle8() > 0.5 ? angle4 - m_driveTrain.getAngle8() - 1 : angle4 - m_driveTrain.getAngle8();
    double maxWheelSpeed = Math.max(Math.max(s1, s2), Math.max(s3, s4));
    if (maxWheelSpeed > 1.0) {
      s1 /= maxWheelSpeed;
      s2 /= maxWheelSpeed;
      s3 /= maxWheelSpeed;
      s4 /= maxWheelSpeed;
    }
    if (maxWheelSpeed > 0.05) {
      m_driveTrain.setChassisSpeed(s1, s2, s3, s4, s5, s6, s7, s8);
    } else {
      m_driveTrain.setChassisSpeed(0, 0, 0, 0, 0, 0, 0, 0);
    }
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
