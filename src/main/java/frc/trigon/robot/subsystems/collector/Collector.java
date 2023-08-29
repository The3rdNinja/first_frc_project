package frc.trigon.robot.subsystems.collector;


import com.ctre.phoenixpro.controls.VoltageOut;
import com.ctre.phoenixpro.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Collector extends SubsystemBase {
    private final static Collector INSTANCE = new Collector();
    private final TalonFX motor = CollectorConstants.MOTOR;

    public static Collector getInstance() {
        return INSTANCE;
    }

    private Collector() {
    }

    /**
     * @return a command to collect
     */
    public CommandBase getCollectCommand() {
        return new StartEndCommand(
                this::collect,
                this::stop,
                this
        );
    }

    /**
     * @return a command that ejects
     */
    public CommandBase getEjectCommand() {
        return new StartEndCommand(
                this::eject,
                this::stop,
                this
        );
    }

    /**
     * @return a command that collects, waits 3 seconds then eject
     */
    public CommandBase getCollectThenEjectCommand() {
        return new SequentialCommandGroup(
                getCollectCommand().withTimeout(3),
                getEjectCommand().withTimeout(3));
    }

    private void collect() {
        VoltageOut request = new VoltageOut(CollectorConstants.COLLECT_VOLTAGE);
        motor.setControl(request);
    }

    private void eject() {
        VoltageOut request = new VoltageOut(CollectorConstants.EJECT_VOLTAGE);
        motor.setControl(request);
    }

    private void stop() {
        motor.stopMotor();
    }

}

