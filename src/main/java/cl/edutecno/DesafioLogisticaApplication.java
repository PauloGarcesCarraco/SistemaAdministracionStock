package cl.edutecno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cl.edutecno.modelo.Producto;
import cl.edutecno.servicio.ProductoService;

@SpringBootApplication
public class DesafioLogisticaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DesafioLogisticaApplication.class, args);

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//		ProductoService svc=context.getBean(ProductoService.class);
//		
//		//Producto auxProducto = new Producto(1, "taladro", 1990,2);
//		
//		System.out.println(svc.getAllProductos());
//	}
	}
}
