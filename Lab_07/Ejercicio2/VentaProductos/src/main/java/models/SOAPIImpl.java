package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "models.SOAPI")
public class SOAPIImpl implements SOAPI {

    @Override
    public List<Producto> getProductos() {
        return Producto.getProductos();
    }

    @Override
    public void addProducto(Producto producto) {
    	Producto.addProducto(producto);
    }

    @Override
    public Producto getProductoById(String idProducto) {
        return Producto.getProductoById(idProducto);
    }

    @Override
    public void updateProducto(Producto producto) {
    	Producto.updateProducto(producto.getIdProducto(), producto);
    }

    @Override
    public void deleteProducto(@WebParam(name = "idProducto") String idProducto) {
    	System.out.print("Eleiminando " + idProducto);
    	Producto.deleteProducto(idProducto);
    }
}
