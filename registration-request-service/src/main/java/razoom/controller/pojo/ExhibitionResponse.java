package razoom.controller.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import razoom.utils.jackson.date.LocalDateSerializer;
import razoom.utils.jackson.encrypt.CipherLongSerializer;

import java.time.LocalDate;

@Getter
@Setter
public class ExhibitionResponse {
    @JsonSerialize(using = CipherLongSerializer.class)
    @Schema(description = "Уникальный идентификатор записи", example = "13qaedfarf1324gv")
    private Long identifier;

    @Schema(description = "Наименование выставки", example = "Супер-турбо")
    private String name;

    @JsonSerialize(using = LocalDateSerializer.class)
    @Schema(description = "Дана проведения", example = "2022-07-06")
    private LocalDate startExhibition;

    @JsonSerialize(using = LocalDateSerializer.class)
    @Schema(description = "Дана завершения", example = "2014-07-06")
    private LocalDate finishExhibition;

    @JsonSerialize(using = LocalDateSerializer.class)
    @Schema(description = "Дана начала регистрации", example = "2022-06-06")
    private LocalDate startRegistration;

    @JsonSerialize(using = LocalDateSerializer.class)
    @Schema(description = "Дана проведения", example = "2022-07-05")
    private LocalDate finishRegistration;
}
