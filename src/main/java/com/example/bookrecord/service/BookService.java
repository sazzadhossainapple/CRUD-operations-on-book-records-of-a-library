package com.example.bookrecord.service;

import com.example.bookrecord.exception.ResourceAlreadyExistException;
import com.example.bookrecord.exception.ResourceDoseNotExistException;
import com.example.bookrecord.model.Book;
import com.example.bookrecord.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //findById
    public Book findById(long id) throws ResourceDoseNotExistException {

        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new ResourceDoseNotExistException(id + "");
        }
    }


    //findAll method
    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();
       bookRepository.findAll().forEach(bookList::add);
        return bookList;
    }


    //insert method
    public Book insertBook(Book book) throws ResourceAlreadyExistException {
        Optional<Book> optionalBook = bookRepository.findById(book.getBookId());
        if (optionalBook.isPresent()) {
            throw new ResourceAlreadyExistException(book.getBookId() + "");
        } else {
            return bookRepository.save(book);
        }

    }



    //update method

    public Book updateBook(long id,Book book) throws ResourceDoseNotExistException {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {

            book.setBookId(id);
            return bookRepository.save(book);

        } else {
            throw new ResourceDoseNotExistException(id + "");
        }

    }


    //deleteById method
    public boolean deleteById(long id) throws ResourceDoseNotExistException {

        Optional<Book> optionalBook = bookRepository.findById(id);
       optionalBook.ifPresent(event -> bookRepository.deleteById(id));
       optionalBook.orElseThrow(() -> new ResourceDoseNotExistException(id + ""));
        return true;

    }
}
