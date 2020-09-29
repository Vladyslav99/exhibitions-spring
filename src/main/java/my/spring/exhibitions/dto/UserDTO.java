package my.spring.exhibitions.dto;

import lombok.*;
import my.spring.exhibitions.annotation.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches(message = "{registration.passwords_should_match}")
public class UserDTO {

    @NonNull
    @Size(min = 5, max = 30, message = "{registration.login_length_between}")
    private String username;

    @NonNull
    @Email(message = "{registration.invalid_email}")
    @NotEmpty(message = "{registration.field_required}")
    private String email;

    @NonNull
    @NotEmpty(message = "{registration.field_required}")
    private String password;

    @NonNull
    @NotEmpty(message = "{registration.field_required}")
    private String matchingPassword;
}
