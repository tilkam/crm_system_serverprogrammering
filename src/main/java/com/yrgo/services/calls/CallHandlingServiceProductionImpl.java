package com.yrgo.services.calls;

import com.yrgo.domain.Action;
import com.yrgo.domain.Call;
import com.yrgo.services.customers.CustomerNotFoundException;

import java.util.Collection;

public class CallHandlingServiceProductionImpl implements CallHandlingService{
    /**
     * Records a call with the customer management service, and also records
     * any actions in the diary service
     *
     * @param customerId
     * @param newCall
     * @param actions
     */
    @Override
    public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException {

    }
}
