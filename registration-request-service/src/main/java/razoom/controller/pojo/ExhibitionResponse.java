package razoom.controller.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import razoom.utils.jackson.encrypt.CipherLongSerializer;

@Getter
@Setter
public class ExhibitionResponse {
    @JsonSerialize(using = CipherLongSerializer.class)
    @Schema(description = "Уникальный идентификатор записи", example = "13qaedfarf1324gv")
    private Long identifier;

    @Schema(description = "Наименование выставки", example = "Супер-турбо")
    private String name;
}
