package AutoService.DiplomaApplication.dtos;

import java.time.LocalDate;

public record OrderRequest(
        String brand,
        String vinNumber,
        String description,
        LocalDate date,
        String status
) {}
