package ru.practicum.shareit.item.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс, представляющий предмет.
 *
 * @author aksndr-1
 * @version 1.0
 */
@Data
@Builder
@Entity
@Table(name = "items", schema = "public")
public class Item {
    /**
     * Идентификатор предмета.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название предмета.
     */
    @Column(name = "name")
    private String name;

    /**
     * Описание предмета.
     */
    @Column(name = "description")
    private String description;

    /**
     * Доступность предмета.
     */
    @Column(name = "available")
    private Boolean available;

    /**
     * Идентификатор владельца предмета.
     */
    @Column(name = "owner")
    private Long owner;

    @Column
    private String url;

    @ElementCollection
    @CollectionTable(name="tags", joinColumns=@JoinColumn(name="item_id"))
    @Column(name="name")
    private Set<String> tags = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        return id != null && id.equals(((Item) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
