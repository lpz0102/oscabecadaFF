package frc.robot.Commands.Intakefloor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.IntakeFloor;

public class IntakeSubindo extends Command {
    private final IntakeFloor intake;
    private final double velocidadeSubindo = -0.6;
    private final double posicaoSubindo = 0.0;

    public IntakeSubindo(IntakeFloor intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.intakeMarlonMotor.set(velocidadeSubindo);
    }

    @Override
    public void end(boolean interrupted) {
        intake.intakeMarlonMotor.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
