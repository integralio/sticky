package io.integral.sticky;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface StickyRepository extends Repository<Sticky, String> {
    Iterable<Sticky> findAll();

    @SuppressWarnings("UnusedReturnValue")
    Sticky save(Sticky entity);

    void deleteAll();
}
