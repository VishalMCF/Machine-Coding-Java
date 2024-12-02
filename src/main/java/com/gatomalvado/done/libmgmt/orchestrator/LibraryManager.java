package com.gatomalvado.done.libmgmt.orchestrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.gatomalvado.done.libmgmt.model.Book;
import com.gatomalvado.done.libmgmt.model.BookCopy;
import com.gatomalvado.done.libmgmt.model.LibUser;
import com.gatomalvado.done.libmgmt.model.request.BorrowBookRequest;
import com.gatomalvado.done.libmgmt.model.request.CreateBookDetails;
import com.gatomalvado.done.libmgmt.model.request.ReturnRequest;
import com.gatomalvado.done.libmgmt.model.request.SearchRequest;

public class LibraryManager {

    private static final int RACK_LIMIT = 1;

    private String id;

    private int totalRacks;

    private static final Integer MAX_BOOKS_ALLOWED = 5;

    private Map<String, Book> booksDetails;

    private Map<Integer, List<BookCopy>> rackBookCopyInfo;

    private Map<String, List<BookCopy>> userBookCopyInfo;

    private Map<String, BookCopy> bookCopyInfo;

    private Map<String, LibUser> userInfo;

    public LibraryManager(int racks) {
        this.userInfo = new ConcurrentHashMap<>();
        this.bookCopyInfo = new ConcurrentHashMap<>();
        this.userBookCopyInfo = new ConcurrentHashMap<>();
        this.booksDetails = new ConcurrentHashMap<>();
        this.rackBookCopyInfo = new ConcurrentHashMap<>();
        this.totalRacks = racks;
        initRack();
    }

    private void initRack() {
        IntStream.range(1, totalRacks+1).forEach(i -> this.rackBookCopyInfo.put(i, new ArrayList<>()));
    }

    public synchronized void addUser(LibUser user){
        if(userInfo.containsKey(user.getUserId())){
            throw new IllegalArgumentException("User already exists");
        }
        userInfo.put(user.getUserId(), user);
    }

    public synchronized void addBookCopy(CreateBookDetails requestDetails){
        if(booksDetails.containsKey(requestDetails.getBookId())){
            Book book = booksDetails.get(requestDetails.getBookId());
            createBookCopies(requestDetails, book);
        } else {
            Book book  = getBookFromRequest(requestDetails);

            // add the book to the registry
            this.booksDetails.put(book.getBookId(), book);
            createBookCopies(requestDetails, book);
        }
    }

    public synchronized void removeBookCopy(String bookCopyId) {
        try{
            BookCopy copy = this.bookCopyInfo.remove(bookCopyId);
            System.out.println("Removed BookCopy "+ bookCopyId +" from rack "+copy.getRack());
        }catch(Exception e){
            throw new IllegalArgumentException("Invalid Book Copy ID");
        }
    }

    public synchronized void borrowBook(BorrowBookRequest borrowBookRequest){
        if(!this.booksDetails.containsKey(borrowBookRequest.getBookId())){
            throw new IllegalArgumentException("Invalid Book ID");
        }
        List<BookCopy> issuedBooks = this.userBookCopyInfo.get(borrowBookRequest.getUserId());
        if(issuedBooks == null){
            issuedBooks = new ArrayList<>();
            this.userBookCopyInfo.put(borrowBookRequest.getUserId(), issuedBooks);
        }
        if(issuedBooks.size() >= MAX_BOOKS_ALLOWED){
            throw new IllegalArgumentException("Overlimit");
        }
        Optional<BookCopy> bookCopy = this.bookCopyInfo
            .keySet()
            .stream()
            .map((k) -> this.bookCopyInfo.get(k))
            .filter((bc) -> !bc.isIssued() && bc.getBook().getBookId().equals(borrowBookRequest.getBookId()))
            .findFirst();
        if(bookCopy.isEmpty()){
            throw new IllegalArgumentException("Not available");
        } else {
            bookCopy.get().setIssued(true);
            bookCopy.get().setIssuerId(borrowBookRequest.getUserId());
            bookCopy.get().setDueDate(borrowBookRequest.getDueDate());
            issuedBooks.add(bookCopy.get());
        }
        System.out.println("Borrowed Book Copy from rack: "+bookCopy.get().getRack());
    }


