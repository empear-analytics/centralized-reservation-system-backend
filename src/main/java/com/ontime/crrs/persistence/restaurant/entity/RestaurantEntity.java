package com.ontime.crrs.persistence.restaurant.entity;

import com.ontime.crrs.persistence.location.entity.LocationEntity;
import com.ontime.crrs.persistence.table.entity.TableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")
@ToString(exclude = "location")
public class RestaurantEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "restaurant_id",
            updatable = false,
            columnDefinition = "uuid"
    )
    private UUID id;

    @Column(
            name = "name",
            nullable = false,
            unique = true,
            length = 20
    )
    private String name;

    @Column(name = "description")
    private String description;

    @Column(
            name = "phone_number",
            nullable = false,
            length = 15
    )
    private String phoneNumber;

    @Column(name = "image")
    private String image;

    /*
        TODO:
            - add relationship to Rule
     */

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @OneToMany(
            mappedBy = "restaurant",
            cascade = ALL
    )
    private List<TableEntity> tables;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTables(List<TableEntity> tables) {
        this.tables = tables;
    }

    public RestaurantEntity(String name, String description, String phoneNumber, String image,
                            LocationEntity location, List<TableEntity> tables) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.location = location;
        this.tables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RestaurantEntity that = (RestaurantEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}