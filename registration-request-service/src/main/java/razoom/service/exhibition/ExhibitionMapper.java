package razoom.service.exhibition;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import razoom.controller.pojo.ExhibitionResponse;
import razoom.dao.entity.ExhibitionConfig;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExhibitionMapper {

    @Mapping(target = "identifier", source = "exhibitionConfig.id")
    ExhibitionResponse toExhibition(ExhibitionConfig exhibitionConfig);

    List<ExhibitionResponse> toExhibition(List<ExhibitionConfig> list);
}