package com.sairapuentes.productos.service;

import com.sairapuentes.productos.dto.CategoriaRequest;
import com.sairapuentes.productos.dto.CategoriaResponse;
import com.sairapuentes.productos.entity.Categoria;
import com.sairapuentes.productos.repository.ICategoriaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServicio implements ICategoriaServicio {
    private final ICategoriaRepositorio categoriaRepositorio;

    @Override
    public List<CategoriaResponse> findAll(){
        return ((List<Categoria>) categoriaRepositorio.findAll())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    public CategoriaServicio(ICategoriaRepositorio categoriaRepositorio){
        this.categoriaRepositorio = categoriaRepositorio;
    }
    @Override
    public CategoriaResponse findById(int id) {
        Categoria categoria = categoriaRepositorio.findById(id)
                .orElseThrow(() ->new RuntimeException("Categoria no encontrada"));
        return mapToResponse(categoria);
    }
    @Override
    public CategoriaResponse save(CategoriaRequest request) {

        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(request.getNombreCategoria());

        Categoria guardar = categoriaRepositorio.save(categoria);
        return mapToResponse(guardar);
    }
    @Override
    public CategoriaResponse update(int id, CategoriaRequest request){
        Categoria categoria = categoriaRepositorio.findById(id)
                .orElseThrow(()-> new RuntimeException("La categoria no fue encontrada"));
        categoria.setNombreCategoria(request.getNombreCategoria());
        Categoria actualizar = categoriaRepositorio.save(categoria);
        return mapToResponse(actualizar);
    }
    @Override
    public void eliminar(int id){
        Categoria categoria = categoriaRepositorio.findById(id)
                .orElseThrow(()-> new RuntimeException("Categoria no encontrada"));
        categoriaRepositorio.delete(categoria);
    }

    private CategoriaResponse mapToResponse (Categoria categoria){
        return new CategoriaResponse(
                categoria.getIdCategoria(),
                categoria.getNombreCategoria()
        );
    }

}
