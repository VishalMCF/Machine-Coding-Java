package com.gatomalvado.done.libmgmt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.gatomalvado.done.libmgmt.model.Library;
import com.gatomalvado.done.libmgmt.model.request.BorrowBookRequest;
import com.gatomalvado.done.libmgmt.model.request.CreateBookDetails;
import com.gatomalvado.done.libmgmt.model.request.ReturnRequest;
import com.gatomalvado.done.libmgmt.model.request.SearchRequest;
import com.gatomalvado.done.libmgmt.orchestrator.LibraryManager;

public class Main {
    private static LibraryManager libraryManager;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        while (true) {
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            switch (commandType) {
                case "create_library":
                    try{
                        handleCreateLibrary(commands);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                case "add_book":
                    try{
                        handleAddBook(commands);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                case "remove_book_copy":
                    try{
                        handleRemoveBookCopy(commands);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                case "borrow_book":
                    try{
                        handleBorrowBook(commands);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                case "borrow_book_copy":
                    try{
                        handleBorrowBookCopy(commands);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                case "print_borrowed":
                    try{
                        handlePrintBorrowed(commands);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                case "return_book_copy":
                    try{
                        handleReturnBookCopy(commands);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                case "search":
                    try{
                        handleSearch(commands);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    private static void handleReturnBookCopy(String[] commands) {
        ReturnRequest returnRequest = new ReturnRequest(commands[1]);
        libraryManager.returnBook(returnRequest);
    }

    private static void handleSearch(String[] commands) {
        SearchRequest searchRequest = SearchRequest.builder().param(commands[1]).value(commands[2]).build();
        libraryManager.search(searchRequest);
    }

    private static void handlePrintBorrowed(String[] commands) {
        libraryManager.printBorrowed(commands[1]);
    }

    private static void handleBorrowBookCopy(String[] commands) {
        BorrowBookRequest request = BorrowBookRequest
            .builder()
            .bookId(commands[1])
            .userId(commands[2])
            .dueDate(Date.from(Instant.parse(commands[3])))
            .build();
        libraryManager.borrowBookId(request);
    }

    private static void handleBorrowBook(String[] commands) {
        BorrowBookRequest request = BorrowBookRequest
            .builder()
            .bookId(commands[1])
            .userId(commands[2])
            .dueDate(convertStringToDate(commands[3]))
            .build();
        libraryManager.borrowBook(request);
    }

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static Date convertStringToDate(String dateString) {
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // or throw a custom exception
        }
    }

    private static void handleRemoveBookCopy(String[] commands) {
        libraryManager.removeBookCopy(commands[1]);
    }

    private static void handleAddBook(String[] commands) {
        List<String> authors = Arrays.stream(commands[3].split(",")).toList();
        List<String> publishers = Arrays.stream(commands[4].split(",")).toList();
        List<String> bookCopyIds = Arrays.stream(commands[5].split(",")).toList();
        CreateBookDetails createBookDetails = CreateBookDetails.builder()
            .bookId(commands[1])
            .title(commands[2])
            .author(authors)
            .publisher(publishers)
            .bookCopyIds(bookCopyIds)
            .build();
        libraryManager.addBookCopy(createBookDetails);
    }

    private static void handleCreateLibrary(String[] commands) {
        libraryManager = new LibraryManager(Integer.parseInt(commands[1]));
        System.out.println("Created library with "+commands[1]+" racks");
    }
}
