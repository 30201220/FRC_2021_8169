/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Tools;

public class DriveTrain extends SubsystemBase {
  private static CANSparkMax motor1 = new CANSparkMax(Constants.MOTOR_CHASSIS_1_ID, MotorType.kBrushless);
  private static CANSparkMax motor2 = new CANSparkMax(Constants.MOTOR_CHASSIS_2_ID, MotorType.kBrushless);
  private static CANSparkMax motor3 = new CANSparkMax(Constants.MOTOR_CHASSIS_3_ID, MotorType.kBrushless);
  private static CANSparkMax motor4 = new CANSparkMax(Constants.MOTOR_CHASSIS_4_ID, MotorType.kBrushless);
  private static CANSparkMax motor5 = new CANSparkMax(Constants.MOTOR_CHASSIS_5_ID, MotorType.kBrushless);
  private static CANSparkMax motor6 = new CANSparkMax(Constants.MOTOR_CHASSIS_6_ID, MotorType.kBrushless);
  private static CANSparkMax motor7 = new CANSparkMax(Constants.MOTOR_CHASSIS_7_ID, MotorType.kBrushless);
  private static CANSparkMax motor8 = new CANSparkMax(Constants.MOTOR_CHASSIS_8_ID, MotorType.kBrushless);
  private static CANEncoder encoder1 = new CANEncoder(motor1);
  private static CANEncoder encoder2 = new CANEncoder(motor2);
  private static CANEncoder encoder3 = new CANEncoder(motor3);
  private static CANEncoder encoder4 = new CANEncoder(motor4);
  private static CANEncoder encoder5 = new CANEncoder(motor5);
  private static CANEncoder encoder6 = new CANEncoder(motor6);
  private static CANEncoder encoder7 = new CANEncoder(motor7);
  private static CANEncoder encoder8 = new CANEncoder(motor8);
  public DriveTrain() {
    motor1.setInverted(false);
    motor2.setInverted(false);
    motor3.setInverted(false);
    motor4.setInverted(false);
    motor5.setInverted(false);
    motor6.setInverted(false);
    motor7.setInverted(false);
    motor8.setInverted(false);
  }
  @Override
  public void periodic() {
  }
  public void setChassisSpeed(double s1, double s2, double s3, double s4, double s5, double s6, double s7, double s8) {
    setMotor1Speed(s1);
    setMotor2Speed(s2);
    setMotor3Speed(s3);
    setMotor4Speed(s4);
    setMotor4Speed(s5);
    setMotor6Speed(s6);
    setMotor7Speed(s7);
    setMotor8Speed(s8);
  }
  public void setEncoderZero() {
    encoder1.setPosition(0);
    encoder2.setPosition(0);
    encoder3.setPosition(0);
    encoder4.setPosition(0);
    encoder5.setPosition(0);
    encoder6.setPosition(0);
    encoder7.setPosition(0);
    encoder8.setPosition(0);
  }
  public double getAngle5() {
    return getPosition5() >=  0 ? (getPosition5() / Constants.N_MOTOR_TO_WHEEL) % 1 : (getPosition5() / Constants.N_MOTOR_TO_WHEEL) % 1 + 1;
  }
  public double getAngle6() {
    return getPosition6() >=  0 ? (getPosition6() / Constants.N_MOTOR_TO_WHEEL) % 1 : (getPosition6() / Constants.N_MOTOR_TO_WHEEL) % 1 + 1;
  }
  public double getAngle7() {
    return getPosition7() >=  0 ? (getPosition7() / Constants.N_MOTOR_TO_WHEEL) % 1 : (getPosition7() / Constants.N_MOTOR_TO_WHEEL) % 1 + 1;
  }
  public double getAngle8() {
    return getPosition8() >=  0 ? (getPosition8() / Constants.N_MOTOR_TO_WHEEL) % 1 : (getPosition8() / Constants.N_MOTOR_TO_WHEEL) % 1 + 1;
  }
  public double getPosition1() {
    return encoder1.getPosition();
  }
  public double getPosition2() {
    return encoder2.getPosition();
  }
  public double getPosition3() {
    return encoder3.getPosition();
  }
  public double getPosition4() {
    return encoder4.getPosition();
  }
  public double getPosition5() {
    return encoder5.getPosition();
  }
  public double getPosition6() {
    return encoder6.getPosition();
  }
  public double getPosition7() {
    return encoder7.getPosition();
  }
  public double getPosition8() {
    return encoder8.getPosition();
  }
  private void setMotor1Speed(double Value) {
    motor1.set(Tools.range(Value, 1, -1));
  }
  private void setMotor2Speed(double Value) {
    motor2.set(Tools.range(Value, 1, -1));
  }
  private void setMotor3Speed(double Value) {
    motor3.set(Tools.range(Value, 1, -1));
  }
  private void setMotor4Speed(double Value) {
    motor4.set(Tools.range(Value, 1, -1));
  }
  private void setMotor5Speed(double Value) {
    motor5.set(Tools.range(Value, 1, -1));
  }
  private void setMotor6Speed(double Value) {
    motor6.set(Tools.range(Value, 1, -1));
  }
  private void setMotor7Speed(double Value) {
    motor7.set(Tools.range(Value, 1, -1));
  }
  private void setMotor8Speed(double Value) {
    motor8.set(Tools.range(Value, 1, -1));
  }
}
