package my.spring.exhibitions.service;

import my.spring.exhibitions.dto.ExhibitionDTO;
import my.spring.exhibitions.entity.Exhibition;
import my.spring.exhibitions.repository.ExhibitionRepository;
import my.spring.exhibitions.util.ImagePathFormatterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .imageUrl(ImagePathFormatterUtil.formatPath(exhibitionDTO.getImageUrl()))
                .build();
        exhibitionRepository.save(exhibition);
        return true;
    }

    @Override
    public Page<Exhibition> findPaginated(Pageable pageable) {
        return exhibitionRepository.findAll(pageable);
    }

    @Override
    public Optional<Exhibition> findById(Long id) {
        return exhibitionRepository.findById(id);
    }
}
