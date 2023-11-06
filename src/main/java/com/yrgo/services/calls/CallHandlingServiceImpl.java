package com.yrgo.services.calls;

import com.yrgo.domain.Action;
import com.yrgo.domain.Call;
import com.yrgo.services.customers.CustomerManagementMockImpl;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerNotFoundException;
import com.yrgo.services.diary.DiaryManagementService;

import java.util.Collection;

public class CallHandlingServiceImpl implements CallHandlingService{

    private CustomerManagementService customerManagementService;
    private DiaryManagementService diaryManagementService;

    public CallHandlingServiceImpl(CustomerManagementService customerManagementService, DiaryManagementService diaryManagementService){
        this.customerManagementService = customerManagementService;
        this.diaryManagementService = diaryManagementService;
    }


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
        customerManagementService.recordCall(customerId,newCall);
        actions.forEach(b -> {
            diaryManagementService.recordAction(b);
        });

    }
}
