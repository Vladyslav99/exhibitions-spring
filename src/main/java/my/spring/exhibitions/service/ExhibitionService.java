package my.spring.exhibitions.service;

import my.spring.exhibitions.dto.ExhibitionDTO;
import my.spring.exhibitions.entity.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ExhibitionService extends AbstractService<Exhibition>{
    List<Exhibition> findAll();

    boolean saveExhibition(ExhibitionDTO exhibitionDTO);

    Optional<Exhibition> findById(Long id);
}
