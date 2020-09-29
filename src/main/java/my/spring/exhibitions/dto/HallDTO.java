package my.spring.exhibitions.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HallDTO {

    @NotNull
    @NotEmpty(message = "{registration.field_required}")
    private String nameEnglish;

    @NotNull
    @NotEmpty(message = "{registration.field_required}")
    private String nameUkrainian;

    @NotNull
    @NotEmpty(message = "{registration.field_required}")
    private String descriptionEnglish;

    @NotNull
    @NotEmpty(message = "{registration.field_required}")
    private String descriptionUkrainian;

    @NotNull
    @NotEmpty(message = "{registration.field_required}")
    private String imageUrl;
}
