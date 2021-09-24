package cl.edutecno.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.edutecno.DesafioLogisticaApplication;
import cl.edutecno.dao.ProductoRepository;
import cl.edutecno.modelo.Producto;
import cl.edutecno.servicio.ProductoService;
import cl.edutecno.vo.NumberVO;
import cl.edutecno.vo.ProductoVO;

@Service
public class ProductoServicioImpl implements ProductoService {

	private static final Logger log = LoggerFactory.getLogger(DesafioLogisticaApplication.class);

	@Autowired
	ProductoRepository dao;

	cl.edutecno.vo.ProductoVO respuestaVO;

	@Override
	@Transactional(readOnly = true)
	public ProductoVO getAllProductos() {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "102");
		try {
			respuestaVO.setProductos((List<Producto>) dao.findAll());
			respuestaVO
					.setMensaje(String.format("Se ha/n encontrado %d registro/s", respuestaVO.getProductos().size()));
			respuestaVO.setCodigo("0");
		} catch (Exception e) {
			log.trace("Producto service: error en getAllProductos", e);
		}

		return respuestaVO;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoVO findByNombreAndPrecio(String nombre, Integer precio) {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "101");
		try {
			List<Producto> productos = dao.findByNombreAndPrecio(nombre, precio);
			if (productos.size() > 0) {
				respuestaVO.setProductos(productos);
				respuestaVO.setMensaje("Producto encontrado correctamente");
				respuestaVO.setCodigo("0");
			}
		} catch (Exception e) {
			log.trace("Usuario Service: Error en findByNombreAndPrecio", e);
		}
		return respuestaVO;
	}

	@Override
	@Transactional
	public ProductoVO add(Producto producto) {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "103");
		try {
			dao.save(producto);
			respuestaVO.setMensaje(String.format("Se ha agregado %s al registro", producto.getNombre()));
			respuestaVO.setCodigo("0");
		} catch (Exception e) {
			log.trace("Usuario Service: Error en add", e);
		}
		return respuestaVO;
	}

	@Override
	@Transactional
	public ProductoVO update(Producto producto) {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "104");
		try {
			dao.save(producto);
			respuestaVO
					.setMensaje(String.format("Se ha actualizado correctamente al producto %s", producto.getNombre()));
			respuestaVO.setCodigo("0");

		} catch (Exception e) {
			log.trace("Usuario Service: Error en update", e);
		}

		return respuestaVO;
	}

	@Override
	@Transactional
	public ProductoVO delete(Producto producto) {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "105");
		try {
			dao.delete(producto);
			respuestaVO.setMensaje("Se ha eliminado correctamente al producto");
			respuestaVO.setCodigo("0");

		} catch (Exception e) {
			log.trace("Usuario Service: Error en delete", e);
		}
		return respuestaVO;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoVO findById(Integer id) {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "106");
		try {
			Producto producto = dao.findById(id).get();
			respuestaVO.getProductos().add(producto);
			respuestaVO.setMensaje("Producto encontrado correctamente");
			respuestaVO.setCodigo("0");
		} catch (Exception e) {
			log.trace("Usuario Service: Error en findByNombreAndClave", e);
		}
		return respuestaVO;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoVO getPage(Integer pagina, Integer cantidad) {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "107");
		try {
			Pageable pageable = PageRequest.of(pagina, cantidad);
			Page<Producto> responsePage = dao.findAll(pageable);
			respuestaVO.setProductos(responsePage.getContent());
			respuestaVO
					.setMensaje(String.format("Se ha/n encontrado %d registro/s", respuestaVO.getProductos().size()));
			respuestaVO.setCodigo("0");
		} catch (Exception e) {
			log.trace("Usuario Service: Error en getPage", e);
		}

		return respuestaVO;
	}

	@Override
	@Transactional(readOnly = true)
	public NumberVO getPageCount(long registroPorPagina) {
		NumberVO respuesta = new NumberVO(0, "Ha ocurrido un error", "109");
		try {
			long registros = dao.count();
			if (registroPorPagina == 0 && registros == 0) {
				respuesta.setValor(1);
			} else {
				respuesta.setValor((registros / registroPorPagina) + (registros % registroPorPagina == 0 ? 0 : 1));
			}
			respuesta.setCodigo("201");
			respuesta.setMensaje(String.format("Hay %d paginas", respuesta.getValor()));

		} catch (Exception e) {
			log.trace("Usuario Service: Error en getPageCount", e);
		}

		return respuesta;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoVO findByNombre(String nombre) {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "110");
		try {
			respuestaVO.setProductos((List<Producto>)dao.findByNombre(nombre));
			respuestaVO
			.setMensaje(String.format("Se ha/n encontrado %d registro/s", respuestaVO.getProductos().size()));
			respuestaVO.setCodigo("0");
		} catch (Exception e) {
			log.trace("Usuario Service: Error en findByNombre", e);
		}
		return respuestaVO;
	}

}
