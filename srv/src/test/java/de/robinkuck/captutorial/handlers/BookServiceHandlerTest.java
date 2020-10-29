package de.robinkuck.captutorial.handlers;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import cds.gen.de.robinkuck.bookshop.Book;

public class BookServiceHandlerTest {

	private BookServiceHandler handler = new BookServiceHandler();
	private Book book = Book.create();

    
	@Before
	public void prepareBook() {
		book.setTitle("title");
	}

	@Test
	public void testDiscount() {
		book.setStock(1200);
		handler.discountBooks(Stream.of(book));
		assertEquals("title (discounted)", book.getTitle());
	}

	@Test
	public void testNoDiscount() {
		book.setStock(100);
		handler.discountBooks(Stream.of(book));
		assertEquals("title", book.getTitle());
	}

}
