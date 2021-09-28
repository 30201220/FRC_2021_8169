/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools;

public class Lolipop extends SubsystemBase {
  private static VictorSPX motor1 = new VictorSPX(Constants.MOTOR_LOLIPOP_ID);
  private static DoubleSolenoid solenoid1 = new DoubleSolenoid(Constants.D_SOLENOID_LOLIPOP_F_ID, Constants.D_SOLENOID_LOLIPOP_R_ID);
  public Lolipop() {
    motor1.setInverted(false);
    solenoid1.set(Value.kOff);
  }
  @Override
  public void periodic() {
  }
  public void setLolipopSpeed(double speed) {
    setMotor1Speed(speed);
  }
  public void setLolipopSolenoid(int Status) {
    setSolenoid1(Status);
  }
  private void setMotor1Speed(double Value) {
    motor1.set(ControlMode.PercentOutput, Tools.range(Value, 1, -1));
  }
  private void setSolenoid1(int Status) {
    solenoid1.set(Status == 1 ? Value.kForward : Status == -1 ? Value.kReverse : Value.kOff);
  }
}
