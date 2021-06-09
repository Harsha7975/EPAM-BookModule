package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.beans.Book;



@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	public Book findByIsbn(long isbn);
}


