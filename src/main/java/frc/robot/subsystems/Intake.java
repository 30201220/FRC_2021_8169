/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools;

public class Intake extends SubsystemBase {
  private static CANSparkMax motor1 = new CANSparkMax(Constants.MOTOR_INTAKE_ID, MotorType.kBrushless);
  private static DoubleSolenoid solenoid1 = new DoubleSolenoid(Constants.D_SOLENOID_INTAKE_F_ID, Constants.D_SOLENOID_INTAKE_B_ID);
  public Intake() {
    motor1.setInverted(false);
    solenoid1.set(Value.kOff);
  }
  @Override
  public void periodic() {
  }
  public void setIntakeSpeed(double speed) {
    setMotor1Speed(speed);
  }
  public void setIntakeSolenoid(int Status) {
    setSolenoid1(Status);
  }
  private void setMotor1Speed(double Value) {
    motor1.set(Tools.range(Value, 1, -1));
  }
  private void setSolenoid1(int Status) {
    solenoid1.set(Status == 1 ? Value.kForward : Status == -1 ? Value.kReverse : Value.kOff); 
  }
}
