package example;

import com.yahoo.elide.annotation.Include;

import javax.persistence.Id;

@Include
public class Book {
    @Id
    long id;

    String title;
}
