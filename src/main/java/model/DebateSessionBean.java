/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Debate;
import entity.DebateUser;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author D062572
 */
@Stateless
@LocalBean
public class DebateSessionBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "OpenDebatePU")
    private EntityManager em;

    /**
     * Creates a new debate with a topic, description, owner, creationDate, closingDate, tags.
     * By creating a new debate its status will be set to isOpen = true.
     * 
     * 
     * @param user
     * @param topic
     * @param description
     * @param tags
     * @param closingDate
     * @return
     * @throws IllegalArgumentException if the debate doesn't belong to a user; has no topic or description; if the closingDate is in the past.
     */
    public Debate createDebate(DebateUser user, String topic, String description,
            String tags, Date closingDate) throws Exception {

        if (user == null || topic == null || description == null
                || closingDate == null || closingDate.before(new Date()) || topic.length() == 0
                || description.length() == 0) {
            throw new IllegalArgumentException();
        }

        Debate newDebate = new Debate();
        newDebate.setTopic(topic);
        newDebate.setDescription(description);
        newDebate.setOwner(user);
        newDebate.setIsOpen(true);
        newDebate.setClicks(0);
        newDebate.setCreationDate(new Date());
        newDebate.setClosingDate(closingDate);
        newDebate.setTags(tags);

        em.persist(newDebate);

        return newDebate;
    }


    /**
     * Looking up a debate by ID.
     * Increase the clicks of the debate by +1.
     * If the closingDate is passed, the isOpen flag is set to false.
     * 
     * @param id
     * @return the debate given by ID
     */
    public Debate getDebateById(Long id) {

        Debate debate = em.find(Debate.class, id);
        if(debate != null){
            debate.setClicks(debate.getClicks()+1);
            
            if(debate.getClosingDate().compareTo(new Date()) < 0){
                debate.setIsOpen(false);
            }      
            em.merge(debate);         
        }
        return debate;
    }
    
    public Debate closeDebate(Long id){
        Debate debate = em.find(Debate.class, id);
        if(debate != null){
            debate.setIsOpen(false);
            em.merge(debate);
        }
        return debate;
    }

    /**
     * Gets all the debates related to a given user.
     * 
     * @param user
     * @return a list of debates
     */
    public List<Debate> getDebatesByUser(DebateUser user) {

        List<Debate> debates = em.createQuery("SELECT d "
                + "FROM Debate d "
                + "WHERE d.owner = :owner ORDER BY d.creationDate DESC")
                .setParameter("owner", user)
                .getResultList();

        return debates;
    }
}
