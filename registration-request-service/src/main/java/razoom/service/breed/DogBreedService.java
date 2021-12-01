package razoom.service.breed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import razoom.controller.pojo.DogBreedResponse;
import razoom.dao.entity.HbKindBreed;
import razoom.dao.repository.HbKindBreedRepository;

import java.util.List;
import java.util.Optional;

/**
 * Сервис получения данных о пароде из справочника
 */
@Service
@CacheConfig(cacheNames = {"breed"})
public class DogBreedService {

    @Autowired
    HbKindBreedRepository hbBreedKindCrud;

    @Autowired
    DogBreedMapper dogBreedMapper;

    /**
     * Метод получения сущности пароды из БД
     * с использованием кэша
     *
     * @param id - идентификатор пароды
     * @return - сущность
     */
    @Cacheable
    public Optional<HbKindBreed> findById(Long id) {
        return hbBreedKindCrud.findById(id);
    }

    /**
     * Метод сброса текущего кэша поиска по идентификатору.
     */
    @CacheEvict(allEntries = true)
    public void clearCache() {}

    /**
     * Загрузка первых 7 подходящих пород под фрагмент
     *
     * @param fragment - фрагмент наименования породы
     * @return список подходящих пород
     */
    public List<DogBreedResponse> loadHandBook(String fragment) {
        return loadHandBook(fragment, 0, 7);
    }

    /**
     * Загрузка первой страницы подходящих пород под фрагмент
     *
     * @param fragment - фрагмент наименования породы
     * @param pageSize - размер страницы
     * @return список подходящих пород
     */
    public List<DogBreedResponse> loadHandBook(String fragment, int pageSize) {
        return loadHandBook(fragment, 0, pageSize);
    }

    /**
     * Загрузка страницы подходящих парод под фрагмент
     *
     * @param fragment - фрагмент наименования породы
     * @param pageSize - размер страницы
     * @param page     - номер страницы
     * @return список подходящих пород
     */
    public List<DogBreedResponse> loadHandBook(String fragment, int page, int pageSize) {
        Page<HbKindBreed> all = hbBreedKindCrud.findByFragment(fragment, PageRequest.of(page, pageSize));
        return dogBreedMapper.toDogBreedResponse(all.toList());
    }
}
