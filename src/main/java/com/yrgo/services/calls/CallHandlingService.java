package com.yrgo.services.calls;

import java.util.Collection;

import com.yrgo.domain.Action;
import com.yrgo.domain.Call;
import com.yrgo.services.customers.CustomerNotFoundException;

public interface CallHandlingService {
	// DO NOT IMPLEMENT THIS INTERFACE UNTIL A LATER CHAPTER!

	/**
	 * Records a call with the customer management service, and also records
	 * any actions in the diary service
	 */
	public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException;
}
