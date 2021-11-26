package razoom.service.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import razoom.constants.Constants;
import razoom.controller.pojo.RegistrationRequest;
import razoom.dao.entity.*;
import razoom.dao.pojo.RequestRegistrationState;
import razoom.dao.repository.*;
import razoom.exception.ApplicationException;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;


/**
 * Сервис сохранения заявки в БД.
 *
 * @author razoom
 */
@Service
public class RegistrationRequestService {

    @Autowired
    RequestRegistrationRepository repository;

    @Autowired
    ExhibitionConfigRepository exhibitionConfig;

    @Autowired
    HbKindBreedRepository kindBreed;

    @Autowired
    HbKindContactRepository kindContact;

    /**
     * Метод сохранения заявки в БД.
     * <p>
     * Содержит в себе проверки бизнес-логики на существование
     * выставки с доступной регистрацией к которой должна быть прикреплена
     * заявка и существование прикрепляемой породы.
     *
     * @param request - карточка заявки
     * @throws ApplicationException - ошибка обработки
     */
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Throwable.class)
    public void save(RegistrationRequest request) {
        ExhibitionConfig activeById = exhibitionConfig.findActiveById(request.getExhibitionIdentifier());
        if (activeById == null) {
            throw new ApplicationException("Регистрация на выставку окончена или прервана");
        }

        Optional<HbKindBreed> kindBreedOpt = kindBreed.findById(request.getBreedIdentifier());
        if (kindBreedOpt.isEmpty()) {
            throw new ApplicationException("Указанная парода более не доступна для регистрации.");
        }

        RequestRegistration requestRegistration = new RequestRegistration();
        requestRegistration.setExhibition(activeById);
        requestRegistration.setCreateDate(ZonedDateTime.now());
        //todo сделать настраиваемую генерацию
        requestRegistration.setNumberReg(UUID.randomUUID().toString());
        requestRegistration.setState(RequestRegistrationState.WAIT);

        Physic physic = new Physic();
        physic.setName(request.getName());
        physic.setPatronymic(request.getPatronymic());
        physic.setSurname(request.getSurname());
        physic.setBirthdate(request.getBirthDate());
        physic.setRequestRegistration(requestRegistration);
        requestRegistration.setPhysic(physic);

        //todo прикрутить кофеиновый кэш
        HbKindContact mobileType = kindContact.findBySysname(Constants.MOBILE_SYSTEM_NAME);
        HbKindContact emailType = kindContact.findBySysname(Constants.EMAIL_SYSTEM_NAME);

        physic.addContact(new Contact(request.getEmail(), emailType, physic));
        physic.addContact(new Contact(request.getPhone(), mobileType, physic));

        Dog dog = new Dog();
        dog.setName(request.getDogName());
        dog.setType(kindBreedOpt.get());
        dog.setRequestRegistration(requestRegistration);
        requestRegistration.setDog(dog);
        repository.save(requestRegistration);
    }


}
