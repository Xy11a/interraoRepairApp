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
public class Resistor implements  Component
{
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Сопротивление",nullable = false)
    private String resistance;

    @Column(name = "Производитель",nullable = true)
    private String manufacturer;

    @Column(name = "Кол-во",nullable = false)
    private int amount;

    @Override
    public void about() {
        System.out.println("Резистор "+ manufacturer +":"+resistance+" ом");
    }
}
