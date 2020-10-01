package my.spring.exhibitions.service;

import my.spring.exhibitions.dto.HallDTO;
import my.spring.exhibitions.entity.Hall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HallService extends AbstractService<Hall>{
    List<Hall> findAll();

    boolean saveHall(HallDTO hallDTO);
}
