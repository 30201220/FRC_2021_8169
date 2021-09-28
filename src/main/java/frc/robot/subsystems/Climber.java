/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools;

public class Climber extends SubsystemBase {
  private static TalonFX motor1 = new TalonFX(Constants.MOTOR_CLIMBER_1_ID);
  private static TalonFX motor2 = new TalonFX(Constants.MOTOR_CLIMBER_2_ID);
  private static DoubleSolenoid solenoid1 = new DoubleSolenoid(Constants.D_SOLENOID_CLIMBER_F_ID, Constants.D_SOLENOID_CLIMBER_R_ID);
  public Climber() {
    motor1.setInverted(false);
    motor2.setInverted(false);
    solenoid1.set(Value.kOff);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setClimberSpeed(double speed) {
    setMotor1Speed(speed);
    setMotor2Speed(speed);
  }
  public void setClimberSolenoid(int Status) {
    setSolenoid1(Status);
  }
  private void setMotor1Speed(double Value) {
    motor1.set(ControlMode.PercentOutput, Tools.range(Value, 1, -1));
  }
  private void setMotor2Speed(double Value) {
    motor2.set(ControlMode.PercentOutput, Tools.range(Value, 1, -1));
  }
  private void setSolenoid1(int Status) {
    solenoid1.set(Status == 1 ? Value.kForward : Status == -1 ? Value.kReverse : Value.kOff); 
  }
}
