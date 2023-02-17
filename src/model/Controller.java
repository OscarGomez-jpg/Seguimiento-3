package model;

public class Controller {
    private BankTurns bankTurns;
    private int actualTurn;

    public Controller() {
        this.bankTurns = new BankTurns();
        this.actualTurn = 1;
    }

    public String giveTurn() {
        int actualTurn = bankTurns.getTail().getValue() + 1;

        String msg = "Turno " + actualTurn + " agregado con exito";

        bankTurns.addNodeAtEnd(actualTurn);

        return msg;
    }

    public String showTurn() {
        if (bankTurns.searchNodeByValue(bankTurns.getRoot(), actualTurn) == null) {
            return "No hay turnos asignados";
        }
        return "Turno actual: " + actualTurn;
    }

    public String passTurn() {
        String msg = "Eliminando turno " + actualTurn + "...";

        bankTurns.deleteNodeByValue(bankTurns.getRoot(), actualTurn);

        return msg;
    }
}
