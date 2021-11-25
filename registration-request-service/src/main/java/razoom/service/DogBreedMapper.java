package razoom.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import razoom.controller.pojo.DogBreedResponse;
import razoom.dao.entity.HbKindBreed;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DogBreedMapper {

    @Mapping(target = "identifier", source = "HbKindBreed.id")
    DogBreedResponse toDogBreedResponse(HbKindBreed HbKindBreed);

    List<DogBreedResponse> toDogBreedResponse(List<HbKindBreed> HbKindBreed);
}