    public synchronized void borrowBookId(BorrowBookRequest borrowBookRequest){
        if(!this.bookCopyInfo.containsKey(borrowBookRequest.getBookId())){
            throw new IllegalArgumentException("Invalid Book Copy ID");
        }
        BookCopy bookCopy = this.bookCopyInfo.get(borrowBookRequest.getBookId());
        List<BookCopy> issuedBooks = this.userBookCopyInfo.get(borrowBookRequest.getUserId());
        if(issuedBooks == null){
            issuedBooks = new ArrayList<>();
            this.userBookCopyInfo.put(borrowBookRequest.getUserId(), issuedBooks);
        }
        if(issuedBooks.size() >= MAX_BOOKS_ALLOWED){
            throw new IllegalArgumentException("Overlimit");
        }
        if(bookCopy != null){
            throw new IllegalArgumentException("Not available");
        } else {
            bookCopy.setIssued(true);
            bookCopy.setIssuerId(borrowBookRequest.getUserId());
            bookCopy.setDueDate(borrowBookRequest.getDueDate());
            issuedBooks.add(bookCopy);
        }

        System.out.println("Borrowed Book Copy from rack: "+bookCopy.getRack());
    }

    public synchronized void returnBook(ReturnRequest returnRequest){
        try{
            BookCopy bookCopy = this.bookCopyInfo.get(returnRequest.getBookCopyId());

            // remove due date
            bookCopy.setDueDate(null);

            // mark it as not issued
            bookCopy.setIssued(false);

            // remove issuer
            bookCopy.setIssuerId(null);

            // remove from the user issued list
            List<BookCopy> issuedBooks = this.userBookCopyInfo.get(bookCopy.getIssuerId())
                .stream()
                .filter((ub)-> !ub.getId().equals(returnRequest.getBookCopyId()))
                .collect(Collectors.toUnmodifiableList());
            this.userBookCopyInfo.put(bookCopy.getIssuerId(), issuedBooks);

            // print result to the console
            System.out.println("Returned book copy "+bookCopy.getId()+" and added to rack: "+bookCopy.getRack());
        }catch(Exception e){
            throw new IllegalArgumentException("Invalid Book Copy ID");
        }
    }

    public synchronized void printBorrowed(String userId){
        if(this.userBookCopyInfo.containsKey(userId)){
            this.userBookCopyInfo.get(userId).stream().forEach((ib) -> {
                System.out.println("Book Copy: "+ib.getId()+" "+ib.getDueDate());
            });
        }
    }

    public synchronized void search(SearchRequest searchRequest) {
        switch(searchRequest.getParam()){
            case "book_id":
                this.bookCopyInfo
                    .values()
                    .stream()
                    .filter((b)->b.getBook().getBookId().equals(searchRequest.getValue()))
                    .forEach((bc)->printBookIdInfo(bc));
                return;
            case "author_id":
                this.bookCopyInfo
                    .values()
                    .stream()
                    .filter((b)->b.getBook().getAuthor().contains(searchRequest.getValue()))
                    .forEach((bc)->printBookIdInfo(bc));
                return;
            case "publisher":
                this.bookCopyInfo
                    .values()
                    .stream()
                    .filter((b)->b.getBook().getPublisher().contains(searchRequest.getValue()))
                    .forEach((bc)->printBookIdInfo(bc));
                return;
            default: throw new IllegalArgumentException("Invalid Search Parameter");
        }
    }

    private void printBookIdInfo(BookCopy bookCopy){
        System.out.println("Book Copy: "+bookCopy.getId()+" "+" "+bookCopy.getBook().getAuthor()+" "+ bookCopy.getBook().getPublisher()+
            " "+(bookCopy.isIssued() ? -1: bookCopy.getRack()) + " "+ (bookCopy.isIssued() ? bookCopy.getIssuerId():"")
            + " " + (bookCopy.isIssued() ? bookCopy.getDueDate():""));
    }

    private static Book getBookFromRequest(CreateBookDetails requestDetails) {
        return Book
            .builder()
            .bookId(requestDetails.getBookId())
            .title(requestDetails.getTitle())
            .bookId(requestDetails.getBookId())
            .author(requestDetails.getAuthor())
            .publisher(requestDetails.getPublisher())
            .build();
    }

    private void createBookCopies(CreateBookDetails requestDetails, Book book) {
        List<Integer> racksUsed = new ArrayList<>();
        requestDetails.getBookCopyIds().forEach((id) -> {
            if(!bookCopyInfo.containsKey(id)){

                // add the mapping of book to bookCopy
                BookCopy bookCopy = BookCopy.builder().book(book).id(id).build();
                if(!bookCopyInfo.containsKey(id)){
                    bookCopyInfo.put(id, bookCopy);
                }

                // added to the rack;
                OptionalInt rackNo = getFreeRack();
                if(rackNo.isPresent()){
                    rackBookCopyInfo.get(rackNo.getAsInt()).add(bookCopy);
                    bookCopy.setRack(rackNo.getAsInt());
                    racksUsed.add(rackNo.getAsInt());
                }else{
                    throw new IllegalArgumentException("Rack not available");
                }
            }
        });
        System.out.println("Added books to racks : "+racksUsed);
    }

    private OptionalInt getFreeRack(){
        return IntStream
            .range(1,totalRacks+1)
            .filter((i) -> rackBookCopyInfo.get(i).size() < RACK_LIMIT)
            .findFirst();
    }
}
