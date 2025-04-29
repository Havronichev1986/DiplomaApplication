package AutoService.DiplomaApplication.dtos;

import lombok.Data;

import java.time.LocalDate;


public record OrderResponse(
        Long id,
        String description,
        LocalDate date,
        String status)
{ }
