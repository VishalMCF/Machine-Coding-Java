package com.gatomalvado.done.trello;

import java.util.ArrayList;
import java.util.List;

import com.gatomalvado.done.trello.entities.TaskUser;
import com.gatomalvado.done.trello.service.BoardService;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Trell App!");

        TaskUser taskUser1 = TaskUser.builder().id("1").name("user-1").email("user1@yvz.com").build();
        TaskUser taskUser2 = TaskUser.builder().id("2").name("user-2").email("user2@yvz.com").build();
        TaskUser taskUser3 = TaskUser.builder().id("3").name("user-3").email("user3@yvz.com").build();

        BoardService boardService = new BoardService();

        boardService.addUser(taskUser1); boardService.addUser(taskUser2); boardService.addUser(taskUser3);

        String input = myInput();
        String[] commands = input.trim().split("\n");
        List<String[]> eachCommand = new ArrayList<>();
        for (String command : commands) {
            eachCommand.add(command.trim().split(" "));
        }

        for(String[] cmdLine: eachCommand) {
            String firstCommand = cmdLine[0];
            switch (firstCommand) {
                case "SHOW":
                    handleShowCommand(cmdLine, boardService);
                case "BOARD":
                    handlerBoardCommand(cmdLine, boardService);
                case "LIST":
                    handleListCommand(cmdLine, boardService);
                case "CARD":
                    handleCardCommand(cmdLine, boardService);
                default:
                    break;
            }
        }

    }

    private static void handleShowCommand(String[] cmdLine, BoardService boardService) {
        if(cmdLine.length == 1) {
            boardService.show();
            return;
        }
        if(cmdLine.length == 3) {
            switch (cmdLine[1]) {
                case "CARD":
                    boardService.showCard(cmdLine[2]);
                    return;
                case "LIST":
                    boardService.showList(cmdLine[2]);
                    return;
                case "BOARD":
                    boardService.showBoard(cmdLine[2]);
                    return;
                default:
            }
        }
    }

    private static void handleListCommand(String[] cmdLine, BoardService boardService) {
        if(cmdLine.length == 1) {
            return;
        }
        if("CREATE".equals(cmdLine[1])) {
            String boardId = cmdLine[2];
            boardService.createSubGroup(boardId, getString(cmdLine, 3));
        } else {
            // update operation
            if("name".equals(cmdLine[2])) {
                boardService.updateSubGroupName(cmdLine[1], getString(cmdLine, 3));
            }
        }
    }

    private static String getString(String[] cmdLine, int ix) {
        String args = "";
        for(int i = ix; i < cmdLine.length; i++) {
            args += cmdLine[i];
            if(i != cmdLine.length - 1) {
                args += " ";
            }
        }
        return args;
    }

    private static void handleCardCommand(String[] cmdLine, BoardService boardService) {
        if(cmdLine.length == 1) {
            return;
        }
        if("CREATE".equals(cmdLine[1])) {
            String boardId = cmdLine[2];
            boardService.createCard(boardId, getString(cmdLine, 3));
        } else {
            // update operation
            if("name".equals(cmdLine[2])) {
                boardService.updateSubGroupName(cmdLine[1], getString(cmdLine, 3));
            }
        }
    }

    private static void handlerBoardCommand(String[] cmdLine, BoardService boardService) {

    }

    private static String myInput() {
        return """
                SHOW
                BOARD CREATE work@tech
                SHOW BOARD 5da1583ec25d2a7e246b0375
                SHOW
                BOARD 5da1583ec25d2a7e246b0375 name workat.tech
                BOARD 5da1583ec25d2a7e246b0375 privacy PRIVATE
                SHOW BOARD 5da1583ec25d2a7e246b0375
                BOARD CREATE workat
                SHOW
                BOARD 5da1583ec25d2a7e246b0375 ADD_MEMBER user1
                BOARD 5da1583ec25d2a7e246b0375 ADD_MEMBER user2
                BOARD 5da1583ec25d2a7e246b0375 ADD_MEMBER user3
                BOARD 5da1583ec25d2a7e246b0375 REMOVE_MEMBER user2
                SHOW BOARD 5da1583ec25d2a7e246b0375
                BOARD DELETE 5da1586caaaad00d9b2d7aa6
                SHOW BOARD 5da1586caaaad00d9b2d7aa6
                SHOW
                LIST CREATE 5da1583ec25d2a7e246b0375 Mock Interviews
                SHOW LIST 5da1583547c78c15a1408df2
                LIST 5da1583547c78c15a1408df2 name Mock Interviews - Applied
                SHOW LIST 5da1583547c78c15a1408df2
                LIST CREATE 5da1583ec25d2a7e246b0375 Mock Interviews - Scheduled
                SHOW BOARD 5da1583ec25d2a7e246b0375
                CARD CREATE 5da1583547c78c15a1408df2 abcd@gmail.com
                CARD CREATE 5da1583547c78c15a1408df2 abcda@gmail.com
                SHOW LIST 5da1583547c78c15a1408df2
                CARD 5da1583547c78c15a14kj78g name abcde@gmail.com
                CARD 5da1583547c78c15a14kj78g description At 7PM
                SHOW LIST 5da1583547c78c15a1408df2
                CARD 5da1583547c78c15a14kjsd8 ASSIGN gaurav@workat.tech
                SHOW CARD 5da1583547c78c15a14kjsd8
                CARD 5da1583547c78c15a14kjsd8 MOVE 5da1583547c78c15a143hj34
                SHOW LIST 5da1583547c78c15a1408df2
                SHOW LIST 5da1583547c78c15a143hj34
                CARD 5da1583547c78c15a14kjsd8 UNASSIGN
                SHOW CARD 5da1583547c78c15a14kjsd8
                SHOW
            """;
    }

}
