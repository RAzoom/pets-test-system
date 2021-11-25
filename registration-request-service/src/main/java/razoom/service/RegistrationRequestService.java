package razoom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import razoom.controller.pojo.DogBreedResponse;
import razoom.dao.entity.HbKindBreed;
import razoom.dao.repository.HbKindBreedRepository;

import java.util.List;

@Service
public class RegistrationRequestService {

    @Autowired
    HbKindBreedRepository hbBreedKindCrud;

    @Autowired
    DogBreedMapper dogBreedMapper;

    public List<DogBreedResponse> loadHandBook() {
        List<HbKindBreed> all = hbBreedKindCrud.findAll();
        return dogBreedMapper.toDogBreedResponse(all);
    }
}
