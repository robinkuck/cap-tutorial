package de.robinkuck.captutorial.handlers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.ServiceName;

import org.springframework.stereotype.Component;

import cds.gen.bookservice.BookService_;
import cds.gen.bookservice.Books_;
import cds.gen.de.robinkuck.bookshop.Book;

@Component
@ServiceName(BookService_.CDS_NAME)
public class BookServiceHandler implements EventHandler {

    public static final double DISCOUNT = 0.9;

    @After(event = CdsService.EVENT_READ, entity = Books_.CDS_NAME)
    public void discountBooks(Stream<Book> books) {
        String discountSuffix = " (-" + Math.round((1 - DISCOUNT) * 100) + "%)";
        books.filter(book -> book.getStock() > 1000)
                .forEach(book -> {
                    book.setTitle(book.getTitle() + discountSuffix);
                    BigDecimal result = book.getPrice().multiply(new BigDecimal(DISCOUNT)).setScale(2, RoundingMode.HALF_UP);
                    book.setPrice(result);
                });
    }

}