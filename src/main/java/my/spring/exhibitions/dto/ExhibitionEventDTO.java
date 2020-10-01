package my.spring.exhibitions.dto;


import lombok.*;
import my.spring.exhibitions.annotation.HallsAvailable;
import my.spring.exhibitions.entity.ExhibitionEventStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@HallsAvailable(message = "{admin_panel.exhibition_event.halls_in_use}")
public class ExhibitionEventDTO {

    @NotNull(message = "{registration.field_required}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @NotNull(message = "{registration.field_required}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    @NotNull(message = "{registration.field_required}")
    private LocalTime timeFrom;

    @NotNull(message = "{registration.field_required}")
    private LocalTime timeTo;

    @NotNull(message = "{registration.field_required}")
    private BigDecimal ticketCost;

    @Min(1)
    @NotNull(message = "{registration.field_required}")
    private Long maxAvailablePlaces;

    private ExhibitionEventStatus eventStatus = ExhibitionEventStatus.PLANNED;

    @NotNull
    private Long exhibitionId;

    @NotNull
    private Set<Long> hallIds;
}
