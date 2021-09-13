package example;

import com.yahoo.elide.annotation.Include;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.Id;

@Include
@RegisterForReflection
public class Book {
    @Id
    long id;

    String title;
}
