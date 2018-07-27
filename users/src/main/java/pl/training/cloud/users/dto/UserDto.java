package pl.training.cloud.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonProperty.Access;

@Data
public class UserDto {

    private String firstName;
    private String lastName;
    @JsonProperty(access = Access.READ_ONLY)
    private String departmentName;
    @JsonIgnore
    private Long departmentId;

}
