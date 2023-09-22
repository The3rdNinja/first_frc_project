package frc.trigon.robot.subsystems.sidecollector;

import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.InvertedValue;
import com.ctre.phoenixpro.signals.NeutralModeValue;

public class SideCollectorConstants {
    private static final int LIFTER_MOTOR_ID = 1;
    private static final int COLLECTOR_MOTOR_ID = 2;

    private static final InvertedValue LIFTER_INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;
    private static final InvertedValue COLLECTOR_INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;

    private static final NeutralModeValue LIFTER_NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;
    private static final NeutralModeValue COLLECTOR_NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;

    private static final double LIFTER_OFFSET = 0;


    static final TalonFX LIFTER_MOTOR = new TalonFX(LIFTER_MOTOR_ID);
    static final TalonFX COLLECTOR_MOTOR = new TalonFX(COLLECTOR_MOTOR_ID);

    private static final double
            P = 8.4,
            I = 0,
            D = 0;


    static {
        TalonFXConfiguration lifterConfiguration = new TalonFXConfiguration();
        lifterConfiguration.Audio.BeepOnBoot = false;
        lifterConfiguration.MotorOutput.Inverted = LIFTER_INVERTED_VALUE;
        lifterConfiguration.MotorOutput.NeutralMode = LIFTER_NEUTRAL_MODE_VALUE;
        lifterConfiguration.Feedback.FeedbackRotorOffset = LIFTER_OFFSET;

        lifterConfiguration.Slot0.kP = P;
        lifterConfiguration.Slot0.kI = I;
        lifterConfiguration.Slot0.kD = D;

        LIFTER_MOTOR.getConfigurator().apply(lifterConfiguration);

        TalonFXConfiguration collectorConfiguration = new TalonFXConfiguration();
        collectorConfiguration.Audio.BeepOnBoot = false;
        collectorConfiguration.MotorOutput.Inverted = COLLECTOR_INVERTED_VALUE;
        collectorConfiguration.MotorOutput.NeutralMode = COLLECTOR_NEUTRAL_MODE_VALUE;
        COLLECTOR_MOTOR.getConfigurator().apply(collectorConfiguration);
    }

    public enum SideShooterStates {
        COLLECTION(-1, 0),
        HIGH(1, 40),
        MIDDLE(1, 50);

        public final double voltage;
        public final double angle;

        private SideShooterStates(double voltage, double angle) {
            this.voltage = voltage;
            this.angle = angle;
        }
    }
}
