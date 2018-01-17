/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBlunarLander;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author Fabian
 */
public class ConfigurationJpaController implements Serializable {

    public ConfigurationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Configuration findConfiguration(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Configuration.class, id);
        } finally {
            em.close();
        }
    }

    public void create(Configuration conf) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(conf);
        em.getTransaction().commit();
    }

    public Configuration findIdfromIdUser(Users findIdByUsername) {
        EntityManager em = getEntityManager();
        try {
            return (Configuration) em.createNamedQuery("Configuration.findByUserId").setParameter("user_id", findIdByUsername).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List findWithName(Users u) {
        EntityManager em = getEntityManager();
        return em.createQuery(
                "SELECT c.id FROM Configuration c WHERE c.userId = :userId")
                .setParameter("user_id", u)
                .setMaxResults(10)
                .getResultList();
    }

    public List findWithuId(Users u) {
        EntityManager em = getEntityManager();
        try {
            return (List) em.createNamedQuery("Configuration.findByUserId").setParameter("userId", u.getId()).getResultList();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Configuration configurationById(int id_conf) {
        EntityManager em = getEntityManager();
        try {
            return (Configuration) em.createNamedQuery("Configuration.findById").setParameter("id", id_conf).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

}
