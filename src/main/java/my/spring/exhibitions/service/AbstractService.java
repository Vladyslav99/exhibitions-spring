package my.spring.exhibitions.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AbstractService<T> {
    Page<T> findPaginated(Pageable pageable);
}
