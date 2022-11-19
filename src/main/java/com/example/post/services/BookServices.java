package com.example.post.services;
import com.example.post.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookServices {
    private static List<Book> list = new ArrayList<>();
    static {
        list.add(new Book(1, "j", "xyz"));
        list.add(new Book(2, "v", "xyz1"));
        list.add(new Book(3, "m", "xyz2"));
    }

    //get all books
    public List<Book> getAllBooks(){
        return list;
    }

    //get single book by id
    public Book getBookById(int id){

        Book book = null;
        try{
            book = list.stream().filter(e->e.getId() == id).findFirst().get();
        }catch (Exception e){
            e.printStackTrace();
        }

        return book;
    }

    //adding the book
    public Book addBook(Book book){
        list.add(book);
        return book;
    }

    //delete book
    public  void deleteBook(int bid){
          list= list.stream().filter(book -> {
            if(book.getId() != bid){
                return true;
            }
            else {
                return false;
            }
        }).collect(Collectors.toList());


    }

    //uptade book
    public  void updateBook(Book book, int bookId){
       list=  list.stream().map(b->{
            if(b.getId() == bookId){
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
            }
            return b;
        }).collect(Collectors.toList());

    }
}
