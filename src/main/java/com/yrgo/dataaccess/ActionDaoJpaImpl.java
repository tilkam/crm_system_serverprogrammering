package com.yrgo.dataaccess;

import com.yrgo.domain.Action;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ActionDaoJpaImpl implements ActionDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Action newAction) {
    em.persist(newAction);
    }

    @Override
    public List<Action> getIncompleteActions(String userId) {
        return em.createQuery("select action from Action as action" , Action.class).getResultList();

    }

    @Override
    public void update(Action actionToUpdate) throws RecordNotFoundException {
        Action actionUpdated  = em.find(Action.class, actionToUpdate.getActionId());
        em.createQuery("update Action as action set action =: action where action.actionId =: action", Action.class).setParameter("action", actionUpdated.getActionId());
    }

    @Override
    public void delete(Action oldAction) throws RecordNotFoundException {
        Action action = em.find(Action.class, oldAction.getActionId());
        em.remove(action);
    }
}
