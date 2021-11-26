package razoom.controller.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DogBreedResponse {
    @Schema(description = "Наименование пароды на английском", example = "Russian wolfhound")
    private String breedEnd;
    @Schema(description = "Наименование пароды на русском языке", example = "Русская борзая")
    private String breedRu;
    // todo сделать зашифрованным, потому что могу
    @Schema(description = "Уникальный идентификатор записи", example = "13qaedfarf1324gv")
    private Long identifier;
}
