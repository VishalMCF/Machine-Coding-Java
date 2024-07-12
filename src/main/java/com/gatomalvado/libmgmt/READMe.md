# Library Management System

A library management system is an automation system used to manage a library and the different resource management required in it, such as cataloging books, allowing check-out and return of books, invoicing, user management, etc.

## Requirements

Create a command-line application for the library management system with the following requirements:

### Library Details
- The library will have one or more copies of multiple books.
- The library will have multiple racks, and each rack can contain at most one copy of any book.
- Each book will have the following properties:
    - **Book ID**
    - **Title**
    - **Authors**
    - **Publishers**

### Book Copies Details
- There could be multiple copies of the same book.
- Each book copy will have a unique ID.
- Each book copy will be placed on a rack.
- Each book copy can be borrowed by a user with a specific due date.
- Every rack will have a unique rack number (numbered serially from 1 to n where n is the total number of racks).

### User Details
- User details include **User ID** and **Name**.
- A user can borrow a maximum of 5 books.

### Functionalities
The library management system can perform the following functions:

1. **Create a library.**
2. **Add a book to the library.**
    - The book should be added to the first available rack.
3. **Remove a book copy from the library.**
4. **Allow a user to borrow a book copy given the book ID, user ID, and due date.**
    - The first available copy based on the rack number should be provided.
5. **Allow a user to borrow a book copy given the book copy ID, user ID, and due date.**
6. **Allow a user to return a book copy given the book copy ID.**
    - The book should be added to the first available rack.
7. **Allow a user to print the book copy IDs of all the books borrowed by them.**
8. **Allow a user to search for books using book properties (Book ID, Title, Author, Publisher).**
    - Searching should return details about all the book copies that match the search query.

### Input/Output Format

The code should strictly follow the input/output format and will be tested with provided test cases.

#### Input Format

Multiple lines, with each line containing a command.

Possible commands:

- `create_library <library_id> <no_of_racks>`
- `add_book <book_id> <title> <comma_separated_authors> <comma_separated_publishers> <comma_separated_book_copy_ids>`
- `remove_book_copy <book_copy_id>`
- `borrow_book <book_id> <user_id> <due_date>`
- `borrow_book_copy <book_copy_id> <user_id> <due_date>`
- `return_book_copy <book_copy_id>`
- `print_borrowed <user_id>`
- `search <attribute> <attribute_value>`

Possible values of attribute: `book_id`, `author`, `publisher`.

Stop taking the input when you encounter the word `exit`.

#### Output Format

Print output based on the specific commands as mentioned below.

**create_library**

```plaintext
Created library with <no_of_racks> racks
```

**add_book**

```plaintext
Added Book to racks: <comma_separated_rack_nos>
Rack not available
```

**remove_book_copy**

```plaintext
Removed book copy: <book_copy_id> from rack: <rack_no>
Invalid Book Copy ID
```

**borrow_book**

```plaintext
Borrowed Book from rack: <rack_no>
Invalid Book ID
Not available
Overlimit
```

**borrow_book_copy**

```plaintext
Borrowed Book Copy from rack: <rack_no>
Invalid Book Copy ID
Overlimit
```

**return_book_copy**

```plaintext
Returned book copy <book_copy_id> and added to rack: <rack_no>
Invalid Book Copy ID
```

**print_borrowed**

```plaintext
Book Copy: <book_copy_id> <due_date>
```

**search**

```plaintext
Book Copy: <book_copy_id> <book_id> <title> <comma_separated_authors> <comma_separated_publishers> <rack_no> <borrowed_by_id> <due_date>
```

### Examples

#### Sample Input

```plaintext
create_library 10
add_book 1 book1 author1,author2 publisher1 book_copy1,book_copy2,book_copy3
add_book 2 book2 author2,author3 publisher2,publisher3 book_copy4,book_copy5,book_copy6,book_copy7,book_copy8,book_copy9,book_copy10
add_book 3 book3 author2 publisher2 book_copy11,book_copy12,book_copy13
search book_id 1
search book_id 3
search author_id author2
search author_id author3
remove_book_copy book_copy6
remove_book_copy book_copy13
add_book 3 book3 author2 publisher2 book_copy13
search book_id 2
print_borrowed user1
borrow_book 1 user1 2020-12-31
borrow_book 1 user1 2020-12-31
borrow_book 1 user1 2020-12-31
borrow_book 1 user1 2020-12-31
search book_id 1
search author_id author1
borrow_book 4 user1 2020-12-31
borrow_book 2 user1 2020-12-31
borrow_book 2 user1 2020-12-31
borrow_book 2 user1 2020-12-31
print_borrowed user1
return_book_copy book_copy1
return_book_copy book_copy2
borrow_book_copy book_copy1
borrow_book_copy book_copy1
borrow_book_copy book_copy2
borrow_book_copy book_copy10
print_borrowed user1
exit
```

#### Expected Output

