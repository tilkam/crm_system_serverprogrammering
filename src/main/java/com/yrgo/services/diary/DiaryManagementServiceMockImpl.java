package com.yrgo.services.diary;

import com.yrgo.domain.Action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiaryManagementServiceMockImpl implements DiaryManagementService {

    private Set<Action> allActions = new HashSet<>();

    @Override
    public void recordAction(Action action) {
        allActions.add(action);
    }

    //Hint:
    //Create a list<Action>
    //In the for each loop going through the list use this condition: "if(action.getOwningUser().equals(requiredUser) && !action.isComplete())" to add a new action to the list.
    public List<Action> getAllIncompleteActions(String requiredUser) {
        List<Action> incompleteList = new ArrayList<>();
        allActions.forEach(action -> {
            if (action.getOwningUser().equals(requiredUser) && !action.isComplete()) {
                incompleteList.add(action);
            }
        });
        return incompleteList;
    }

}
