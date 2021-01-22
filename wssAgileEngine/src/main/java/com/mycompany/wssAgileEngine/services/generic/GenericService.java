package com.mycompany.wssAgileEngine.services.generic;

import com.mycompany.wssAgileEngine.enumaration.RestrictionEnum;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Tomas Mazzocchi
 */
public interface GenericService<T, ID extends Serializable>{     
    public abstract T obtenerPorId(ID id);
//<editor-fold defaultstate="collapsed" desc="comment">

    /**
     * Busca solo 1 por todas las propiedades iguales a su valor pasadas en props. No utilizar si pueden existir más de un registro con esas condiciones. En ese caso usar {@link #obtenerPorCriterios}
     *
     * @param props
     * @return T
     * @throws NonUniqueResultException si hay más de un registro.
     *
     */
//</editor-fold>
    public abstract T obtenerPorPropiedadesIguales(Map<Fieldeable, Serializable> props);
//<editor-fold defaultstate="collapsed" desc="comment">    

    /**
     * Busca todos por las propiedades y restricciones pasadas en props.
     *
     * @param props
     * @return List<T>
     *
     */
//</editor-fold>
    public abstract List<T> obtenerPorCriterios(Map<Fieldeable, Map<RestrictionEnum, Serializable>> props);
//<editor-fold defaultstate="collapsed" desc="comment">

    /**
     * Igual a {@link #obtenerPorCriterios} pero con paginacion y/u orden, ignorando el que se aplica por default
     *
     * @param props
     * @return List<T>
     *
     */
//</editor-fold>
    public abstract List<T> obtenerTodos();

    public abstract T agregarOActualizar(T entidad) throws Exception;

    public abstract List<T> agregarOActualizarMuchos(List<T> entidades) throws Exception;

    public abstract void eliminar(T entidad) throws Exception;

    public abstract void eliminarMuchos(List<T> entidades) throws Exception;

    public abstract void refresh(T entidad) throws Exception;
}
