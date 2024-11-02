package com.example.carshowroom.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ShowroomUpdateDto implements Serializable {
    @NotNull(message = "SHOWROOM_ID_CAN_NOT_EMPTY")
    private Long id;
    @Size(max = 100,message = "MANAGER_NAME_CHARACTER_CAN_NOT_BE_MORE_THAN_HUNDRED")
    private String managerName;
    @Digits(integer = 15, fraction = 0,message = "CONTACT_NUMBER_CAN_NOT_BE_MORE_THAN_10_DIGITS")
    private Long contactNumber;
    @Size(max = 255,message = "ADDRESS_CAN_NOT_BE_MORE_THAN_HUNDRED")
    private String address;
}