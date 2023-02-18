package ui;

import java.util.Scanner;

import model.Controller;

public class Main {

    private Controller controller;
    
    private Scanner reader;

    private Box container;

    public Main() {
        this.reader = new Scanner(System.in);
        this.controller = new Controller();
        this.container = new Box();
    }

    public static void main(String[] args) {
        Main main = new Main();

        int option = -1;
        do {

            option = main.getOptionShowMenu();
            main.executeOption(option);

        } while (option != 0);
    }

    public int getOptionShowMenu() {
        int option = 0;
        System.out.println(printMenu());

        option = validateIntegerOption();

        return option;
    }

    public String printMenu() {
        String menu = 
                "1. Dar turno \n" +
                "2. Mostrar turno actual \n" +
                "3. Pasar turno\n" +
                "4. Seguir\n" +
                "0. Salir";
        String head = "<< --------------------------------------------------------------------- >>\n" +
        "<< -                               Opciones                            - >>\n" +
        "<< --------------------------------------------------------------------- >>\n";

        menu = container.box(menu, 2, "|", "-");

        container.setMsg("");

        return head + menu;
    }

    public void executeOption(int option) {
        String msg = "";

        switch (option) {
            case 1:
                msg = giveTurn();
                System.out.println(msg);
                break;
            case 2:
                msg = showTurn();
                System.out.println(msg);
                break;
            case 3:
                msg = passTurn();
                System.out.println(msg);
                break;
            case 4:
                msg = keepGoing();
                System.out.println(msg);
                break;
            case 0:
                System.out.println("Exit program.");
                break;

            default:
                System.out.println("Invalid Option");
                break;
        }

        wait(1000);
        cleanConsole();
    }

    public String giveTurn() {
        return controller.giveTurn();
    }

    public String showTurn() {
        return controller.showTurn();
    }

    public String passTurn() {
        return controller.passTurn();
    }

    public String keepGoing() {
        return controller.keepGoing();
    }

    /**
     * This function cleans the console, is just a stetic feature
     */
    private void cleanConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function waits for the time specified in miliseconds
     * 
     * @param miliseconds The miliseconds the program will stop
     */
    private void wait(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function validates if the input of the user is an integer
     * returns -1 if the input is incorrect
     * 
     * @return The number the user digits if it is an int
     */
    public int validateIntegerOption() {
        int option = 0;

        if (reader.hasNextInt()) {
            option = reader.nextInt();
        } else {
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }

    /**
     * This function validates if the input of the user is a double
     * returns -1 if the input is incorrect
     * 
     * @return The number the user digits if it is a double
     */
    public double validateDoubleOption() {
        double option = 0;

        if (reader.hasNextDouble()) {
            option = reader.nextDouble();
        } else {
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }
}
