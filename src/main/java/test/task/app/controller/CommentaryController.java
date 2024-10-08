package test.task.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import test.task.app.dto.commentaryDTO.CommentaryCreatedDTO;
import test.task.app.dto.commentaryDTO.CommentaryDTO;
import test.task.app.dto.commentaryDTO.CommentaryUpdatedDTO;
import test.task.app.service.CommentaryService;

import java.util.List;

/**
 * Контроллер для управления комментариями.
 * Этот контроллер обрабатывает запросы, связанные с комментариями CRUD
 */
@RestController
@RequestMapping(path = "/api/commentaries")
@RequiredArgsConstructor
public class CommentaryController {
    private final CommentaryService service;

    /**
     * Получает комментарий по его ID.
     * @param id ID комментария для получения.
     * @return объект {@link CommentaryDTO}, представляющий комментарий.
     */
    @GetMapping(path = "/{id}")
    public CommentaryDTO getById(@PathVariable Long id) {
        return service.show(id);
    }

    /**
     * Получает все комментарии.
     * @return список {@link CommentaryDTO}, представляющий все комментарии.
     */
    @GetMapping()
    public List<CommentaryDTO> getAll() {
        return service.getAll();
    }

    /**
     * Создает новый комментарий.
     * @param data объект {@link CommentaryCreatedDTO}, содержащий данные для создания комментария.
     * @return объект {@link CommentaryDTO}, представляющий созданный комментарий.
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CommentaryDTO create(@Valid @RequestBody CommentaryCreatedDTO data) {
        return service.create(data);
    }

    /**
     * Обновляет существующий комментарий.
     * @param data объект {@link CommentaryUpdatedDTO}, содержащий обновленные данные комментария.
     * @param id ID комментария для обновления.
     * @return объект {@link CommentaryDTO}, представляющий обновленный комментарий.
     */
    @PutMapping(path = "/{id}")
    public CommentaryDTO update(@Valid @RequestBody CommentaryUpdatedDTO data, @PathVariable Long id) {
        return service.update(data, id);
    }

    /**
     * Удаляет комментарий по его ID.
     * @param id ID комментария для удаления.
     */
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
