package razoom.controller.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import razoom.utils.jackson.encrypt.CipherLongSerializer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DogBreedResponse {
    @Schema(description = "Наименование пароды на английском", example = "Russian wolfhound")
    private String breedEnd;
    @Schema(description = "Наименование пароды на русском языке", example = "Русская борзая")
    private String breedRu;
    @JsonSerialize(using = CipherLongSerializer.class)
    @Schema(description = "Уникальный идентификатор записи", example = "13qaedfarf1324gv")
    private Long identifier;
}
