package ua.hudyma.userservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.Date;

@Embeddable
@Data
public class Profile {

    private String name;
    private String surname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String email;
    @Transient
    private String password;
    private String phoneNumber;
    private String about;
}
