package my.spring.exhibitions.serviice;

import my.spring.exhibitions.dto.ExhibitionDTO;
import my.spring.exhibitions.entity.Exhibition;
import my.spring.exhibitions.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExhibitionServiceImpl implements ExhibitionService{

    @Autowired
    private ExhibitionRepository exhibitionRepository;

    @Override
    public List<Exhibition> findAll() {
        return exhibitionRepository.findAll();
    }



    @Override
    public boolean saveExhibition(ExhibitionDTO exhibitionDTO) {
        Exhibition exhibition = Exhibition.builder()
                .themeEnglish(exhibitionDTO.getThemeEnglish())
                .themeUkrainian(exhibitionDTO.getThemeUkrainian())
                .descriptionEnglish(exhibitionDTO.getDescriptionEnglish())
                .descriptionUkrainian(exhibitionDTO.getDescriptionUkrainian())
                .imageUrl(exhibitionDTO.getImageUrl())
                .build();
        exhibitionRepository.save(exhibition);//make something with it
        return true;
    }

    @Override
    public Page<Exhibition> findPaginated(Pageable pageable) {
        return exhibitionRepository.findAll(pageable);
    }
}
