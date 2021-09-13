package example;


import com.yahoo.elide.core.datastore.DataStore;
import com.yahoo.elide.core.datastore.inmemory.HashMapDataStore;

import io.quarkus.arc.AlternativePriority;
import io.quarkus.runtime.Startup;

import java.util.Arrays;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;

@ApplicationScoped
public class DataStoreProducer {

    @Produces
    @ApplicationScoped
    @AlternativePriority(100)
    public DataStore producesDataStore() {
        System.out.println("Overriding data store");
        return new HashMapDataStore(Arrays.asList(Book.class));
    }

    @Produces
    @ApplicationScoped
    public EntityManagerFactory produceFactory() {
        return null;
    }
}
