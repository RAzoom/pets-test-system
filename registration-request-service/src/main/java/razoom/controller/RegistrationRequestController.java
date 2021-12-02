package razoom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import razoom.controller.pojo.DogBreedResponse;
import razoom.controller.pojo.ExhibitionResponse;
import razoom.controller.pojo.RegistrationRequest;
import razoom.exception.pojo.BadRequestMessage;
import razoom.service.breed.DogBreedService;
import razoom.service.exhibition.ExhibitionService;
import razoom.service.registration.RegistrationRequestService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class RegistrationRequestController {

    @Autowired
    DogBreedService dogBreedService;

    @Autowired
    ExhibitionService exhibitionService;

    @Autowired
    RegistrationRequestService registrationRequestService;

    @Operation(description = "Загрузка пароды собаки по фрагменту")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ", content = @Content(schema = @Schema(implementation = DogBreedResponse.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка запроса", content = @Content(schema = @Schema(implementation = BadRequestMessage.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content(schema = @Schema(implementation = BadRequestMessage.class)))
    })
    @GetMapping(value = "/breed/search/{fragment}")
    public List<DogBreedResponse> searchBreed(
            @NotBlank(message = "Не указан фрагмент наименования пароды")
            @Size(min = 3, message = "Минимальный размер фрагмента - 3 символа")
            @Pattern(regexp = "[a-zA-Zа-яА-ЯёЁ \\-]+",
                    message = "Наименование породы можешь содержать только кириллицу, латиницу, пробелы и '-'")
            @PathVariable("fragment") String fragment
    ) {
        return dogBreedService.loadHandBook(fragment);
    }

    @Operation(description = "Регистрация заявки в БД")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ"),
            @ApiResponse(responseCode = "400", description = "Ошибка запроса", content = @Content(schema = @Schema(implementation = BadRequestMessage.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content(schema = @Schema(implementation = BadRequestMessage.class)))
    })
    @PostMapping(value = "/request/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createRequest(@RequestBody @Valid RegistrationRequest request) {
        registrationRequestService.save(request);
    }

    @Operation(description = "Список активных выставок")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ", content = @Content(schema = @Schema(implementation = ExhibitionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка запроса", content = @Content(schema = @Schema(implementation = BadRequestMessage.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content(schema = @Schema(implementation = BadRequestMessage.class)))
    })
    @GetMapping(value = "/exhibitions")
    public List<ExhibitionResponse> createRequest() {
        return exhibitionService.loadActiveExhibition();
    }
}
