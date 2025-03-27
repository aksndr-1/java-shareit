package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.item.model.ItemDto;

import java.util.List;

/**
 * Контроллер для управления предметами.
 *
 * @author aksndr-1@yandex.ru
 * @version 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    /**
     * Метод для получения всех предметов пользователя.
     *
     * @param userId идентификатор пользователя
     * @return список предметов пользователя
     */
    @GetMapping
    public List<ItemDto> findAllOwned(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Получен запрос на получение предметов пользователя {}", userId);
        List<ItemDto> itemDtos = itemService.getUserItems(userId);
        log.info("Предметы {} успешно получены", itemDtos);
        return itemService.getUserItems(userId);
    }

    /**
     * Метод для получения предмета по идентификатору.
     *
     * @param id идентификатор предмета
     * @param userId идентификатор пользователя
     * @return предмет
     */
    @GetMapping("/{id}")
    public ItemDto findById(@PathVariable Long id,
                            @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Получен запрос на получение предмета {} пользователем {}", id, userId);
        ItemDto itemDto = itemService.read(id);
        log.info("Предмет {} успешно получен", itemDto);
        return itemDto;
    }

    /**
     * Метод для поиска предметов по тексту.
     *
     * @param text текст для поиска
     * @param userId идентификатор пользователя
     * @return список предметов, соответствующих тексту поиска
     */
    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam String text,
                                @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Получен запрос поиска предметов {} пользователем {}", text, userId);
        List<ItemDto> itemDtos = itemService.search(text);
        log.info("Предметы {} успешно найдены", itemDtos);
        return itemDtos;
    }

    /**
     * Метод для создания нового предмета.
     *
     * @param item объект предмета
     * @param userId идентификатор пользователя
     * @return созданный предмет
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto create(@RequestBody @Valid ItemDto item,
                          @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Получен запрос на создание предмета {} пользователем {}", item, userId);
        ItemDto itemDto = itemService.create(item, userId);
        log.info("Предмет {} успешно создан", itemDto);
        return itemDto;
    }

    /**
     * Метод для обновления существующего предмета.
     *
     * @param id идентификатор предмета
     * @param item объект предмета
     * @param userId идентификатор пользователя
     * @return обновленный предмет
     */
    @PatchMapping("/{id}")
    public ItemDto update(@PathVariable Long id,
                          @RequestBody ItemDto item,
                          @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Получен запрос на обновление предмета {} пользователем {}", item, userId);
        ItemDto itemDto = itemService.update(id, item, userId);
        log.info("Предмет {} успешно обновлен", itemDto);
        return itemDto;
    }
}
