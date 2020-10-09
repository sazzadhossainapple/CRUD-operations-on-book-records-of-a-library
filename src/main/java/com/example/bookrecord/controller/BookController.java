package com.example.bookrecord.controller;


import com.example.bookrecord.exception.ResourceAlreadyExistException;
import com.example.bookrecord.exception.ResourceDoseNotExistException;
import com.example.bookrecord.model.Book;
import com.example.bookrecord.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v3/sazzadhossain/2017000000199")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @GetMapping("/{id}")

    public ResponseEntity<Book> getBook(@PathVariable long id) {

        try {
            Book book = bookService.findById(id);
            return ResponseEntity.ok(book);
        } catch (ResourceDoseNotExistException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> getBook() {
        List<Book> bookList= bookService.findAll();
        return ResponseEntity.ok(bookList);
    }

    @PostMapping("")
    public ResponseEntity<Book> insertBook(@RequestBody Book book) {
        try {
           Book insertBooks = bookService.insertBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertBooks);
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PutMapping("/{id}")
    public Book update(@PathVariable long id, @RequestBody Book book) throws ResourceDoseNotExistException {
        return bookService.updateBook(id,book);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteBookById(@PathVariable long id) {
        try {
            boolean deleted = bookService.deleteById(id);
            return ResponseEntity.ok(id);
        } catch (ResourceDoseNotExistException e) {
            return ResponseEntity.notFound().build();

        }

    }
}
