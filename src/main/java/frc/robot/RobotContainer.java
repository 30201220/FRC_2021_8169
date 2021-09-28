/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CatchBall;
import frc.robot.commands.Climb;
import frc.robot.commands.DriveControl;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lolipop;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public AHRS m_ahrs = new AHRS(SPI.Port.kMXP);
  private Climber m_climber = new Climber();
  private DriveTrain m_driveTrain = new DriveTrain();
  private Intake m_intake = new Intake();
  private Lolipop m_lolipop = new Lolipop();
  private Shooter m_shooter = new Shooter();
  private CatchBall m_catchBall = new CatchBall(m_intake, m_lolipop);
  private Climb m_climb = new Climb(m_climber);
  private DriveControl m_driveControl = new DriveControl(m_driveTrain, m_ahrs);
  private static XboxController xbox1 = new XboxController(Constants.XBOX_DRIVER_ID);
  private static XboxController xbox2 = new XboxController(Constants.XBOX_OPERATOR_ID);
  private Button dButtonStart = new JoystickButton(this.xbox1, Constants.BUTTON_START);
  private Button oButtonStart = new JoystickButton(this.xbox2, Constants.BUTTON_START);
  public RobotContainer() {
    setDefaultCommand();
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    this.oButtonStart.whenPressed(new Shoot(m_shooter, m_lolipop));
  }

  public Command getAutonomousCommand() {
    return null;
  }
  private void setDefaultCommand() {
    m_lolipop.setDefaultCommand(m_catchBall);
    m_climber.setDefaultCommand(m_climb);
    m_driveTrain.setDefaultCommand(m_driveControl);
  }
  public boolean getDriverButton(int axis) {
    return this.xbox1.getRawButton(axis);
  }
  public boolean getOperatorButton(int axis) {
    return this.xbox2.getRawButton(axis);
  }
  public double getDriverRawAxis(int axis) {
    return this.xbox1.getRawAxis(axis);
  }
  public double getOperatorRawAxis(int axis) {
    return this.xbox2.getRawAxis(axis);
  }
  public int getDriverPOV(){
    return this.xbox1.getPOV();
  }
  public int getOperatorPOV(){
    return this.xbox2.getPOV();
  }
}
