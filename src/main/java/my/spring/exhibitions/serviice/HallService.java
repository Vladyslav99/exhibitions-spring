package my.spring.exhibitions.serviice;

import my.spring.exhibitions.dto.HallDTO;
import my.spring.exhibitions.entity.Hall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HallService {
    List<Hall> findAll();

    boolean saveHall(HallDTO hallDTO);

    Page<Hall> findPaginated(Pageable pageable);
}
