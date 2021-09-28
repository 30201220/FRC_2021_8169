/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools;

public class Shooter extends SubsystemBase {
  private static TalonFX motor1 = new TalonFX(Constants.MOTOR_SHOOTER_ID);
  private static VictorSPX motor2 = new VictorSPX(Constants.MOTOR_HEADER_ID);
  private static CANSparkMax motor3 = new CANSparkMax(Constants.MOTOR_DELEVER_ID, MotorType.kBrushless);
  private static DoubleSolenoid solenoid1 = new DoubleSolenoid(Constants.D_SOLENOID_SHOOTER_F_ID, Constants.D_SOLENOID_SHOOTER_R_ID);
  public Shooter() {
    motor1.setInverted(false);
    motor2.setInverted(false);
    motor3.setInverted(false);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setShooterSolenoid(int Status) {
    setSolenoid1(Status);
  }
  public double getShooterSpeed() {
    return getMotor1Speed();
  }
  public void setShooterSpeed(double speed) {
    setMotor1Speed(speed);
  }
  public void setHeaderSpeed(double speed) {
    setMotor2Speed(speed);
  }
  public void setDeleverSpeed(double speed) {
    setMotor3Speed(speed);
  }
  private void setMotor1Speed(double Value) {
    motor1.set(ControlMode.PercentOutput, Tools.range(Value, 1, -1));
  }
  private void setMotor2Speed(double Value) {
    motor2.set(ControlMode.PercentOutput, Tools.range(Value, 1, -1));
  }
  private void setMotor3Speed(double Value) {
    motor3.set(Tools.range(Value, 1, -1));
  }
  private double getMotor1Speed() {
    return motor1.getSelectedSensorVelocity();
  }
  private void setSolenoid1(int Status) {
    solenoid1.set(Status == 1 ? Value.kForward : Status == -1 ? Value.kReverse : Value.kOff);
  }
}
