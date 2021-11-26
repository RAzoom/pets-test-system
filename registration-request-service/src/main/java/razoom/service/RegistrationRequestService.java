package razoom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public List<DogBreedResponse> loadHandBook(String fragment) {
        Page<HbKindBreed> all = hbBreedKindCrud.findByFragment(fragment, PageRequest.of(0, 7));
        return dogBreedMapper.toDogBreedResponse(all.toList());
    }
}
