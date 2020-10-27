using de.robinkuck.bookshop as bookshop from '../db/data-model';

//@path : 'books'
service BookService {
    @readonly
    entity Books as projection on bookshop.Book;
    @readonly
    entity Authors as projection on bookshop.Author;
}

//@path : 'orders'
service OrderService {
    entity Order    as projection on bookshop.Order;
    entity Customer as projection on bookshop.Customer;

    function countOpenOrders() returns Integer;
}

//@path : 'admin'
service AdminService {
    entity Books as projection on bookshop.Book;
    entity Authors as projection on bookshop.Author;
}