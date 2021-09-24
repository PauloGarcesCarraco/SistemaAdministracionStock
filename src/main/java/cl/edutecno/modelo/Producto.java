package cl.edutecno.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "SQ_PRODUCTOS", initialValue = 1, allocationSize = 1, sequenceName = "SQ_PRODUCTOS")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUCTOS")
	private Integer idProducto;
	private String nombre;
	private Integer precio;
	private Integer stock;

}
