package ru.interrao.itrepair.Web.Entity.Reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.interrao.itrepair.Web.Entity.Auth.User;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Reports")
@Table(name = "Reports")

public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Owner")
    private String owner;

    @Column(name = "Date_Report")
    private Date date;

    @Column(name = "Report")
    private String report;

    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    ReportStatusEnum status;

}
