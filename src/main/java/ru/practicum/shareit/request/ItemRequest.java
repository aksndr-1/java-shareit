package ru.practicum.shareit.request;

import lombok.Builder;
import lombok.Data;

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
}

