package my.spring.exhibitions.validator;

import my.spring.exhibitions.annotation.HallsAvailable;
import my.spring.exhibitions.dto.ExhibitionEventDTO;
import my.spring.exhibitions.entity.ExhibitionEvent;
import my.spring.exhibitions.entity.Hall;
import my.spring.exhibitions.service.ExhibitionEventService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class HallsAvailableValidator implements ConstraintValidator<HallsAvailable, ExhibitionEventDTO> {

    @Autowired
    private ExhibitionEventService exhibitionEventService;

    @Override
    public boolean isValid(ExhibitionEventDTO exhibitionEventDTO, ConstraintValidatorContext validatorContext) {

        Set<ExhibitionEvent> exhibitionEvents = exhibitionEventService.findAllByDateFromBetweenDateTo(
                exhibitionEventDTO.getDateFrom(),
                exhibitionEventDTO.getDateTo());

        for (ExhibitionEvent exhibitionEvent : exhibitionEvents) {
            for (Hall hall : exhibitionEvent.getHalls()) {
                if (exhibitionEventDTO.getHallIds().contains(hall.getId())){
                    validatorContext.disableDefaultConstraintViolation();
                    validatorContext.buildConstraintViolationWithTemplate(validatorContext.getDefaultConstraintMessageTemplate())
                            .addPropertyNode( "hallIds" ).addConstraintViolation();
                    return false;
                }
            }
        }
        return true;
    }
}
