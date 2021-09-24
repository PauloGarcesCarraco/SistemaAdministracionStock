package cl.edutecno.vo;

import java.util.List;

import javax.persistence.Entity;

import cl.edutecno.modelo.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductoVO extends GenericVO{
	
	List<Producto>productos;

	public ProductoVO(List<Producto> productos, String mensaje, String codigo) {
		super(mensaje, codigo);
		this.productos=productos;
	}

	

}
