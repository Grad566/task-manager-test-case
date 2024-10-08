package test.task.app.dto.priorityDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) для представления сущности приоритета.
 * Этот класс используется для передачи данных о приоритете, включая
 * его идентификатор, название и дату создания. Он предназначен для
 * использования в API.
 */
@Getter
@Setter
@Schema(description = "Сущность приоритета")
public class PriorityDTO {
    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
}
