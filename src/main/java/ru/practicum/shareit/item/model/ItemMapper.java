package ru.practicum.shareit.item.model;

import org.springframework.stereotype.Component;

/**
 * Класс, который отвечает за преобразование объектов Item в объекты ItemDto и наоборот.
 *
 * @author aksndr-1
 * @version 1.0
 */
@Component
public class ItemMapper {

    /**
     * Метод преобразует объект Item в объект ItemDto.
     *
     * @param item объект Item
     * @return объект ItemDto
     */
    public static ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .build();
    }

    /**
     * Метод преобразует объект ItemDto в объект Item.
     *
     * @param itemDto объект ItemDto
     * @param ownerId идентификатор владельца предмета
     * @return объект Item
     */
    public static Item toItem(ItemDto itemDto, Long ownerId) {
        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .available(itemDto.getAvailable())
                .owner(ownerId)
                .build();
    }
}
