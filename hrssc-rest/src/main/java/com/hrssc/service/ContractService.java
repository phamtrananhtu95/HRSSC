package com.hrssc.service;

import com.hrssc.entities.Contract;
import com.hrssc.entities.ContractVersion;
import com.hrssc.entities.Interaction;
import javassist.NotFoundException;

import java.util.List;

public interface ContractService {

    Interaction loadContract(int interactionId) throws NotFoundException;
    String changeOffer(Contract contract);
    String acceptOffer(Contract contract);
    String rejectOffer(int contractId);
    List<ContractVersion> getContractVersionByContractId(int contractId);
    String endContract(int jobId);
}
