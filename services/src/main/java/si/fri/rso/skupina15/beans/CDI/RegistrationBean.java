package si.fri.rso.skupina15.beans.CDI;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.skupina15.entities.Registration;

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
public class RegistrationBean {
    private Logger log = Logger.getLogger(RegistrationBean.class.getName());

    @PostConstruct
    private void init(){
        log.info("Initialization " + RegistrationBean.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinitialization"+ RegistrationBean.class.getSimpleName());
    }

    @PersistenceContext(unitName = "climb-jpa")
    private EntityManager em;

    public List<Registration> findAllRegistrations(QueryParameters query) {
        return JPAUtils.queryEntities(em, Registration.class, query);
    }

    public Long registrationsCount(QueryParameters query) {
        return JPAUtils.queryEntitiesCount(em, Registration.class, query);
    }

    @Transactional
    public Registration createRegistration(Registration registration){
        if(registration != null) {
            if(registration.getId_registration() == null) {
                log.info("Can't create new registration. ID is not defined.");
                return null;
            }

            em.persist(registration);
        }
        return registration;
    }

    public Registration findRegistration(int id_registration){
        try {
            return em.find(Registration.class, id_registration);
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public Registration updateRegistration(int id_registration, Registration registration){
        Registration i = findRegistration(id_registration);
        if(i == null){
            return null;
        }
        registration.setId_registration(i.getId_registration());
        em.merge(registration);
        return registration;
    }

    @Transactional
    public int deleteRegistration(int id_registration){
        Registration i = findRegistration(id_registration);
        if(i != null){
            em.remove(i);
        }
        return id_registration;
    }
}
