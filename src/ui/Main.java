package ui;

import java.util.Scanner;

import model.Controller;

public class Main {

    private Controller controller;
    
    private Scanner reader;

    public Main() {
        this.reader = new Scanner(System.in);
        this.controller = new Controller();
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
        return "\n" +
                "<< --------------------------------------------------------------------- >>\n" +
                "<< -                               Opciones                            - >>\n" +
                "<< --------------------------------------------------------------------- >>\n" +
                "1. Dar turno \n" +
                "2. Mostrar turno actual \n" +
                "3. Pasar turno\n" +
                "4. Seguir\n" +
                "0. Salir";
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
                break;
            case 0:
                System.out.println("Exit program.");
                break;

            default:
                System.out.println("Invalid Option");
                break;
        }

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
