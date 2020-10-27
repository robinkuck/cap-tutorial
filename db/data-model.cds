namespace de.robinkuck.bookshop;

entity Book {
    key ID     : Integer;
        title  : String;
        stock  : Integer;
        price  : Decimal(10, 2);
        author : Association to Author;
}

entity Author {
    key ID        : Integer;
        firstname : String;
        lastname  : String;
        books     : Association to many Book
                        on books.author = $self;
}

entity Customer {
    key ID          : Integer;
        firstname   : String;
        lastname    : String;
        emailAdress : String;
}

entity Order {
    key ID        : Integer;
        createdAt : DateTime;
        isOpen    : Boolean;
        customer  : Association to Customer;
        books     : Composition of many {
                    key book : Association to Book;
        };
}
