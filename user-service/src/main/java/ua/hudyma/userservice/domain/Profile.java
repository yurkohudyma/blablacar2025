package ua.hudyma.userservice.domain;

import jakarta.persistence.Embeddable;

import java.util.Date;

@Embeddable
public class Profile {

    private String name;
    private String surname;
    private Date birthday;
    private String email;
    private transient String password;
    private String phoneNumber;
    private String about;
}
