package ru.interrao.itrepair.Web.Entity.ElectroComponents;

public enum ComponentEnum
{
    CAPACITOR("Конденсатор"),
    RESISTOR("Резистор"),
    TRANSFORMATOR("Трансформатор"),
    DIODE("Диод"),
    OTHER("Другое");

    private String name;

    ComponentEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
