package ua.hudyma.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class DriverDto {
    private String userId;
    private String username;
    private Long tripQuantity;
    private String expLevel;
    private Boolean verifiedId;
    private Boolean confirmedEmail;
    private Boolean confirmedPhoneNumber;
    private Boolean professionalMember;


}
