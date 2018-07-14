package com.hrssc.service;

import com.hrssc.entities.Interaction;
import javassist.NotFoundException;

public interface ContractService {

    Interaction loadContract(int interactionId) throws NotFoundException;
}
