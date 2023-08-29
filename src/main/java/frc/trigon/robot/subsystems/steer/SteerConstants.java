package frc.trigon.robot.subsystems.steer;

import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.InvertedValue;
import com.ctre.phoenixpro.signals.NeutralModeValue;

public class SteerConstants {
    private static final int MOTOR_ID = 0;

    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;

    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;

    static final TalonFX MOTOR = new TalonFX(MOTOR_ID);

    public static final double GEAR_RATIO = 12.8;

    private static final double
        P = 8.4,
        I = 0,
        D = 0;


    static {
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.Audio.BeepOnBoot = false;
        configuration.MotorOutput.Inverted = INVERTED_VALUE;
        configuration.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;
        configuration.Slot0.kP = P;
        configuration.Slot0.kI = I;
        configuration.Slot0.kD = D;

        MOTOR.getConfigurator().apply(configuration);
    }
}
