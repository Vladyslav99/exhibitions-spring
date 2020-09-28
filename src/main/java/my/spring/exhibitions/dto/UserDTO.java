package my.spring.exhibitions.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NonNull
    @Size(min = 5, max = 30, message = "{registration.login_length_between}")
    private String username;

    @Email(message = "{registration.invalid_email}")
    private String email;

    @NotEmpty(message = "{registration.field_required}")
    private String password;
}
