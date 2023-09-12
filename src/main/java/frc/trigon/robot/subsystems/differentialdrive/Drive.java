package frc.trigon.robot.subsystems.differentialdrive;


import com.ctre.phoenixpro.hardware.TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.trigon.robot.subsystems.steer.SteerConstants;

import java.util.function.Supplier;

public class Drive extends SubsystemBase {
    private final static Drive INSTANCE = new Drive();
    private final DifferentialDrive myDrive = DriveConstants.DRIVE;
    public static Drive getInstance() {
        return INSTANCE;
    }
    private Drive() {
    }

    public CommandBase tankDriveCommand(Supplier<Double> leftPowerSupplier, Supplier<Double> rightPowerSupplier){
        return new FunctionalCommand(
                () -> {},
                () -> tankDrive(leftPowerSupplier.get(), rightPowerSupplier.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    public CommandBase arcadeDriveCommand(Supplier<Double> speedSupplier, Supplier<Double> rotationSupplier){
        return new FunctionalCommand(
                () -> {},
                () -> arcadeDrive(speedSupplier.get(), rotationSupplier.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    public CommandBase curvatureDriveCommand(Supplier<Double> speedSupplier, Supplier<Double> rotationSupplier, boolean enableRotation){
        return new FunctionalCommand(
                () -> {},
                () -> curvatureDrive(speedSupplier.get(), rotationSupplier.get(), enableRotation  ),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    public void curvatureDrive(double speed, double rotation, boolean enableRotation) {
        myDrive.curvatureDrive(speed, rotation, enableRotation);
    }

    public void tankDrive(double left_power, double right_power){
        myDrive.tankDrive(left_power, right_power);
    }

    public void arcadeDrive(double speed, double rotation){
        myDrive.arcadeDrive(speed, rotation);
    }

    private void stop() {
        myDrive.stopMotor();
    }

}

