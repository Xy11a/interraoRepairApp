package ru.interrao.itrepair.Web.Entity.Provider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Providers")
@Table(name = "Providers")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

}
