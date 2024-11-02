package com.example.carshowroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.example.carshowroom.model.Showroom}
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ShowroomCarDto implements Serializable {
    private String name;
    private Long contactNumber;
}