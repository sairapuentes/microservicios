package com.sairapuentes.productos.service;

import com.sairapuentes.productos.dto.ProductoRequest;
import com.sairapuentes.productos.entity.Categoria;
import com.sairapuentes.productos.entity.Producto;
import com.sairapuentes.productos.dto.ProductoResponse;
import com.sairapuentes.productos.repository.ICategoriaRepositorio;
import com.sairapuentes.productos.repository.IProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductoServicio implements IProductoServicio {

    private final IProductoRepositorio productoRepositorio;
    private final ICategoriaRepositorio categoriaRepositorio;


    public ProductoServicio(IProductoRepositorio productoRepositorio, ICategoriaRepositorio categoriaRepositorio){
        this.productoRepositorio = productoRepositorio;
        this.categoriaRepositorio = categoriaRepositorio;
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
        return productoRepositorio.findByCategoria_IdCategoria(idCategoria)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoResponse save(ProductoRequest request) {

        Categoria categoria = categoriaRepositorio
                .findById(request.getIdCategoria())
                .orElseThrow(()-> new RuntimeException("Categoria no encontrada"));

        Producto producto = new Producto();
        producto.setCategoria(categoria);
        producto.setNombreProducto(request.getNombreProducto());
        producto.setPrecioCompra(request.getPrecioCompra());
        producto.setIvaCompra(request.getIvaCompra());

        //Operacion para que se actualice el precio de venta segun el valor de compra e iva ingresados
        double precioVenta = request.getPrecioCompra() * (1 + request.getIvaCompra()/100);
        precioVenta = Math.round(precioVenta * 100.0)/100.0;
        producto.setPrecioVenta(precioVenta);

        Producto guardar = productoRepositorio.save(producto);
        return mapToResponse(guardar);
    }

    @Override
    public ProductoResponse update(int id, ProductoRequest request){
        Categoria categoria = categoriaRepositorio
                .findById(request.getIdCategoria())
                .orElseThrow(()-> new RuntimeException("Categoria no encontrada"));

        Producto producto = productoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto no fue encontrado"));
        producto.setCategoria(categoria);
        producto.setNombreProducto(request.getNombreProducto());
        if(request.getPrecioCompra() <= 0){
            throw new RuntimeException("Precio invalido");
        }
        producto.setPrecioCompra(request.getPrecioCompra());
        if(request.getIvaCompra() < 0){
            throw new RuntimeException("Iva invalido");
        }
        producto.setIvaCompra(request.getIvaCompra());

        //Operacion para que se actualice el precio de venta segun el valor de compra e iva ingresados
        double precioVenta = request.getPrecioCompra() * (1 + request.getIvaCompra()/100);
        precioVenta = Math.round(precioVenta * 100.0)/100.0;
        producto.setPrecioVenta(precioVenta);

        Producto actualizar = productoRepositorio.save(producto);
        return mapToResponse(actualizar);
    }
    @Override
    public void eliminar(int id) {
        Producto producto = productoRepositorio.findById(id)
                        .orElseThrow(()-> new RuntimeException("Producto no encontrado"));
        productoRepositorio.delete(producto);
    }

    private ProductoResponse mapToResponse (Producto producto){
        return new ProductoResponse(
                producto.getIdProducto(),
                producto.getCategoria().getNombreCategoria(),
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
