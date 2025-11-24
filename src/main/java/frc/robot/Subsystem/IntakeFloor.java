package frc.robot.Subsystem;

import com.revrobotics.spark.SparkMax;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeFloor extends SubsystemBase {
    public SparkMax intakeMarlonMotor = new SparkMax(Constants.IntakeFloor.intakeMarlonMotorID, MotorType.kBrushless);
    public SparkMax intakeCleitaoMotor = new SparkMax(Constants.IntakeFloor.intakeCleitaoMotorID, MotorType.kBrushless);
    public final double LimiteSuperior = 270.0;
    public final double LimiteInferior = 0.0;

    private RelativeEncoder intakeMarlonEncoder = intakeMarlonMotor.getEncoder();

    SparkMaxConfig configSparkintakeMarlonMotor = new SparkMaxConfig();
    SparkMaxConfig configSparkintakeCleitaoMotor = new SparkMaxConfig();

    public IntakeFloor() {
        intakeMarlonMotor.configure(
                new SparkMaxConfig()
                        .idleMode(IdleMode.kBrake)
                        .inverted(true)
                        .smartCurrentLimit(50),
                ResetMode.kNoResetSafeParameters,
                PersistMode.kPersistParameters);

        intakeCleitaoMotor.configure(
                new SparkMaxConfig()
                        .idleMode(IdleMode.kBrake)
                        .smartCurrentLimit(50),
                ResetMode.kNoResetSafeParameters,
                PersistMode.kPersistParameters);

        zeroIntakeEncoders();
    }

    public void zeroIntakeEncoders() {
        intakeMarlonEncoder.setPosition(0);
    }

    public double getIntakeMarlonPosition() {
        return intakeMarlonEncoder.getPosition();
    }

    public double getPosicaoGraus() {
        double graus = intakeMarlonEncoder.getPosition() * 360.0;
        graus = ((graus % 360) + 360) % 360;
        return graus;
    }

    private final double LimiteSuperiorGraus = 300.0;
    private final double LimiteInferiorGraus = 75.0;

    public boolean isNoLimiteSuperior() {
        return getPosicaoGraus() >= LimiteSuperiorGraus;
    }

    public boolean isNoLimiteInferior() {
        return getPosicaoGraus() <= LimiteInferiorGraus;
    }

    public void setIntakeMarlonVelocidade(double velocidade) {
        intakeMarlonMotor.set(velocidade);
    }

    public void setRoller(double velocidade) {
        intakeCleitaoMotor.set(velocidade);
    }

    public void stopRoller() {
        intakeCleitaoMotor.stopMotor();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake - Posição Marlon", intakeMarlonEncoder.getPosition());
        SmartDashboard.putNumber("Intake - Velocidade Marlon", intakeMarlonEncoder.getVelocity());

        // Mostra estado dos motores
        SmartDashboard.putNumber("Motor Marlon - Saída", intakeMarlonMotor.get());
        SmartDashboard.putNumber("Motor Cleitao - Saída", intakeCleitaoMotor.get());
    }

    public void stopIntake() {
        intakeMarlonMotor.stopMotor();
        intakeCleitaoMotor.stopMotor();
    }

}
