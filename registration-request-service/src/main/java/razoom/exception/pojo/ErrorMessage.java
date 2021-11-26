package razoom.exception.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    @Schema(description = "Описание ошибки", example = "Минимальный размер фрагмента - 3 символа")
    private String errorMessage;
}