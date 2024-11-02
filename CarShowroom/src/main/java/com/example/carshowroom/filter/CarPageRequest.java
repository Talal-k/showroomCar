package com.example.carshowroom.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CarPageRequest {
    private int pageNumber;
    private int pageSize;
    private String sortBy = "id";
    private Sort.Direction order = Sort.Direction.DESC;
    private String carShowroomName;
    private String maker;
}
