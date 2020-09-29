package my.spring.exhibitions.repository;

import my.spring.exhibitions.entity.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

}
