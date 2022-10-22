package ru.interrao.itrepair.Web.Entity.Reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Reports")
@Table(name = "Reports")
public class Report implements Serializable {
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
