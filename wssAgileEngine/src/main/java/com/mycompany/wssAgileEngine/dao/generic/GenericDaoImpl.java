package com.mycompany.wssAgileEngine.dao.generic;


import com.mycompany.wssAgileEngine.enumaration.RestrictionEnum;
import com.mycompany.wssAgileEngine.services.generic.Fieldeable;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID>{    
    
    private final Class<T> persistentClass;          
//    @Autowired
//    SessionFactory sessionFactory;   
          
    public GenericDaoImpl() {  
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()  
                                .getGenericSuperclass()).getActualTypeArguments()[0];  
     }    
  
//    protected Session getSession(boolean applyFilter) {  
////     Session s =  sessionFactory.getCurrentSession();     
//  
//     if (s == null)  
//            throw new IllegalStateException("La sesión no ha sido instanciada correctamente");  
//        return s;  
//    }  
  
    private Class<T> getPersistentClass() {  
        return persistentClass;  
    }  
  
//@Override    
//    public T obtenerPorId(ID id) {  
////        return  (T) getSession(false).get(getPersistentClass(), id);            
//    }  
    
//    @Override    
//    public T obtenerPorPropiedadesIguales(Map<Fieldeable, Serializable> props) { 
//        Map map = new HashMap();
////        Criteria c = this.createCriteria();
//        for (Map.Entry<Fieldeable, Serializable> e : props.entrySet()) {
//            map.put(e.getKey().getField(), e.getValue());
//        }
//        this.addAliases(c, this.getAliases());
//        return  (T) c.add(Restrictions.allEq(map)).uniqueResult();            
//    }  
    
//    @Override
//    public List<T> obtenerPorCriterios(Map<Fieldeable, Map<RestrictionEnum, Serializable>> props){      
//         return this.findByCriteriaWithDefaultFilter(this.createCriterions(props));
//    }
  
    
    protected List<Criterion> createCriterions(Map<Fieldeable, Map<RestrictionEnum, Serializable>> props){
        List<Criterion> crits = new ArrayList<>();
        for (Map.Entry<Fieldeable , Map<RestrictionEnum, Serializable>> entry : props.entrySet()) {
            for (Map.Entry<RestrictionEnum, Serializable> e : entry.getValue().entrySet()) {
                crits.add(e.getKey().buildRestriction(entry.getKey(), e.getValue()));
            }            
        }
        return crits;
    }
    
//    @Override    
//    public List<T> obtenerTodos() {  
//        return findByCriteriaWithDefaultFilter(EMPTY_RESTRICTIONS);  
//    }          
//    
//    @Override        
//    public T  agregarOActualizar(T entidad) throws Exception{                
//        getSession(false).saveOrUpdate(entidad);            
//        this.setContenidoSesionMedica(entidad);
//        return entidad;  
//    }
    
    @Override    
    public List<T>  agregarOActualizarMuchos(List<T> entidades) throws Exception{          
        for (T entidad : entidades) {
            agregarOActualizar(entidad);                        
        }

        return entidades;  
    }
  
//    @Override       
//    public void eliminar(T entidad) throws Exception{           
//        getSession(false).delete(entidad);  
//    }  
    
    @Override    
    public void eliminarMuchos(List<T> entidades) throws Exception{
        for (T entidad : entidades) {
            eliminar(entidad);
        }
    }

//    @Override
//    public void refresh(T entidad) throws Exception {
//        getSession(false).refresh(entidad);
//    }        
    
    protected void setContenidoSesionMedica(T entidad) throws Exception {        
    }                         
  
    protected List<Order> getOrderByForAll(){
     return EMPTY_ORDERS;   
    }
    
    protected List<Criterion> getDefaultFilter(){
        return EMPTY_RESTRICTIONS;
    }  
    
    protected List<Fieldeable> getAliases(){
     return EMPTY_ALIASES;
    }        
  
//    protected List<T> findByCriteriaWithDefaultFilter(List<Criterion> criterions) {  
//        Criteria crit = createCriteria();         
//         this.addAliases(crit, this.getAliases());
//         this.addOrder(crit, this.getOrderByForAll());
//         this.addRestrictions(crit, this.getDefaultFilter());        
//        return findByCriteria(crit, criterions);
//   }        
   
    protected List<T> findByCriteria(Criteria criteria, List<Criterion> criterions) {          
        this.addRestrictions(criteria, criterions);
        return criteria.list();  
   }               
    
   protected Criteria addOrder(Criteria criteria, List<Order> ordenes) {           
       for (Order o : ordenes) {
           criteria.addOrder(o);
       }
        return criteria;
   }
   
   protected Criteria addRestrictions(Criteria criteria, List<Criterion> restrictions) {
         for (Criterion c : restrictions) {
           criteria.add(c);
       }
        return criteria;
    }

//    protected Criteria createCriteria(){
//        return getSession(true).createCriteria(getPersistentClass());  
//    }
    

protected String createAlias ( Criteria criteria, String associationPath )
    {
        return createAlias( criteria, criteria, criteria.getAlias(),
                associationPath, JoinType.INNER_JOIN);
    }

protected String createAlias ( Criteria rootCriteria,
                                        Criteria currentSubCriteria, String alias,
                                        String associationPath, JoinType joinType )
    {
        StringTokenizer st = new StringTokenizer( associationPath, ".");
        if ( st.countTokens() == 1 )
        {
            return alias + "." + associationPath;
        }
        else
        {
            String associationPathToken = st.nextToken();
            String newAlias = alias + "_" + associationPathToken;
            Criteria criteriaForAlias = findCriteriaForAlias( rootCriteria,
                    newAlias );
            if(criteriaForAlias == null){
                criteriaForAlias = findCriteriaForAlias( rootCriteria,
                    associationPathToken );
                if(criteriaForAlias == null){
                    currentSubCriteria = currentSubCriteria.createCriteria(
                        associationPathToken, newAlias, joinType );
                }else newAlias = associationPathToken;
            }else{                            
                currentSubCriteria = criteriaForAlias;
            }
            String newKey = associationPath.substring( associationPathToken
                    .length() + 1, associationPath.length() );
            return createAlias( rootCriteria, currentSubCriteria, newAlias,
                    newKey, joinType );
        }
    }

    protected Criteria findCriteriaForAlias ( Criteria criteria, String alias )
    {
        Iterator subcriterias = ( ( CriteriaImpl ) criteria )
                .iterateSubcriteria();
        while ( subcriterias.hasNext() )
        {
            Criteria subcriteria = ( Criteria ) subcriterias.next();
            if ( subcriteria.getAlias().equals( alias ) )
            {
                return subcriteria;
            }
        }
        return null;
    }   

    protected void addAliases(Criteria crit, List<Fieldeable> aliases) {
       String al;
       for (Fieldeable alias : aliases.toArray(new Fieldeable[0])) {
         String aliasField = alias.getField();
         al = this.createAlias(crit, aliasField);
         crit.createAlias(al, aliasField.contains(".") ? aliasField.substring(aliasField.lastIndexOf(".")+1) : aliasField, JoinType.LEFT_OUTER_JOIN);
        }                
    }            
    
}