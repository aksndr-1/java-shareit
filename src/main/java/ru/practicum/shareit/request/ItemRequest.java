package ru.practicum.shareit.request;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;

/**
 * Класс, представляющий запрос на предмет.
 *
 * @author aksndr-1
 * @version 1.0
 */
@Data
@Builder
public class ItemRequest {
    /**
     * Идентификатор запроса.
     */
    private Long id;

    /**
     * Описание запроса.
     */
    private String description;

    /**
     * Идентификатор автора запроса.
     */
    private Long author;

    /**
     * Время создания запроса.
     */
    private LocalDateTime createdTime;

    /**
     * Запрашиваемая вещь.
     */
    @ElementCollection
    @CollectionTable(name="items", joinColumns=@JoinColumn(name="id"))
    @Column(name="name")
    private Item item;
}

