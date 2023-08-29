package frc.trigon.robot.subsystems.turret;


import com.ctre.phoenixpro.controls.VoltageOut;
import com.ctre.phoenixpro.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.Supplier;

public class Turret extends SubsystemBase {
    private final static Turret INSTANCE = new Turret();
    private final TalonFX motor = TurretConstants.MOTOR;
    private final PIDController pidController = TurretConstants.PID_CONTROLLER;

    public static Turret getInstance() {
        return INSTANCE;
    }

    private Turret() {
    }

    /**
     * Creates a new command that align the turret to the middle of the screen of the limelight.
     *
     * @param reflectorPixelSupplier supplies the pixels of the reflector on the screen
     * @param hasTargetSupplier      supplies if the limelight sees the reflector
     * @return the command
     */
    public CommandBase getAlignToReflectorCommand(Supplier<Double> reflectorPixelSupplier, Supplier<Boolean> hasTargetSupplier) {
        return new FunctionalCommand(
                () -> {
                },
                () -> alignToReflector(reflectorPixelSupplier.get(), hasTargetSupplier.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    private void alignToReflector(double reflectorPixel, boolean hasTarget) {
        if (!hasTarget) {
            VoltageOut request = new VoltageOut(5);
            motor.setControl(request);
            return;
        }
        double output = pidController.calculate(reflectorPixel);
        VoltageOut request = new VoltageOut(output);
        motor.setControl(request);
    }

    private void stop() {
        motor.stopMotor();
    }
}

