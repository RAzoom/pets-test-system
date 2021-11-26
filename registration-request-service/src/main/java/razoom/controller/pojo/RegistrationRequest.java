package razoom.controller.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Зарос на создание заявки")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationRequest {

    @NotBlank(message = "Не задано имя хозяина")
    @Schema(description = "Имя хозяина", example = "Александр")
    private String name;

    @NotBlank(message = "Не задана фамилия хозяина")
    @Schema(description = "Фамилия хозяина", example = "Пелевин")
    private String surname;

    @Schema(description = "Отчество хозяина", example = "Сергеевич")
    private String patronymic;

    @NotNull(message = "Дата рождения не может быть пустой")
    private LocalDate birthDate;

    @Pattern(regexp = "\\d{10}", message = "Телефон не соответствует паттерну")
    @Schema(description = "Номер мобильного телефона", example = "9998887766")
    private String phone;

    @Email(message = "Неверный формат электронной почты")
    @NotNull(message = "Электронная почта не может быть пустой")
    @Schema(description = "Электронная почта хозяина")
    private String email;

    //todo добавить шифрование
    @NotNull(message = "Не указана парода")
    @Schema(description = "Идентификатор пароды", example = "sed132412easd=")
    private Long breedIdentifier;

    //todo добавить шифрование
    @NotNull(message = "Не указана выставка")
    @Schema(description = "Идентификатор выставки", example = "sed132412easd=")
    private Long exhibitionIdentifier;

    @NotBlank(message = "Не указана кличка собаки")
    @Schema(description = "Кличка собаки", example = "Кот")
    private String dogName;

}
