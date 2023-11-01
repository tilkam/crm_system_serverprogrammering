 package com.yrgo.services.diary;

import java.util.List;

import com.yrgo.domain.Action;

public interface DiaryManagementService {
	/**
	 * Records an action in the diary
	 */
	public void recordAction(Action action);

	/**
	 * Gets all actions for a particular user that have not yet been complete
	 */
	public List<Action> getAllIncompleteActions(String requiredUser);
}
