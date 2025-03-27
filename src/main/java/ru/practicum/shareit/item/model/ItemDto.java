package ru.practicum.shareit.item.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * Класс, представляющий предмет.
 *
 * @author aksndr-1
 * @version 1.0
 */
@Data
@Builder
public class ItemDto {
    /**
     * Идентификатор предмета.
     */
    private Long id;

    /**
     * Название предмета.
     */
    @NotBlank
    private String name;

    /**
     * Описание предмета.
     */
    @NotBlank
    private String description;

    /**
     * Доступность предмета.
     */
    @NotNull
    private Boolean available;
}
