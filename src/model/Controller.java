package model;

public class Controller {
    private BankTurns bankTurns;
    private int actualTurn;

    public Controller() {
        this.bankTurns = new BankTurns();
        this.actualTurn = 1;
    }

    public String giveTurn() {
        if (bankTurns.getTail() != null) {
            actualTurn = bankTurns.getTail().getValue() + 1;
        }

        bankTurns.addNodeAtEnd(actualTurn);
        
        String msg = "Turno " + actualTurn + " agregado con exito";

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

        if (actual == null) {
            actualTurn = 1;
            return "No queda nadie en la lista";
        }
        
        if (actual.getTimesPassed() < 3) {
            int timesPassed = actual.getTimesPassed();

            actual.setTimesPassed();

            actualTurn = actual.getNext().getValue();
            
            if (timesPassed == 2) {
                return "Peligro, esta a 1 falta de perder su turno";
            }

            return "Veces que no se ha presentado " + (timesPassed + 1);
        }

        int aux2 = actual.getNext().getValue(); 

        String msg = "Se ha eliminado el turno " + actualTurn + " por no haberse presentado";

        bankTurns.deleteNodeByValue(bankTurns.getRoot(), actualTurn);

        actualTurn = aux2;

        return msg;
    }

    public String keepGoing() {
        Turn actual = bankTurns.searchNodeByValue(bankTurns.getRoot(), actualTurn);
        int aux = 1;
        String msg = "Lista finalizada";

        if (actual == null) {
            actualTurn = 1;
            return "No queda nadie en la lista";
        }

        if (actual.getNext() != null) {
            aux = actual.getNext().getValue();
    
            msg = "Turno " + actualTurn + " atendido.";
        }

        bankTurns.deleteNodeByValue(bankTurns.getRoot(), actualTurn);

        actualTurn = aux;

        return msg;
    }
}
