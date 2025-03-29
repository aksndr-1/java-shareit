package ru.practicum.shareit.booking.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Класс, представляющий бронирование предмета.
 *
 * @author aksdnr-1
 * @version 1.0
 */
@Data
@Builder
public class Booking {
    /**
     * Идентификатор бронирования.
     */
    private Long id;

    /**
     * Время начала бронирования.
     */
    private LocalDateTime start;

    /**
     * Время окончания бронирования.
     */
    private LocalDateTime end;

    /**
     * Идентификатор предмета, который бронируется.
     */
    private Long item;

    /**
     * Идентификатор пользователя, который бронирует предмет.
     */
    private Long booker;

    /**
     * Статус бронирования.
     */
    private BookingStatusType status;
}

