// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

import javax.swing.AbstractCellEditor;
import javax.swing.plaf.nimbus.AbstractRegionPainter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
 
private MecanumDrive myRobot;
private Joystick driverStick;
private Joystick turningStick;
Solenoid cannon = new Solenoid(PneumaticsModuleType.CTREPCM, 7);

WPI_TalonFX Frontleft = new WPI_TalonFX(1,"rio");
WPI_TalonFX Rearleft = new WPI_TalonFX(3,"rio");
WPI_TalonFX Frontright = new WPI_TalonFX(2,"rio");
WPI_TalonFX Rearright = new WPI_TalonFX(4,"rio");
Spark liftMotor  = new Spark(0);

@Override
  public void robotInit() {
    
    driverStick = new Joystick(0);
    turningStick = new Joystick(1);

    myRobot = new MecanumDrive(Frontleft, Rearleft, Frontright, Rearright);
    Frontleft.setInverted(true);
    Rearleft.setInverted(true);
    
  }


  @Override
  public void robotPeriodic() {
    double frontLeftVal = Frontleft.getSelectedSensorVelocity(0); /* position units per 100ms */
    double rearLeftVal = Rearleft.getSelectedSensorVelocity(0);
    double frontRightVal = Frontright.getSelectedSensorVelocity(0);
    double rearRightVal = Rearright.getSelectedSensorVelocity(0);

    SmartDashboard.putNumber("Front Left Encoder", frontLeftVal); // displays the velocity
    SmartDashboard.putNumber("Rear Left Encoder", rearLeftVal);
    SmartDashboard.putNumber("Front Right Encoder", frontRightVal);
    SmartDashboard.putNumber("Rear Right Encoder", rearRightVal);
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    myRobot.driveCartesian(driverStick.getY(), -driverStick.getX(), -turningStick.getX());
    //myRobot.driveCartesian(0, 0.2, 0);
    if(driverStick.getRawButton(3)) {
      liftMotor.set(1);
    } else if (driverStick.getRawButton(2)) {
      liftMotor.set(-1);
    } else {
      liftMotor.set(0);
    }
    if(driverStick.getRawButton(1)){
      cannon.set(true);
    } else {
      cannon.set(false);
    }
  }


  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
