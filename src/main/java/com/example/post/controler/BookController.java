package com.example.post.controler;

import com.example.post.model.Book;
import com.example.post.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookServices bookServices;
//    get all books handler
    @GetMapping("/books")
    public ResponseEntity <List<Book>> getBooks(){
        List<Book> list= bookServices.getAllBooks();
        if (list.size() == 0) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }
//    get books by id handler
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id){
        Book book =  bookServices.getBookById(id);
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));


    }
//    create new book handler
    @PostMapping("/books")
    public ResponseEntity <Book> addBook(@RequestBody Book book){
       Book b = null;
        try{
            b =this.bookServices.addBook(book);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.of(Optional.of(b));
    }

    //delete a book handler
    @DeleteMapping("/books/{bookId}")
    public  ResponseEntity <Void> deleteBook(@PathVariable("bookId") int bookId){
      try {
          this.bookServices.deleteBook(bookId);
          return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      }catch (Exception e){
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();


      }
    }

    //update book handler
    @PutMapping("/books/{bookId}")
    public  Book updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId){
        this.bookServices.updateBook(book, bookId);
        return book;
    }
}
