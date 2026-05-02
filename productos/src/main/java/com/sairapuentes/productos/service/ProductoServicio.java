package com.sairapuentes.productos.service;

import com.sairapuentes.productos.dto.ProductoRequest;
import com.sairapuentes.productos.entity.Producto;
import com.sairapuentes.productos.dto.ProductoResponse;
import com.sairapuentes.productos.repository.IProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductoServicio implements IProductoServicio {

    private final IProductoRepositorio productoRepositorio;


    public ProductoServicio(IProductoRepositorio productoRepositorio){
        this.productoRepositorio = productoRepositorio;
    }
    @Override
    public List<ProductoResponse> findAll() {

        return ((List<Producto>) productoRepositorio.findAll())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoResponse findById(int id) {
        Producto producto = productoRepositorio.findById(id)
                .orElseThrow(() ->new RuntimeException("Producto no encontrado"));
        return mapToResponse(producto);
    }

    @Override
    public List<ProductoResponse> findByIdCategoria(int idCategoria) {
        return productoRepositorio.findByIdCategoria(idCategoria)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoResponse save(ProductoRequest request) {

        Producto producto = new Producto();
        producto.setIdCategoria(request.getIdCategoria());
        producto.setNombreProducto(request.getNombreProducto());
        producto.setPrecioCompra(request.getPrecioCompra());
        producto.setIvaCompra(request.getIvaCompra());

        //Operacion para que se actualice el precio de venta segun el valor de compra e iva ingresados
        double precioVenta = request.getPrecioCompra() * (1 + request.getIvaCompra());
        producto.setPrecioVenta(precioVenta);

        Producto guardar = productoRepositorio.save(producto);
        return mapToResponse(guardar);
    }

    @Override
    public ProductoResponse update(int id, ProductoRequest request){
        Producto producto = productoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto no fue encontrado"));
        producto.setIdCategoria(request.getIdCategoria());
        producto.setNombreProducto(request.getNombreProducto());
        producto.setPrecioCompra(request.getPrecioCompra());
        producto.setIvaCompra(request.getIvaCompra());

        //Operacion para que se actualice el precio de venta segun el valor de compra e iva ingresados
        double precioVenta = request.getPrecioCompra() * (1 + request.getIvaCompra());
        producto.setPrecioVenta(precioVenta);

        Producto actualizar = productoRepositorio.save(producto);
        return mapToResponse(actualizar);
    }
    @Override
    public void eliminar(int id) {
        productoRepositorio.deleteById(id);
    }

    private ProductoResponse mapToResponse (Producto producto){
        return new ProductoResponse(
                producto.getIdProducto(),
                producto.getNombreProducto(),
                producto.getPrecioVenta(),
                producto.getPrecioCompra(),
                producto.getIvaCompra()
        );
    }

//    @Override
//    public List<VentaResponse> findByIDProductos(Integer ID_producto){
//        return producto_comunicacion.findByIDProducto(ID_producto);
//    }
//    @Override
//    public ProductoResponse findAllVentasByProducto(Integer idProducto) {
//        Producto producto = producto_repositorio.findById(idProducto).orElseThrow();
//        List<Producto> productoList = client.findAllVentasByProducto(idProducto);
//        return new ProductoResponse(productoList);
//    }
}
