package frc.trigon.robot.subsystems.steer;


import com.ctre.phoenixpro.controls.PositionVoltage;
import com.ctre.phoenixpro.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.*;

import java.util.function.Supplier;

public class Steer extends SubsystemBase {
    private final static Steer INSTANCE = new Steer();

    private final TalonFX motor = SteerConstants.MOTOR;

    public static Steer getInstance() {
        return INSTANCE;
    }

    private Steer() {
    }

    /**
     * Creates a new command that sets the target angle from the supplier.
     * @param angleSupplier supplies the current angle
     * @return the command
     */
    public CommandBase getSetTargetAngleCommand(Supplier<Double> angleSupplier) {
        return new FunctionalCommand(
                () -> {
                },
                () -> setTargetAngle(angleSupplier.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    /**
     * Creates a command that sets the target angle.
     * @param targetAngle target angle
     * @return the command
     */
    public CommandBase getSetTargetAngleCommand(double targetAngle) {
        return new StartEndCommand(
                () -> setTargetAngle(targetAngle),
                this::stop,
                this
        );
    }

    /**
     * @return a command that turns to angle 90, waits 3 seconds, then turns to angle 180, waits 3 seconds, turns to angle 0
     */
    public CommandBase getAngleSequenceCommand() {
        return new SequentialCommandGroup(
                getSetTargetAngleCommand(90).withTimeout(3),
                getSetTargetAngleCommand(180).withTimeout(3),
                getSetTargetAngleCommand(0)
        );
    }

    private void setTargetAngle(double angle) {
        double systemRevolution = angle / 360;
        double motorRevolution = systemRevolution * SteerConstants.GEAR_RATIO;
        PositionVoltage request = new PositionVoltage(motorRevolution);
        motor.setControl(request);
    }

    private void stop() {
        motor.stopMotor();
    }
}

