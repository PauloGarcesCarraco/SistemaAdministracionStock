package cl.edutecno.controlador;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.edutecno.modelo.Producto;
import cl.edutecno.servicio.ProductoService;
import cl.edutecno.vo.ProductoVO;
import cl.edutecno.util.Util;

@Controller
//@RequestMapping(value = "/home", method = RequestMethod.GET)
public class ProductoController {

	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private ProductoService svc;
	
	
	//-------------------HOME-------------------------------------
	
	
	@GetMapping({ "/", "/home" })
	public String home(Model model, @RequestParam(defaultValue = "1") Integer p, @RequestParam(defaultValue = "3") Integer cantidadRegistro) {
//		ProductoVO productoVO = svc.getAllProductos(); // viene de LibroService
//		((HashMap<String, Object>) model).put("productoVO", productoVO);
		Integer totalPaginas = (int) svc.getPageCount(cantidadRegistro).getValor();
		model.addAttribute("paginas", Util.getArregloPaginas(p, totalPaginas));
		model.addAttribute("paginaActual", p);
		model.addAttribute("ProductoVO", svc.getPage(p - 1, cantidadRegistro));
	
		return "home";
	}


	//-------------------AGREGAR-------------------------------------
	
	
	@GetMapping("/agregarForm")
	public String agregarForm(Model model) {
		return "agregar";// llama al mapping /agregar correspondiente al metodo agregar
	}

	@PostMapping("/agregar")
	public ModelAndView agregarSubmit(@ModelAttribute Producto producto, RedirectAttributes ra) {
		ProductoVO respuestaServicio = svc.add(producto);
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		if (respuestaServicio.getCodigo().equals("0")) {
			return new ModelAndView("redirect:/home");
		} else {
			return new ModelAndView("redirect:/agregarForm");
		}
	}
	
	//--------------------ELIMINAR------------------------------------
	

	@GetMapping("/eliminar")
	public ModelAndView eliminar(Model model, @RequestParam String idProducto, RedirectAttributes ra) {
		ProductoVO respuestaServicio = new ProductoVO();
		respuestaServicio.setMensaje("No se pudo eliminar al producto, intente nuevamente.");
		try {
			Producto eliminado = new Producto();
			eliminado.setIdProducto(Integer.parseInt(idProducto));
			respuestaServicio = svc.delete(eliminado);
			ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
			return new ModelAndView("redirect:/home");
		} catch (Exception e) {
			log.error("Error en UsuarioController eliminar", e);
		}
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		respuestaServicio = svc.getAllProductos();
		ra.addAttribute("ProductoVO", respuestaServicio);
		return new ModelAndView("redirect:/home");
	}

	//------------------EDITAR--------------------------------------
	
	
	@GetMapping("/editarForm")
	public ModelAndView editarForm(Model model, @RequestParam String idProducto, RedirectAttributes ra) {
		ProductoVO respuestaServicio = new ProductoVO();
		respuestaServicio.setMensaje("No se pudo cargar la vista de edición, intente nuevamente.");
		try {
			Integer id = (Integer.parseInt(idProducto));
			respuestaServicio = svc.findById(id);
			model.addAttribute("mensaje", respuestaServicio.getMensaje());
			model.addAttribute("ProductoVO", respuestaServicio.getProductos().get(0));
			return new ModelAndView("editar");
		} catch (Exception e) {
			log.error("Error en ProductoController eliminar", e);
		}
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		respuestaServicio = svc.getAllProductos();
		return new ModelAndView("redirect:/home");
	}

	@PostMapping("/editar")
	public ModelAndView editar(@ModelAttribute Producto producto, RedirectAttributes ra) {
		ProductoVO respuestaServicio = svc.update(producto);
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		if (respuestaServicio.getCodigo().equals("0")) {
			return new ModelAndView("redirect:/home");
		} else {
			return new ModelAndView("redirect:/editarForm");
		}
	}


	//--------------------BUSCAR------------------------------------

	
	
	@GetMapping("/buscar")
	public ModelAndView buscarForm(Model model, @RequestParam String nombre, RedirectAttributes ra) {
		ProductoVO respuestaServicio = new ProductoVO();
		respuestaServicio.setMensaje("No se encontro titulo o autor, intente nuevamente");
		try {
			respuestaServicio = svc.findByNombre(nombre);
			((HashMap<String, Object>) model).put("productoVO", respuestaServicio);
			model.addAttribute("mensaje", respuestaServicio.getMensaje());
			model.addAttribute("ProductoVO", respuestaServicio.getProductos().get(0));
			return new ModelAndView("buscar");
		} catch (Exception e) {
			log.error("Error en HomeController buscarByNombre", e);
		}
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		respuestaServicio = svc.getAllProductos();
		return new ModelAndView("redirect:/");

	}
	


}
