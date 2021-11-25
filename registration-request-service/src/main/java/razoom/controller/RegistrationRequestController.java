package razoom.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import razoom.controller.pojo.DogBreedResponse;
import razoom.service.RegistrationRequestService;

import javax.validation.constraints.NotBlank;
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
    @GetMapping(value = "/breed/search/{fragment}")
    public List<DogBreedResponse> search(
            @NotBlank(message = "Не указан фрагмент наименования пароды")
            @Size(min = 3, message = "Минимальный размер фрагмента - 3 символа")
            @PathVariable("fragment") String fragment
    ) {
        return registrationRequestService.loadHandBook();
    }

}
