package frc.trigon.robot.subsystems.differentialdrive;

import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.InvertedValue;
import com.ctre.phoenixpro.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class DriveConstants {
    private static final int FRONT_RIGHT_MOTOR_ID = 1;
    private static final int BACK_RIGHT_MOTOR_ID = 2;
    private static final int FRONT_LEFT_MOTOR_ID = 3;
    private static final int BACK_LEFT_MOTOR_ID = 4;
    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;

    private static final NeutralModeValue NEUTRAL_MODE_VALUE = NeutralModeValue.Brake;

    static final TalonFX FRONT_LEFT_MOTOR = new TalonFX(FRONT_LEFT_MOTOR_ID);
    static final TalonFX BACK_LEFT_MOTOR = new TalonFX(BACK_LEFT_MOTOR_ID);
    static final TalonFX FRONT_RIGHT_MOTOR = new TalonFX(FRONT_RIGHT_MOTOR_ID);
    static final TalonFX BACK_RIGHT_MOTOR = new TalonFX(BACK_RIGHT_MOTOR_ID);

    static final MotorControllerGroup left_group = new MotorControllerGroup(FRONT_LEFT_MOTOR, BACK_LEFT_MOTOR);
    static final MotorControllerGroup right_group = new MotorControllerGroup(FRONT_RIGHT_MOTOR, BACK_RIGHT_MOTOR);
    static final DifferentialDrive DRIVE = new DifferentialDrive(left_group, right_group);

    static {
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.Audio.BeepOnBoot = false;
        configuration.MotorOutput.Inverted = INVERTED_VALUE;
        configuration.MotorOutput.NeutralMode = NEUTRAL_MODE_VALUE;

        BACK_LEFT_MOTOR.getConfigurator().apply(configuration);
        FRONT_LEFT_MOTOR.getConfigurator().apply(configuration);
        BACK_RIGHT_MOTOR.getConfigurator().apply(configuration);
        FRONT_RIGHT_MOTOR.getConfigurator().apply(configuration);
    }
}
