package model;

public class Controller {
    private BankTurns bankTurns;
    private int totalTurns;
    private int actualTurn;

    public Controller() {
        this.bankTurns = new BankTurns();
        this.totalTurns = 1;
        this.actualTurn = 1;
    }

    public String giveTurn() {
        if (bankTurns.getTail() != null) {
            totalTurns = bankTurns.getTail().getValue() + 1;
        }

        bankTurns.addNodeAtEnd(totalTurns);
        
        String msg = "Turno " + totalTurns + " agregado con exito";

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

        int aux = bankTurns.searchNodeByValue(bankTurns.getRoot(), actualTurn).getNext().getValue();

        bankTurns.deleteNodeByValue(bankTurns.getRoot(), actualTurn);

        actualTurn = aux;

        return msg;
    }

    public String keepGoing() {
        int aux = bankTurns.searchNodeByValue(bankTurns.getRoot(), actualTurn).getNext().getValue();

        String msg = "Turno " + actualTurn + " atendido.";

        bankTurns.deleteNodeByValue(bankTurns.getRoot(), actualTurn);

        actualTurn = aux;

        return msg;
    }
}
