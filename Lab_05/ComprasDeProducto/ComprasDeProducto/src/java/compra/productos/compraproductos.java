/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/EjbWebService.java to edit this template
 */
package compra.productos;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Lenovo
 */
@WebService(serviceName = "compraproductos")
@Stateless()
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
    public String comprasProductos(@WebParam(name = "CantidadPan") int CantidadPan, @WebParam(name = "CantidadQueso") int CantidadQueso, @WebParam(name = "CantidadPlatanos") int CantidadPlatanos, @WebParam(name = "CantidadNaranjas") int CantidadNaranjas) throws IllegalArgumentException {
        String mensaje = "";
        double total = 0;
        if (CantidadPan < 1 || CantidadQueso < 1 || CantidadPlatanos < 1 || CantidadNaranjas < 1) {
            mensaje += "Ingreso incorrecto de las cantidades de los productos";
            throw new IllegalArgumentException("Ingreso incorrecto de las cantidades de los productos");
        } else {
            mensaje += "\n";
            total += CantidadPan * 0.50;
            mensaje += "Pan: " + CantidadPan + "Unidades --> SubTotal: " + CantidadPan * 0.50;
            mensaje += "\n";
            total += CantidadQueso * 2.50;
            mensaje += "Queso: " + CantidadQueso + "Unidades --> SubTotal: " + CantidadQueso * 0.50;
            mensaje += "\n";
            total += CantidadPlatanos * 0.40;
            mensaje += "Platanos: " + CantidadPlatanos + "Unidades --> SubTotal: " + CantidadPlatanos * 0.50;
            mensaje += "\n";
            total += CantidadNaranjas * 0.40;
            mensaje += "Naranjas: " + CantidadNaranjas + "Unidades --> SubTotal: " + CantidadNaranjas * 0.50;
        }
        return mensaje;
    }
}
