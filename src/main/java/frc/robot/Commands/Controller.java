package frc.robot.Commands;

import frc.robot.Subsystem.Traction;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class Controller extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final Traction traction;
    private final XboxController xbox;
    public double drive;
    public double turn;
    public double velocidade;

    public static boolean turboAtivo = false;

    public Controller(Traction traction, XboxController xbox) {
        this.traction = traction;
        this.xbox = xbox;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(traction);
    }

    public static void toggleTurbo() {
        turboAtivo = !turboAtivo;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drive = 0;
        turn = 0;

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        if (traction.turbo) {
            velocidade = 0.9;

        } else
            velocidade = 0.6;
        /*
         * Talvez o erro que esta acontecendo seja por conta de estar incorreto poderia
         * ser
         * double drive = xbox.getLeftY() * velocidade;
         * double turn = xbox.getRightX() * velocidade;
         * ou precisariamos inverter no codigo
         * double turn = -xbox.getRightX() * velocidade;
         * ou lá no traction.
         */
        turn = xbox.getLeftY() * velocidade;
        drive = xbox.getRightX() * velocidade;

        double max = Math.abs(drive) + Math.abs(turn);
        if (max > 1.0) {
            drive /= max;
            turn /= max;
        }

        traction.arcadeMode(drive, +turn);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        traction.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}