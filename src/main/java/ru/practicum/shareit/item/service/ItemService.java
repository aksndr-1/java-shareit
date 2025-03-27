package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.extention.ConditionsNotMetException;
import ru.practicum.shareit.extention.ExceptionMessages;
import ru.practicum.shareit.extention.NotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemMapper;
import ru.practicum.shareit.item.model.ItemDto;
import ru.practicum.shareit.item.storage.ItemStorage;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для управления предметами.
 *
 * @author aksndr-1
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemStorage itemStorage;
    private final UserService userService;

    /**
     * Метод для получения предмета по идентификатору.
     *
     * @param id идентификатор предмета
     * @return предмет
     */
    public ItemDto read(Long id) {
        return ItemMapper.toItemDto(itemStorage.read(id));
    }

    /**
     * Метод для получения всех предметов пользователя.
     *
     * @param userId идентификатор пользователя
     * @return список предметов пользователя
     */
    public List<ItemDto> getUserItems(Long userId) {
        userService.exists(userId);
        return itemStorage.getAll()
                .stream()
                .filter(item -> item.getOwner().equals(userId))
                .map(ItemMapper::toItemDto)
                .toList();
    }

    /**
     * Метод для получения предмета по идентификатору и идентификатору пользователя.
     *
     * @param id идентификатор предмета
     * @param userId идентификатор пользователя
     * @return предмет
     */
    public Item getItem(Long id, Long userId) {
        userService.exists(userId);
        exists(id);
        return itemStorage.read(id);
    }

    /**
     * Метод для создания нового предмета.
     *
     * @param itemDto объект предмета
     * @param userId идентификатор пользователя
     * @return созданный предмет
     */
    public ItemDto create(ItemDto itemDto, Long userId) {
        userService.exists(userId);
        return ItemMapper.toItemDto(itemStorage.create(ItemMapper.toItem(itemDto, userId)));
    }

    /**
     * Метод для обновления существующего предмета.
     *
     * @param id идентификатор предмета
     * @param itemDto объект предмета
     * @param userId идентификатор пользователя
     * @return обновленный предмет
     */
    public ItemDto update(Long id, ItemDto itemDto, Long userId) {
        userIsOwner(id, userId);

        Item item = itemStorage.read(id);
        if (itemDto.getName() != null) {
            item.setName(itemDto.getName());
        }
        if (itemDto.getDescription() != null) {
            item.setDescription(itemDto.getDescription());
        }
        if (itemDto.getAvailable() != null) {
            item.setAvailable(itemDto.getAvailable());
        }

        return ItemMapper.toItemDto(itemStorage.update(item));
    }

    /**
     * Метод для удаления предмета.
     *
     * @param itemId идентификатор предмета
     * @param userId идентификатор пользователя
     */
    public void delete(Long itemId, Long userId) {
        userIsOwner(itemId, userId);
        itemStorage.delete(itemId);
    }

    /**
     * Метод для поиска предметов по тексту.
     *
     * @param text текст для поиска
     * @return список предметов, соответствующих тексту поиска
     */
    public List<ItemDto> search(String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }
        String lowerCaseText = text.toLowerCase();
        return itemStorage.getAll().stream()
                .filter(Item::getAvailable)
                .filter(item -> item.getName().toLowerCase().contains(lowerCaseText)
                        || item.getDescription().toLowerCase().contains(lowerCaseText))
                .map(ItemMapper::toItemDto)
                .toList();
    }

    /**
     * Метод для проверки существования предмета по идентификатору.
     *
     * @param id идентификатор предмета
     * @throws ConditionsNotMetException если предмет не найден
     */
    public void exists(Long id) throws ConditionsNotMetException {
        Optional.ofNullable(itemStorage.read(id))
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessages.ITEM_NOT_FOUND_ERROR, id)));
    }

    /**
     * Метод для проверки, является ли пользователь владельцем предмета.
     *
     * @param id идентификатор предмета
     * @param userId идентификатор пользователя
     * @throws ConditionsNotMetException если пользователь не является владельцем предмета
     */
    public void userIsOwner(Long id, Long userId) {
        userService.exists(userId);
        Item item = itemStorage.read(id);
        if (item == null || !item.getOwner().equals(userId)) {
            throw new ConditionsNotMetException("Пользователь не владелец предмета");
        }
    }
}
