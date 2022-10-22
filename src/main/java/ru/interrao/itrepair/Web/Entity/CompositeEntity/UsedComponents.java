package ru.interrao.itrepair.Web.Entity.CompositeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.interrao.itrepair.Web.Entity.PK.UsedComponentsPK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "used_components")
@Table(name = "used_components")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsedComponents {
    @EmbeddedId
    @Column(name = "id")
    private UsedComponentsPK key;

    @Column(name = "amount")
    private int amount;
}
