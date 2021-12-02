package razoom.service.exhibition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import razoom.controller.pojo.ExhibitionResponse;
import razoom.dao.entity.ExhibitionConfig;
import razoom.dao.repository.ExhibitionConfigRepository;

import java.util.List;

@Service
public class ExhibitionService {

    @Autowired
    ExhibitionConfigRepository exhibitionConfigRepository;

    @Autowired
    ExhibitionMapper exhibitionMapper;

    public List<ExhibitionResponse> loadActiveExhibition() {
        List<ExhibitionConfig> active = exhibitionConfigRepository.findActive();
        return exhibitionMapper.toExhibition(active);
    }
}