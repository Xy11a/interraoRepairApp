package ru.interrao.itrepair.Web.Entity.PK;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsedComponentsPK implements Serializable {
    @Column(name = "report_id")
    private int reportId;

    @Column(name = "component_id")
    private int componentId;

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsedComponentsPK that = (UsedComponentsPK) o;
        return reportId == that.reportId && componentId == that.componentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, componentId);
    }

    @Override
    public String toString() {
        return "UsedComponentsPK{" +
                "reportId=" + reportId +
                ", componentId=" + componentId +
                '}';
    }
}
