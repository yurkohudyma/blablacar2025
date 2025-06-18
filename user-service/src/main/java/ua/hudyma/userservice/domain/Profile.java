package ua.hudyma.userservice.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;

import java.util.Date;

@Embeddable
public class Profile {

    private String name;
    private String surname;
    private Date birthday;
    private String email;
    @Transient
    private String password;
    private String phoneNumber;
    private String about;
}
