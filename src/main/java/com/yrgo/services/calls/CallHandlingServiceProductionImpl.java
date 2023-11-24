package com.yrgo.services.calls;

import com.yrgo.dataaccess.ActionDao;
import com.yrgo.domain.Action;
import com.yrgo.domain.Call;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerManagementServiceProductionImpl;
import com.yrgo.services.customers.CustomerNotFoundException;
import com.yrgo.services.diary.DiaryManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.AssociationOverride;
import java.util.Collection;
@Service("callService")
@Transactional
public class CallHandlingServiceProductionImpl implements CallHandlingService{
    /**
     * Records a call with the customer management service, and also records
     * any actions in the diary service
     *
     * @param customerId
     * @param newCall
     * @param actions
     */

    private CustomerManagementService customerService;
    private ActionDao actionDao;
    private DiaryManagementService diaryService;
    public CallHandlingServiceProductionImpl(CustomerManagementService customerService, ActionDao actionDao, DiaryManagementService diaryService){
        this.customerService = customerService;
        this.actionDao = actionDao;
        this. diaryService = diaryService;
    }

    @Transactional
    public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException {
        customerService.recordCall(customerId, newCall);
        actions.forEach(action -> {
            actionDao.create(action);
            diaryService.recordAction(action);
        });
    }
}
