package com.example.bookrecord.repository;

import com.example.bookrecord.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  extends CrudRepository<Book,Long> {
}
