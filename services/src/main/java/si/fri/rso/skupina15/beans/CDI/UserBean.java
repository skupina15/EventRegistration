package si.fri.rso.skupina15.beans.CDI;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.skupina15.entities.Persone;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UserBean {
    private Logger log = Logger.getLogger(UserBean.class.getName());

    @PostConstruct
    private void init(){
        log.info("Initialization " + UserBean.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinitialization"+ UserBean.class.getSimpleName());
    }

    @PersistenceContext(unitName = "climb-jpa")
    private EntityManager em;

    public List<Persone> findAllUsers(QueryParameters query) {
        return JPAUtils.queryEntities(em, Persone.class, query);
    }

    public Long usersCount(QueryParameters query) {
        return JPAUtils.queryEntitiesCount(em, Persone.class, query);
    }

    @Transactional
    public Persone createPersone(Persone persone){
        if(persone != null) {
            if(persone.getId_persone() == null) {
                log.info("Can't create new user. ID is not defined.");
                return null;
            }

            em.persist(persone);
        }
        return persone;
    }

    public Persone findPersone(int id_persone){
        try {
            return em.find(Persone.class, id_persone);
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public Persone updateUser(int id_persone, Persone persone){
        Persone i = findPersone(id_persone);
        if(i == null){
            return null;
        }
        persone.setId_persone(i.getId_persone());
        em.merge(persone);
        return persone;
    }

    @Transactional
    public int deleteUser(int id_persone){
        Persone i = findPersone(id_persone);
        if(i != null){
            em.remove(i);
        }
        return id_persone;
    }
}
