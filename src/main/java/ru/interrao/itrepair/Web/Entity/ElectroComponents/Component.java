package ru.interrao.itrepair.Web.Entity.ElectroComponents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Components")
@Table(name = "Components")
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
}
