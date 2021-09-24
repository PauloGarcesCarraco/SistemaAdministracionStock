package cl.edutecno.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import cl.edutecno.modelo.Producto;



public interface ProductoRepository extends CrudRepository<Producto, Integer>, PagingAndSortingRepository<Producto, Integer> {


	 @Query("FROM Producto WHERE nombre = ?1 and precio = ?2")
	    public List<Producto> findByNombreAndPrecio(String nombre, Integer precio);
	 
	 @Query("FROM Producto WHERE nombre LIKE %?1%")
	 public List<Producto> findByNombre(String nombre);
	
	
}
