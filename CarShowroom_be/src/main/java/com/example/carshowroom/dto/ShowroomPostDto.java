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
public class ShowroomPostDto implements Serializable {
    @NotBlank(message = "NAME_CAN_NOT_BE_EMPTY")
    @Size(max = 100,message = "NAME_CHARACTER_CAN_NOT_BE_MORE_THAN_HUNDRED")
    private String name;
    @NotNull(message = "COMMERCIAL_REGISTRATION_NUMBER_CAN_NOT_BE_NULL")
    @Digits(integer = 10, fraction = 0,message = "COMMERCIAL_REGISTRATION_NUMBER_CAN_NOT_BE_MORE_THAN_10_DIGITS")
    private Long commercialRegistrationNumber;
    @Size(max = 100,message = "MANAGER_NAME_CHARACTER_CAN_NOT_BE_MORE_THAN_HUNDRED")
    private String managerName;
    @NotNull(message = "CONTACT_NUMBER_CAN_NOT_BE_EMPTY")
    @Digits(integer = 15, fraction = 0,message = "CONTACT_NUMBER_CAN_NOT_BE_MORE_THAN_10_DIGITS")
    private Long contactNumber;
    @Size(max = 255,message = "ADDRESS_CAN_NOT_BE_MORE_THAN_HUNDRED")
    private String address;
}