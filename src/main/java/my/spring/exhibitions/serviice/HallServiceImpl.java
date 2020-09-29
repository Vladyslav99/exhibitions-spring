package my.spring.exhibitions.serviice;

import my.spring.exhibitions.dto.HallDTO;
import my.spring.exhibitions.entity.Hall;
import my.spring.exhibitions.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HallServiceImpl implements HallService{

    @Autowired
    private HallRepository hallRepository;

    @Override
    public List<Hall> findAll() {
        return hallRepository.findAll();
    }

    @Override
    public boolean saveHall(HallDTO hallDTO) {
        Hall hall = Hall.builder()
                .nameEnglish(hallDTO.getNameEnglish())
                .nameUkrainian(hallDTO.getDescriptionUkrainian())
                .descriptionEnglish(hallDTO.getDescriptionEnglish())
                .descriptionUkrainian(hallDTO.getDescriptionUkrainian())
                .imageUrl(hallDTO.getImageUrl())
                .build();
        hallRepository.save(hall);//make something with it
        return true;
    }

    @Override
    public Page<Hall> findPaginated(Pageable pageable) {
        return hallRepository.findAll(pageable);
    }
}
