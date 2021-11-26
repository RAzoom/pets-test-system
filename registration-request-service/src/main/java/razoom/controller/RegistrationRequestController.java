package razoom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import razoom.controller.pojo.DogBreedResponse;
import razoom.exception.pojo.ErrorMessage;
import razoom.service.RegistrationRequestService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RestController()
@RequestMapping(
        path = "/doggy-service",
//        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class RegistrationRequestController {

    @Autowired
    RegistrationRequestService registrationRequestService;

    @Operation(description = "Загрузка пароды собаки по фрагменту")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Успешный ответ", content = @Content(schema = @Schema(implementation = DogBreedResponse.class))),
            @ApiResponse(responseCode = "400",description = "Ошибка запроса", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping(value = "/breed/search/{fragment}")
    public List<DogBreedResponse> search(
            @NotBlank(message = "Не указан фрагмент наименования пароды")
            @Size(min = 3, message = "Минимальный размер фрагмента - 3 символа")
            @Pattern(regexp = "[a-zA-Zа-яА-ЯёЁ \\-]+",
                    message = "Наименование породы можешь содержать только кириллицу, латиницу, пробелы и '-'")
            @PathVariable("fragment") String fragment
    ) {
        return registrationRequestService.loadHandBook(fragment);
    }

}