```plaintext
Created library with 10 racks
Added Book to racks: 1,2,3
Added Book to racks: 4,5,6,7,8,9,10
Rack not available
Book Copy: book_copy1 1 book1 author1,author2 publisher1 1
Book Copy: book_copy2 1 book1 author1,author2 publisher1 2
Book Copy: book_copy3 1 book1 author1,author2 publisher1 3
Book Copy: book_copy1 1 book1 author1,author2 publisher1 1
Book Copy: book_copy2 1 book1 author1,author2 publisher1 2
Book Copy: book_copy3 1 book1 author1,author2 publisher1 3
Book Copy: book_copy4 2 book2 author2,author3 publisher2,publisher3 4
Book Copy: book_copy5 2 book2 author2,author3 publisher2,publisher3 5
Book Copy: book_copy6 2 book2 author2,author3 publisher2,publisher3 6
Book Copy: book_copy7 2 book2 author2,author3 publisher2,publisher3 7
Book Copy: book_copy8 2 book2 author2,author3 publisher2,publisher3 8
Book Copy: book_copy9 2 book2 author2,author3 publisher2,publisher3 9
Book Copy: book_copy10 2 book2 author2,author3 publisher2,publisher3 10
Book Copy: book_copy4 2 book2 author2,author3 publisher2,publisher3 4
Book Copy: book_copy5 2 book2 author2,author3 publisher2,publisher3 5
Book Copy: book_copy6 2 book2 author2,author3 publisher2,publisher3 6
Book Copy: book_copy7 2 book2 author2,author3 publisher2,publisher3 7
Book Copy: book_copy8 2 book2 author2,author3 publisher2,publisher3 8
Book Copy: book_copy9 2 book2 author2,author3 publisher2,publisher3 9
Book Copy: book_copy10 2 book2 author2,author3 publisher2,publisher3 10
Removed book copy: book_copy6 from rack: 6
Invalid Book Copy ID
Added Book to racks: 6
Book Copy: book_copy4 2 book2 author2,author3 publisher2,publisher3 4
Book Copy: book_copy5 2 book2 author2,author3 publisher2,publisher3 5
Book Copy: book_copy7 2 book2 author2,author3 publisher2,publisher3 7
Book Copy: book_copy8 2 book2 author2,author3 publisher2,publisher3 8
Book Copy: book_copy9 2 book2 author2,author3 publisher2,publisher3 9
Book Copy: book_copy10 2 book2 author2,author3 publisher2,publisher3 10
Borrowed Book from rack: 1
Borrowed Book from rack: 2
Borrowed Book from rack: 3
Not available
Book Copy: book_copy1 1 book1 author1,author2 publisher1 -1 user1 2020-12-31
Book Copy: book_copy2 1 book1 author1,author2 publisher1 -1 user1 2020-12-31
Book Copy: book_copy3 1 book1 author1,author2 publisher1 -1 user1 2020-12-31
Book Copy: book_copy1 1 book1 author

1,author2 publisher1 -1 user1 2020-12-31
Book Copy: book_copy2 1 book1 author1,author2 publisher1 -1 user1 2020-12-31
Book Copy: book_copy3 1 book1 author1,author2 publisher1 -1 user1 2020-12-31
Invalid Book ID
Borrowed Book from rack: 4
Borrowed Book from rack: 5
Overlimit
Book Copy: book_copy1 2020-12-31
Book Copy: book_copy2 2020-12-31
Book Copy: book_copy3 2020-12-31
Book Copy: book_copy4 2020-12-31
Book Copy: book_copy5 2020-12-31
Returned book copy book_copy1 and added to rack: 1
Returned book copy book_copy2 and added to rack: 2
Borrowed Book from rack: 1
Invalid Book Copy ID
Borrowed Book from rack: 2
Overlimit
Book Copy: book_copy1 2020-12-31
Book Copy: book_copy2 2020-12-31
Book Copy: book_copy3 2020-12-31
Book Copy: book_copy4 2020-12-31
Book Copy: book_copy5 2020-12-31
```

### Expectations

- **Functional and Demonstrable Code**: Ensure the code is working and can be demonstrated.
- **Functionally Correct**: The code should perform all specified functionalities correctly.
- **Modular and Readable Code**: Code should be broken into manageable functions and classes, and should be easy to read.
- **Separation of Concerns**: Ensure the code adheres to the principle of separation of concerns.
- **Maintainable Code**: The code should accommodate new requirements with minimal changes.
- **Main Method**: Provide a main method for easy testing.
- **Optional**: Write unit tests if possible.
- **No GUI**: There's no need for a graphical user interface.

### Optional Requirements

- **Extensibility**: Allow for changing the number of books users can borrow.
- **Additional Attributes**: Allow adding more attributes to the book and book search system.
- **Thread Safety**: Make the system thread-safe to handle concurrent requests.

---

This document provides a comprehensive outline of the library management system, its requirements, and expected functionality. The design should ensure modularity, readability, and maintainability, with considerations for future extensibility and thread safety.