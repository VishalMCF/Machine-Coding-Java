package com.gatomalvado.done.trello.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.gatomalvado.done.trello.entities.BaseEntity;
import com.gatomalvado.done.trello.entities.TrelloBoard;
import com.gatomalvado.done.trello.entities.Card;
import com.gatomalvado.done.trello.entities.SubGroup;
import com.gatomalvado.done.trello.entities.TaskUser;
import com.gatomalvado.done.trello.enums.Status;

public class BoardService {

    private Map<String, TaskUser> taskUsersMap;
    private Map<String, Card> cardMap;
    private Map<String, TrelloBoard> boardMap;
    private Map<String, SubGroup> listMap;
    private Map<String, BaseEntity> urlMap;

    private final String ENDPOINT = "http://www.trello.com";

    public BoardService() {
        this.taskUsersMap = new HashMap<>();
        this.cardMap = new HashMap<>();
        this.boardMap = new HashMap<>();
        this.listMap = new HashMap<>();
        this.urlMap = new HashMap<>();
    }

    private String generateUrl(BaseEntity entity) {
        return ENDPOINT + "/" + entity.getEntityType() + "/" + entity.getId();
    }

    public void createBoard(TrelloBoard board) {
        String id = UUID.randomUUID().toString();
        board.setId(id);
        board.setUrl(generateUrl(board));
        boardMap.put(id, board);
        System.out.println("Created board: "+board.getId());
    }

    public void show() {
        if(boardMap.isEmpty()) {
            System.out.println("No boards");
        }
        for(String key: boardMap.keySet()) {
            boardMap.get(key).printSelf();
        }
    }

    public void showBoard(String boardId) {
        if(!boardMap.containsKey(boardId)) {
            System.out.println("Board "+boardId+" does not exist");
        }
    }

    public void changeBoardName(String boardId, String newName) {
        TrelloBoard board = boardMap.get(boardId);
        board.setName(newName);
    }

    public void addMember(String boardId, String userId) {
        TaskUser user = taskUsersMap.get(userId);
        TrelloBoard board = boardMap.get(boardId);
        board.getTaskMembers().add(user);
    }

    public void removeMember(String boardId, String userId) {
        TaskUser user = taskUsersMap.get(userId);
        TrelloBoard board = boardMap.get(boardId);
        board.getTaskMembers().remove(user);
    }

    public void deleteBoard(String boardId) {
        TrelloBoard board = boardMap.get(boardId);
        Set<SubGroup> subGroupSet = board.getSubGroups();
        for (SubGroup subGroup : subGroupSet) {
            for(Card card: subGroup.getCards()) {
                cardMap.remove(card.getId());
            }
            listMap.remove(subGroup.getId());
        }
        boardMap.remove(boardId);
    }

    public void createSubGroup(String boardId, String name) {
        TrelloBoard board = boardMap.get(boardId);
        SubGroup subGroup = SubGroup.builder().name(name).id(UUID.randomUUID().toString()).build();
        board.getSubGroups().add(subGroup);
        listMap.put(subGroup.getId(), subGroup);
        System.out.println("Created list: "+subGroup.getId());
    }

    public void updateSubGroupName(String listId, String name) {
        SubGroup subGroup = listMap.get(listId);
        subGroup.setName(name);
    }

    public void showList(String listId) {
        if(!listMap.containsKey(listId)) {
            System.out.println("List " + listId + " does not exist");
        }
        SubGroup subGroup = listMap.get(listId);
        subGroup.printSelf();
    }

    public void createCard(String listId, String name) {
        Card card = Card.builder().id(UUID.randomUUID().toString()).name(name).build();
        card.setSubGroup(listMap.get(listId));
        listMap.get(listId).getCards().add(card);
        System.out.println("Created card: "+card.getId());
    }

    public void assignCard(String cardId, String userId) {
        Card card = cardMap.get(cardId);
        card.setAssignedUser(taskUsersMap.get(userId));
    }

    public void unassignCard(String cardId) {
        Card card = cardMap.get(cardId);
        card.setAssignedUser(null);
    }

    public void updateCardName(String cardId, String name) {
        Card card = cardMap.get(cardId);
        card.setName(name);
    }

    public void moveCard(String cardId, String to) {
        Card card = cardMap.get(cardId);
        SubGroup fromList = card.getSubGroup();
        SubGroup toList = listMap.get(to);
        fromList.getCards().remove(card);
        toList.getCards().add(card);
        card.setSubGroup(toList);
    }

    public void setCardDescription(String cardId, String description) {
        Card card = cardMap.get(cardId);
        card.setDescription(description);
    }

    public void setCardName(String cardId, String name) {
        Card card = cardMap.get(cardId);
        card.setName(name);
    }

    public void showCard(String cardId) {
        Card card = cardMap.get(cardId);
        card.printSelf();
    }

    public void updateStatus(String boardId, Status status) {
        TrelloBoard trelloBoard = boardMap.get(boardId);
        trelloBoard.setStatus(status);
    }

    public void addUser(TaskUser user) {
        taskUsersMap.put(user.getId(), user);
    }
}
