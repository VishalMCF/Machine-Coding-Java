package com.gatomalvado.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import com.gatomalvado.tictactoe.model.Move;
import com.gatomalvado.tictactoe.model.Symbol;
import com.gatomalvado.tictactoe.model.User;
import com.gatomalvado.tictactoe.orchestrator.TicTacToeGame;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Tic tac Toe");
        System.out.println("Enter number of users -> ");
        Scanner scanner = new Scanner(System.in);
        int numberOfUsers = scanner.nextInt();
        System.out.println("Total number of users -> "+numberOfUsers);
        if(numberOfUsers <= -1){
            System.out.println("Number of users cannot be less than 2");
            System.exit(0);
        }
        List<User> users = new ArrayList<>();
        List<Symbol> symbols = getSymbols(numberOfUsers);
        for(int i=1; i<=numberOfUsers; i++){
            System.out.println("Enter the name of user"+i+" -> ");
            String currName = scanner.next();
            scanner.nextLine();
            users.add(new User(currName, symbols.get(i-1)));
        }
        System.out.println("Following users will play the game -> ");
        for(int i=0; i<numberOfUsers; i++){
            System.out.println(users.get(i).getName()+" <-> "+users.get(i).getSymbol().getSign());
        }

        // users and their symbols have been taken as input
        // declare the board
        TicTacToeGame game;
        try{
            game = new TicTacToeGame(symbols, users);
            game.printGameCurrentStatus();
            int totalMoves = game.getTotalMoves();
            for(int i=0; i<totalMoves; i++){
                String moveString = scanner.nextLine();
                String[] strings = moveString.split(" ");
                if(strings.length != 3){
                    System.out.println("Invalid Move");
                    i--;
                } else{
                    User user = game.getUser(strings[0]);
                    if(user == null){
                        i--;
                        continue;
                    }
                    try{
                        Move move = new Move(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]), user);
                        if(!game.takeMove(move)){
                            i--;
                        } else if(game.checkAnyWinner()){
                            System.out.println(game.getWinner().getName()+" won the game");
                            break;
                        }
                        game.printGameCurrentStatus();
                    }catch(Exception exc){
                        exc.printStackTrace();
                        System.out.println("Invalid Move");
                        i--;
                    }
                }
            }
        }catch(UnsupportedOperationException exc){
            exc.printStackTrace();
            System.out.println(exc.getMessage());
            System.exit(0);
        }
    }

    private static List<Symbol> getSymbols(int noOfUsers){
        if(noOfUsers==2){
            return List.of(new Symbol("X"), new Symbol("O"));
        } else {
            List<Symbol> symbols = new ArrayList<>();
            for(int i=0; i<noOfUsers; i++){
                symbols.add(new Symbol("X"+(i+1)));
            }
            return symbols;
        }
    }
}
