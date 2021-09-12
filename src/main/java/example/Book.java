package example;

import com.yahoo.elide.annotation.Include;

import javax.persistence.Entity;
import javax.persistence.Id;

@Include
@Entity
public class Book {
    @Id
    long id;

    String title;
}
