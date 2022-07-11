package ru.interrao.itrepair.Web.Entity.Reports;

public enum ReportStatusEnum
{
    CREATED("Конденсатор"),
    PROCESS("Резистор"),
    DONE("Трансформатор"),
    REJECT("Диод"),
    OTHER("Другое");

    private String name;

    ReportStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
