package razoom.exception.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BadRequestMessage {

    @Schema(description = "Список ошибок", example = "Минимальный размер фрагмента - 3 символа")
    private List<Error> errorMessage = new ArrayList<>();

    public BadRequestMessage add(String field, String msg) {
        this.errorMessage.add(new Error(field, msg));
        return this;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    static class Error {
        @Schema(description = "Поле (по мере возможности) в котором произошло исключение", example = "searchBreed.fragment")
        private String field;
        @Schema(description = "Описание ошибки", example = "Минимальный размер фрагмента - 3 символа")
        private String error;
    }
}