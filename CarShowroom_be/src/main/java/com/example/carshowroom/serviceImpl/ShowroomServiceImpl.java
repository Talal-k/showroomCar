package com.example.carshowroom.serviceImpl;

import com.example.carshowroom.dto.ShowroomDto;
import com.example.carshowroom.dto.ShowroomPostDto;
import com.example.carshowroom.dto.ShowroomUpdateDto;
import com.example.carshowroom.exception.ErrorResponseDto;
import com.example.carshowroom.exception.ResourceNotFoundException;
import com.example.carshowroom.filter.ShowroomPageRequest;
import com.example.carshowroom.mapper.ShowroomMapper;
import com.example.carshowroom.model.Showroom;
import com.example.carshowroom.repository.ShowroomRepository;
import com.example.carshowroom.service.ShowroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowroomServiceImpl implements ShowroomService {
    private final ShowroomRepository showroomRepository;
    private final ShowroomMapper showroomMapper;
    @Override
    public Page<ShowroomDto> findAllCarShowroom(ShowroomPageRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize(), request.getOrder(),request.getSortBy());
        Page<Showroom> entities = this.showroomRepository.findAll(pageable);
            List<ShowroomDto> dtos = this.showroomMapper.toDtoList(entities.getContent());
        return new PageImpl<ShowroomDto>(dtos,pageable,entities.getTotalElements());
    }

    @Override
    public ShowroomDto addCarShowroom(ShowroomPostDto postDto) {
        Showroom toEntity = this.showroomMapper.toEntity(postDto);
        Showroom saved = this.showroomRepository.save(toEntity);

        return this.showroomMapper.toDto(saved);
    }

    @Override
    public ShowroomDto findCarShowroomDetails(Long id) {
        Showroom showroom = showroomRepository.findByCommercialRegistrationNumber(id).orElse(null);
        return this.showroomMapper.toDto(showroom);
    }

    @Override
    public ShowroomDto updateCarShowroom(ShowroomUpdateDto updateDto) {
//        Showroom toEntity = this.showroomMapper.toUpdateEntity(updateDto);
        Showroom showroom = this.showroomRepository.findById(updateDto.getId()).orElseThrow(() -> new ResourceNotFoundException("SHOWROOM_NOT_FOUND"));
        if (updateDto.getContactNumber() != null) {
            showroom.setContactNumber(updateDto.getContactNumber());
        }
        if (updateDto.getManagerName() != null) {
            showroom.setManagerName(updateDto.getManagerName());
        }
        if (updateDto.getAddress() != null) {
            showroom.setAddress(updateDto.getAddress());
        }
        Showroom saved = this.showroomRepository.save(showroom);
        return this.showroomMapper.toDto(saved);
    }

    @Override
    public boolean deleteCarShowroomById(Long id) {
        this.showroomRepository.deleteById(id);
        return true;
    }
}
