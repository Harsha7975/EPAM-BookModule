package com.epam.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.beans.Book;
import com.epam.service.BookService;


@RestController
@RequestMapping("/books")
public class BookController {

private static final Logger LOG = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	
	@GetMapping(value = "/{book_id}", produces = "application/json")
	public ResponseEntity<Book> getBook(@PathVariable("book_id") long id) {
		LOG.info("****** Entered BookController to get the data for a particular ID from Database *******");
        Optional<Book> response=bookService.getBook(id);
		LOG.debug("Book response :: {}",response);
		return new ResponseEntity<>(response.get(), OK);
	}

	@GetMapping(value = "/",consumes= "application/json", produces = "application/json")
	public ResponseEntity<List<Book>> getAllBooks() {
		LOG.info("****** Entered BookController to get complete from Database *******");

		List<Book> response=bookService.getAllBooks();
		LOG.debug("Book response :: {}",response);
		return new ResponseEntity<>(response, OK);
	}

	@PostMapping(value = "/")
	public ResponseEntity<String> addBook(@RequestBody Book book) {

		LOG.info("****** Entered Bookcontroller to Save the data into Database *******");

		String response=bookService.addBookDetails(book);
		LOG.debug("Book response :: {}",response);
		return new ResponseEntity<>(response, OK);

	} 
	
	@PutMapping(path="/{book_id}", produces = "application/json")
    public ResponseEntity<String> updateBook(@PathVariable("book_id") long id, @RequestBody Book book) {
		LOG.info("****** Entered Bookcontroller to update the data from Database *******");

		String response=bookService.updateBook(id,book);
		LOG.debug("Book response :: {}",response);
		return new ResponseEntity<>(response, OK);
   }
	
	@DeleteMapping(path = "/{book_id}", produces = "application/json")
	public ResponseEntity<String> deleteBook(@PathVariable("book_id") long id) {

		LOG.info("****** Entered Bookcontroller to delete the data from Database *******");

		String response=bookService.deleteBook(id);
		LOG.debug("Book response :: {}",response);
		return new ResponseEntity<>(response, OK);
     } 
}
