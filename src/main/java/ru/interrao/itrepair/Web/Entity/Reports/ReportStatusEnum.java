package ru.interrao.itrepair.Web.Entity.Reports;

public enum ReportStatusEnum
{
    CREATED("Создана"),
    PROCESS("В обработке"),
    DONE("Выполнена"),
    REJECT("Отклонена"),
    OTHER("Другое");

    private String name;

    ReportStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
