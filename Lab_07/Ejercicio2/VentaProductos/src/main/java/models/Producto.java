package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;


public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    // Atributos de la clase Producto
    private String idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadEnStock;
    private String categoria;
    private String sku; // Código de referencia del producto
    private String fabricante;
    
    // Lista de productos de ejemplo
    public static List<Producto> productos = new ArrayList<>(Arrays.asList(
        new Producto("1", "Rosa", "Rosa de color marfil", 5.99, 100, "Flores", "SKU123", "Floristeria XYZ"),
        new Producto("2", "Pepito", "Pepito de juguete", 3.49, 200, "Juguetes", "SKU456", "Jugueteria ABC"),
        new Producto("3", "Manuela", "Manuela de peluche", 7.99, 150, "Peluche", "SKU789", "Peluche Ltd.")
    ));
    
    // Constructor vacío
    public Producto() {
        super();
    }

    // Constructor con parámetros
    public Producto(String idProducto, String nombre, String descripcion, double precio, int cantidadEnStock, String categoria, String sku, String fabricante) {
        super();
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
        this.categoria = categoria;
        this.sku = sku;
        this.fabricante = fabricante;
    }

    // Getters y setters
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    // Método para obtener la lista de productos
    public static List<Producto> getProductos() {
        return productos;
    }
    
 // Método para agregar un nuevo producto a la lista
    public static void addProducto(Producto producto) {
        productos.add(producto);
    }
    
    // Método para eliminar un producto de la lista por ID
    public static void deleteProducto(String idProducto) {
    	System.out.print("Eleiminando " + idProducto);
        productos.removeIf(p -> p.getIdProducto().equals(idProducto));
    }
    
    // Método para actualizar un producto en la lista por ID
    public static void updateProducto(String idProducto, Producto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            if (producto.getIdProducto().equals(idProducto)) {
                productos.set(i, productoActualizado);
                break;
            }
        }
    }
    
    // Método para obtener un producto de la lista por ID
    public static Producto getProductoById(String idProducto) {
        for (Producto producto : productos) {
            if (producto.getIdProducto().equals(idProducto)) {
                return producto;
            }
        }
        return null;
    }
}