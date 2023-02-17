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
        main.start();

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
                "1. Empezar nuevo juego \n" +
                "2. Mostrar tablero \n" +
                "0. Salir";
    }

    public void executeOption(int option) {
        String msg = "";

        switch (option) {
            case 1:
                msg = play();
                System.out.println(msg);
                break;
            case 2:
                System.out.println(controller.printProblemSet());
                break;
            case 0:
                System.out.println("Exit program.");
                break;

            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    /**
     * This function inits the characteristics that the game doesn't need to iterate
     */
    public void start() {
        System.out.println("!!!Bienvenido!!!");

        System.out.print("Ingrese el nombre del jugador: ");

        String userName = reader.next();

        controller.start(userName);

        System.out.print("Ingrese la cantidad de ejercicios que tendra el juego: ");

        int ammount = validateIntegerOption();

        controller.createBoard(ammount);
    }

    public String play() {
        String msg = "";
        int totalProblems = 1;

        cleanConsole();

        while(totalProblems < controller.getBoard().getLength()) {
            Boolean skip = false;

            System.out.println(controller.printProblemSet());

            System.out.println("Ingrese el resultado (ingrese solo un decimal): ");
            System.out.println("En caso de querer saltarse el ejercicio escriba 'paso'");
            
            String answer = reader.next();

            answer.toLowerCase();

            if (answer.equals("paso")) {
                skip = true;
            }

            msg = controller.play(answer, skip);

            System.out.println(msg);

            wait(1000);

            System.out.println("TOtal: " + totalProblems);

            totalProblems += 1;

            cleanConsole();
        }

        return "Su puntaje es de: " + controller.getPlayer().getScore();
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
