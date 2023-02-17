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
        Turn actual = bankTurns.searchNodeByValue(bankTurns.getRoot(), actualTurn);
        
        if (bankTurns.searchNodeByValue(bankTurns.getRoot(), actualTurn).getTimesPassed() < 3) {
            int timesPassed = actual.getTimesPassed();

            actual.setTimesPassed();

            actualTurn = actual.getNext().getValue();
            
            if (timesPassed == 2) {
                return "Peligro, esta a 1 falta de perder su turno";
            }

            return "Veces que no se ha presentado " + (timesPassed + 1);
        }
        
        if (actual == null || actual.getNext() == null) {
            return "No hay mas turnos";
        }

        int aux2 = actual.getNext().getValue(); 

        String msg = "Se ha eliminado el turno " + actualTurn + " por no haberse presentado";

        bankTurns.deleteNodeByValue(bankTurns.getRoot(), actualTurn);

        actualTurn = aux2;

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
