package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Subsystem.Traction;
import frc.robot.Subsystem.IntakeFloor;
import frc.robot.Commands.Tracao.Andar;
import frc.robot.Commands.Tracao.Viradinha;
import frc.robot.Commands.Intakefloor.IntakeDescendo;
import frc.robot.Commands.Intakefloor.IntakeGirando;
import frc.robot.Commands.Intakefloor.IntakeSubindo;

public class Autonomo extends SequentialCommandGroup {
    public Autonomo(Traction traction, IntakeFloor intake) {
        IntakeGirando ejetar = new IntakeGirando(intake, -0.9) {
            @Override
            public void execute() {
                intake.intakeCleitaoMotor.set(-1);
            }
        };
        addCommands(
                new Andar(traction, 0.5, 3),
                new IntakeDescendo(intake),
                new IntakeGirando(intake, 0.9),
                new IntakeSubindo(intake),
                new Andar(traction, -0.5, 3),
                ejetar.withTimeout(2),
                new Viradinha(traction, 0.5, 4));
    }

}
