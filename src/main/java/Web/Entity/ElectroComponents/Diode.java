package Web.Entity.ElectroComponents;

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
public class Diode implements Component {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Название", nullable = false)
    private String name;

    @Column(name = "Производитель", nullable = true)
    private String manufacturer;

    @Column(name = "Предельное напряжение", nullable = false)
    private int voltageLimit;


    @Override
    public void about() {
        System.out.println("Диод  " + name + " " + manufacturer + ":" + voltageLimit + " ом");
    }
}
