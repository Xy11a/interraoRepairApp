package ru.interrao.itrepair.Web.Entity.ElectroComponents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Capacitor implements Component
{
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Вольт", nullable = false)
    private float voltage;

    @Column(name = "Фарад", nullable = false)
    private String capacity;

    @Column(name = "Производитель", nullable = true)
    private String manufacturer;

    @Column(name = "Кол-во", nullable = false)
    private int amount;

    @Override
    public void about() {
        System.out.println("Конденсатор "+ manufacturer+":"+voltage +"v " + capacity);
    }
}
