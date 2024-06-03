/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package compra.productos;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author hp
 */
@WebService(serviceName = "compraproductos")
public class compraproductos {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "comprasProductos")
    public String comprasProductos(@WebParam(name = "CantidadPan") int CantidadPan, @WebParam(name = "CantidadQueso") int CantidadQueso, @WebParam(name = "CantidadPlatanos") int CantidadPlatanos, @WebParam(name = "CantidadNaranjas") int CantidadNaranjas) {
        // Validaciones
        if (CantidadPan < 0) {
            return "Error: La cantidad de pan no puede ser negativa.";
        }
        if (CantidadQueso < 0) {
            return "Error: La cantidad de queso no puede ser negativa.";
        }
        if (CantidadPlatanos < 0) {
            return "Error: La cantidad de plátanos no puede ser negativa.";
        }
        if (CantidadNaranjas < 0) {
            return "Error: La cantidad de naranjas no puede ser negativa.";
        }

        // Precios unitarios (por ejemplo)
        double precioPan = 1.0; // Precio por unidad de pan
        double precioQueso = 1.5; // Precio por unidad de queso
        double precioPlatanos = 0.5; // Precio por unidad de plátano
        double precioNaranjas = 0.75; // Precio por unidad de naranja

        // Cálculo del costo total
        double totalPan = CantidadPan * precioPan;
        double totalQueso = CantidadQueso * precioQueso;
        double totalPlatanos = CantidadPlatanos * precioPlatanos;
        double totalNaranjas = CantidadNaranjas * precioNaranjas;
        double costoTotal = totalPan + totalQueso + totalPlatanos + totalNaranjas;

        // Respuesta estructurada
        StringBuilder respuesta = new StringBuilder();
        respuesta.append("Resumen de Compras:\n");
        respuesta.append("Cantidad de Pan: ").append(CantidadPan).append(" x $").append(precioPan).append(" = $").append(totalPan).append("\n");
        respuesta.append("Cantidad de Queso: ").append(CantidadQueso).append(" x $").append(precioQueso).append(" = $").append(totalQueso).append("\n");
        respuesta.append("Cantidad de Plátanos: ").append(CantidadPlatanos).append(" x $").append(precioPlatanos).append(" = $").append(totalPlatanos).append("\n");
        respuesta.append("Cantidad de Naranjas: ").append(CantidadNaranjas).append(" x $").append(precioNaranjas).append(" = $").append(totalNaranjas).append("\n");
        respuesta.append("Costo Total: $").append(costoTotal);

        return respuesta.toString();
    }
}
