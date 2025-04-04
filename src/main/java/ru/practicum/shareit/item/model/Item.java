package ru.practicum.shareit.item.model;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;

/**
 * Класс, представляющий предмет.
 *
 * @author aksndr-1
 * @version 1.0
 */
@Data
@Builder
public class Item {
    /**
     * Идентификатор предмета.
     */
    private Long id;

    /**
     * Название предмета.
     */
    private String name;

    /**
     * Описание предмета.
     */
    private String description;

    /**
     * Доступность предмета.
     */
    private Boolean available;

    /**
     * Идентификатор владельца предмета.
     */
    private Long owner;

    /**
     * Запрос на предмет.
     */
    private ItemRequest request;
}
