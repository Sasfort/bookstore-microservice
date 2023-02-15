package com.sasfort.bookservice.repositories;

import com.sasfort.bookservice.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
