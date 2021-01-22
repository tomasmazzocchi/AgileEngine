package com.mycompany.wssAgileEngine.dao.generic;

import com.mycompany.wssAgileEngine.enumaration.RestrictionEnum;
import com.mycompany.wssAgileEngine.services.generic.Fieldeable;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

/**
 *
 * @author Tomas Mazzocchi
 */
 public interface GenericDao<T,ID extends Serializable> {   
    
    public static final List<Order> EMPTY_ORDERS = Collections.<Order>emptyList();
    public static final List<Criterion> EMPTY_RESTRICTIONS = Collections.<Criterion>emptyList();
    public static final List<Fieldeable> EMPTY_ALIASES = Collections.<Fieldeable>emptyList();   
    
    public abstract T obtenerPorId(ID id); 
    
    public abstract T obtenerPorPropiedadesIguales(Map<Fieldeable, Serializable> props);  
    
    public abstract List<T> obtenerPorCriterios(Map<Fieldeable, Map<RestrictionEnum, Serializable>> props);  
  
    public abstract List<T> obtenerTodos();        
    
    public abstract T agregarOActualizar(T entidad) throws Exception;  
    
    public abstract List<T>  agregarOActualizarMuchos(List<T> entidades) throws Exception;        
  
    public abstract void eliminar(T entidad) throws Exception;  
    
    public abstract void eliminarMuchos(List<T> entidades) throws Exception;  
    
    public abstract void refresh(T entidad) throws Exception;  
    
}
