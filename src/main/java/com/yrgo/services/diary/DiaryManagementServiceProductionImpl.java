package com.yrgo.services.diary;

import com.yrgo.dataaccess.ActionDao;
import com.yrgo.domain.Action;
import com.yrgo.services.calls.CallHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class DiaryManagementServiceProductionImpl implements DiaryManagementService{

    private Set<Action> allActions= new HashSet<>();


    /**
     * Records an action in the diary
     *
     * @param action
     */

    public void recordAction(Action action) {
        allActions.add(action);
    }

    /**
     * Gets all actions for a particular user that have not yet been complete
     *
     * @param requiredUser
     */


    public List<Action> getAllIncompleteActions(String requiredUser) {
        return allActions.stream().filter(action -> !action.isComplete() && action.getOwningUser().equals(requiredUser)).collect(Collectors.toList());
    }
}
