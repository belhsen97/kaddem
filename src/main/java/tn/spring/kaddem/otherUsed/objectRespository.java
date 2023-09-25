package tn.spring.kaddem.otherUsed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import tn.spring.kaddem.entities.Universite;

import java.io.Serializable;

@Repository
public interface objectRespository{}//<T  ,ID   >  extends JpaRepository<T, ID> {   }
//public interface objectRespository<T  ,ID   >  extends JpaRepository<T, ID> {   }
//public interface objectRespository{}//<T  ,IdType   extends Serializable >  extends JpaRepository<T, IdType> {   }


/*T: Domain type that repository manages (Generally the Entity/Model class name)
ID: Type of the id of the entity that repository manages (Generally the wrapper class of your @Id that is created inside the Entity/Model class)*/