package com.hrssc.service;

import com.hrssc.entities.Contract;
import com.hrssc.entities.Interaction;
import javassist.NotFoundException;

public interface ContractService {

    Interaction loadContract(int interactionId) throws NotFoundException;
    String changeOffer(Contract contract);
    String acceptOffer(Contract contract);
    String rejectOffer(Contract contract);
}
