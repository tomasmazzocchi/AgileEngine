
package com.mycompany.wssAgileEngine.enumaration;

import com.mycompany.wssAgileEngine.services.generic.Fieldeable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.CalendarType;
import org.hibernate.type.FloatType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

/**
 *
 * @author Tomas Mazzocchi
 */
public enum RestrictionEnum {
    EQ{
        @Override
    public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
            return Restrictions.eq(k.getField(), v);
        }

    },
    GE{
         @Override
       public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
             return Restrictions.ge(k.getField(), v);
    }
    },
    LE{
         @Override
       public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
             return Restrictions.le(k.getField(), v);    
       }            
    },
    GT{
         @Override
       public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
             return Restrictions.gt(k.getField(), v);    
       }            
    },    
    LT{
         @Override
       public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
             return Restrictions.lt(k.getField(), v);    
       }            
    },
    EQ_OR_NULL{
        @Override
     public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
            return Restrictions.eqOrIsNull(k.getField(), v);
        }

    },    
    GE_OR_NULL{
        @Override
     public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
            return Restrictions.or(Restrictions.isNull(k.getField()), Restrictions.ge(k.getField(), v));
        }

    },        
    LT_OR_NULL{
        @Override
     public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
            return Restrictions.or(Restrictions.isNull(k.getField()), Restrictions.lt(k.getField(), v));
        }

    },
    GT_OR_NULL{
        @Override
     public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
            return Restrictions.or(Restrictions.isNull(k.getField()), Restrictions.gt(k.getField(), v));
        }

    },
    LE_OR_NULL{
        @Override
     public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
            return Restrictions.or(Restrictions.isNull(k.getField()), Restrictions.le(k.getField(), v));
        }

    },
    IN{
        @Override
     public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
            return Restrictions.in(k.getField(), (Collection)v);
        }

    },
    NE{
        @Override
     public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
            return Restrictions.ne(k.getField(), v);
        }

    },
    NOT_IN{
        @Override
     public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
            return Restrictions.not(Restrictions.in(k.getField(), (Collection)v));
        }

    }, 
    NULL{
         @Override
      public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
            return Restrictions.isNull(k.getField());
        }

    },
    NOT_NULL{
         @Override
       public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
             return Restrictions.isNotNull(k.getField());
    }
    },                
    LIKE{
         @Override
       public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
             return Restrictions.like(k.getField(), v.toString(), MatchMode.START);    
       }            
    },
    ILIKE{
         @Override
       public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
             return Restrictions.ilike(k.getField(), v.toString(), MatchMode.START);    
       }
    },
    BETWEEN{
         @Override
       public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
             Object[] obj = (Object[]) v;
             return Restrictions.between(k.getField(), obj[0], obj[1]);    
       }            
    },
     EQ_AND_OR_NULL{
        @Override
    public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
            Map<RestrictionEnum, Serializable> mapa = (Map<RestrictionEnum, Serializable>) v;
            List<Criterion> crit = new ArrayList<>();
            Conjunction conj = Restrictions.conjunction();
            for (Map.Entry<RestrictionEnum, Serializable> entry : mapa.entrySet()) {
                conj.add(entry.getKey().buildRestriction(k, entry.getValue()));                
            }
            return Restrictions.or(Restrictions.isNull(k.getField()), Restrictions.and(conj));
        }
    },
     SQL{
        @Override
     public <T extends Serializable> Criterion buildRestriction(Fieldeable k, T v){
         Object[] values = (Object[]) v;
         Type[] types = new Type[values.length];
         Type t = null;
         int index = 0;
            for (Object value : values) {
                if(value instanceof Long) t = LongType.INSTANCE;
                else if(value instanceof Float) t = FloatType.INSTANCE;
                else if(value instanceof String) t = StringType.INSTANCE;
                else if(value instanceof Calendar) t = CalendarType.INSTANCE;
                types[index] = t;
                index++;
            }
            return Restrictions.sqlRestriction(k.getField(), values, types);
        }

    };

   public abstract <T extends Serializable> Criterion buildRestriction(Fieldeable k, T e);
}
