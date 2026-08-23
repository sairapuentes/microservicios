package com.sairapuentes.consolidado.service;

import com.sairapuentes.consolidado.dto.ConsolidadoProductoResponse;
import com.sairapuentes.consolidado.dto.ConsolidadoResponse;
import com.sairapuentes.consolidado.dto.ConsolidadoSedeResponse;

import java.util.List;

public interface IConsolidadoServicio {
    ConsolidadoResponse obtenerConsolidado(Integer idSede, String rol);
    List<ConsolidadoSedeResponse> obtenerConsolidadoPorSede(Integer idSede, String rol);
    List<ConsolidadoProductoResponse> obtenerConsolidadoPorProducto(Integer idSede, String rol);
}
