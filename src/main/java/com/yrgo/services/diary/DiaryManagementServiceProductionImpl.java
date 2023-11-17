package com.yrgo.services.diary;

import com.yrgo.dataaccess.ActionDao;
import com.yrgo.domain.Action;
import com.yrgo.services.calls.CallHandlingService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiaryManagementServiceProductionImpl implements DiaryManagementService{
    private Set<Action> allActions= new HashSet<Action>();
    private ActionDao actionDao;

    public DiaryManagementServiceProductionImpl(ActionDao actionDao){
       this.actionDao = actionDao;
    }


    /**
     * Records an action in the diary
     *
     * @param action
     */
    @Override
    public void recordAction(Action action) {
        allActions.add(action);
    }

    /**
     * Gets all actions for a particular user that have not yet been complete
     *
     * @param requiredUser
     */
    @Override
    public List<Action> getAllIncompleteActions(String requiredUser) {
        return allActions.stream().filter(action -> !action.isComplete() && action.getOwningUser().equals(requiredUser)).collect(Collectors.toList());
    }
}
