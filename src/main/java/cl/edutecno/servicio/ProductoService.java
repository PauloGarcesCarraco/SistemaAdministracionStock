package cl.edutecno.servicio;

import cl.edutecno.modelo.Producto;
import cl.edutecno.vo.NumberVO;
import cl.edutecno.vo.ProductoVO;

public interface ProductoService {
	public ProductoVO getAllProductos();
	public ProductoVO findByNombreAndPrecio(String nombre, Integer precio);
	public ProductoVO findByNombre(String nombre);
	public ProductoVO add(Producto producto);
	public ProductoVO update(Producto producto);
	public ProductoVO delete(Producto producto);
	public ProductoVO findById(Integer id);
	public ProductoVO getPage(Integer pagina, Integer cantidad);
	NumberVO getPageCount(long registroPorPagina);
	
	
	

}
