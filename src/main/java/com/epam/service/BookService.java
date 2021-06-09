package com.epam.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.beans.Book;
import com.epam.exception.BookNotFoundException;
import com.epam.repository.BookRepository;

@Service
public class BookService {

	private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

	@Autowired
	private BookRepository bookRepository;
	
	public String addBookDetails(Book book) {
		String message="";
		try {
			LOG.info("Entered into service adddetails method");
			if(bookRepository.findByIsbn(book.getIsbn())!=null) {
				message="Name is already present,please use different name";
			}else {
				bookRepository.save(book);
				message="Details are saved successfully";
			}

		}catch (Exception e) {
			message="Details are not saved successfully";
		}
		return message;
	}
	
	public Optional<Book> getBook(long id) {
		Optional<Book> bookDetails=bookRepository.findById(id);
		if(!(bookDetails.isPresent())){
			throw new BookNotFoundException("There is no data for this Id!!!");
		} else {
			return bookDetails;
		}
	}
	
	public List<Book> getAllBooks() {
		List<Book> bookDetails=bookRepository.findAll();
		if(bookDetails.isEmpty()){
			throw new BookNotFoundException("There is no data in Database!!!");
		} else {
			return bookDetails;
		}

	}
	
	public String updateBook(long id,Book book) {
		String message="";
        if(bookRepository.findById(id).isPresent()){
			Book bk=new Book();
			bk.setBookId(id);
			bk.setIsbn(book.getIsbn());
			bk.setBookName(book.getBookName());
			bk.setAuthorName(book.getAuthorName());
			bk.setCategory(book.getCategory());
			bk.setDescription(book.getDescription());
			bk.setPublisherName(book.getPublisherName());
			bookRepository.save(bk);
			return message="Update  is  happened Successfully!!!";
		}else {
			throw new BookNotFoundException("There is no id to update!!!");
		}
	}


	public String deleteBook(long id) {
		String message="";
		if(bookRepository.findById(id).isPresent()){
			bookRepository.deleteById(id);
			return 	message="Delete is  happened Successfully!!!";
		}else {
			throw new BookNotFoundException("There is no id to delete!!!");
		}
	}

}
