package ru.interrao.itrepair.Web.Entity.ElectroComponents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Components")
@Table(name = "Components")
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Type")
    @Enumerated(EnumType.STRING)
    private ComponentEnum type;

    @Column(name = "Name")
    private String name;

    @Column(name = "Vals")
    private String values;

    @Column(name = "Amount")
    private int amount;


    @Override
    public String toString() {
        return
                "Тип:" + type + ", Название:" + name + ", Показатели:" + values + ", Количество:" + amount;
    }
}
