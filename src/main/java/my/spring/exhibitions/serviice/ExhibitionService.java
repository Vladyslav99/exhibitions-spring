package my.spring.exhibitions.serviice;

import my.spring.exhibitions.dto.ExhibitionDTO;
import my.spring.exhibitions.entity.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExhibitionService {
    List<Exhibition> findAll();

    boolean saveExhibition(ExhibitionDTO exhibitionDTO);

    Page<Exhibition> findPaginated(Pageable pageable);
}
