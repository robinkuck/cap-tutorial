package de.robinkuck.captutorial.handlers;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import cds.gen.de.robinkuck.bookshop.Book;

public class BookServiceHandlerTest {

	private BookServiceHandler handler = new BookServiceHandler();
	private Book book = Book.create();
    private String discountSuffix = " (-" + Math.round((1 - BookServiceHandler.DISCOUNT) * 100) + "%)";
    
	@Before
	public void prepareBook() {
        book.setTitle("title");
        book.setPrice(new BigDecimal(30.0));
	}

	@Test
	public void testDiscount() {
        book.setStock(1200);
		handler.discountBooks(Stream.of(book));
        assertEquals("title" + discountSuffix, book.getTitle());
        assertEquals(new BigDecimal(27).setScale(book.getPrice().scale()), book.getPrice());
	}

	@Test
	public void testNoDiscount() {
        book.setStock(100);
		handler.discountBooks(Stream.of(book));
        assertEquals("title", book.getTitle());
        assertEquals(new BigDecimal(30), book.getPrice());
    }

}
