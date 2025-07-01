package ua.hudyma.userservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Embeddable
@Data
public class Profile {

    private String name;
    private String surname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date registeredOn;
    @Column(nullable = false)
    private String email;
    @Transient
    private String password;
    private String phoneNumber;
    private String about;
}
