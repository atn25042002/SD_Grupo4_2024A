package models;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SOAPI {

    @WebMethod
    public List<Producto> getProductos();

    @WebMethod
    public void addProducto(Producto producto);

    @WebMethod
    public Producto getProductoById(String idProducto);

    @WebMethod
    public void updateProducto(Producto producto);

    @WebMethod
    public void deleteProducto(String idProducto);
}