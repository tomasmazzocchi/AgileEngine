package com.mycompany.wssAgileEngine.services.generic;

import com.mycompany.wssAgileEngine.dao.generic.GenericDao;
import com.mycompany.wssAgileEngine.enumaration.RestrictionEnum;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas Mazzocchi
 */
@Service
public class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID>{         
   
    protected GenericDao<T, ID> genericDao;

    public void setGenericDao(GenericDao<T, ID> genericDao) {
        this.genericDao = genericDao;
    }   
    
    @Override
    @Transactional
    public T obtenerPorId(ID id) {  
        return  genericDao.obtenerPorId(id);            
    }  
    
    @Override
    @Transactional
    public T obtenerPorPropiedadesIguales(Map<Fieldeable, Serializable> props) {  
        return  genericDao.obtenerPorPropiedadesIguales(props);            
    } 
    
    @Override
    @Transactional
    public List<T> obtenerPorCriterios(Map<Fieldeable, Map<RestrictionEnum, Serializable>> props) {  
        return  genericDao.obtenerPorCriterios(props);            
    } 
    
    @Override
    @Transactional
    public List<T> obtenerTodos() {  
        return genericDao.obtenerTodos();  
    }        
    
    @Override    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public T  agregarOActualizar(T entidad) throws Exception{                      
       return genericDao.agregarOActualizar(entidad);                          
    }
    
    @Override    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<T>  agregarOActualizarMuchos(List<T> entidades) throws Exception{          
                 return genericDao.agregarOActualizarMuchos(entidades);                             
    }
  
    @Override   
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public void eliminar(T entidad) throws Exception{  
        genericDao.eliminar(entidad);  
    }  
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public void eliminarMuchos(List<T> entidades) throws Exception{
        genericDao.eliminarMuchos(entidades);
    }              

    @Override
    public void refresh(T entidad) throws Exception {
        genericDao.refresh(entidad);
    }
    
}