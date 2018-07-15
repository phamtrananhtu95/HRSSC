package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.dto.ResponseStatus;
import com.hrssc.domain.jacksonview.ApplianceView;
import com.hrssc.entities.Contract;
import com.hrssc.entities.Interaction;
import com.hrssc.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract")
public class ContractController {



    @Autowired
    ContractService contractService;


    @JsonView(ApplianceView.ContractView.class)
    @GetMapping("/get-contract/{interactionId}")
    public Interaction loadContract(@PathVariable("interactionId") int interactionId) throws Exception{
        return contractService.loadContract(interactionId);
    }

    @PostMapping("/change-offer/")
    public ResponseStatus changeOffer(@RequestBody Contract contract){
        ResponseStatus response = new ResponseStatus(contractService.changeOffer(contract));
        return response;
    }

    @PostMapping("/accept-offer/")
    public ResponseStatus acceptOffer(@RequestBody Contract contract){
        ResponseStatus response = new ResponseStatus(contractService.acceptOffer(contract));
        return response;
    }

    @PostMapping("/reject-offer/")
    public ResponseStatus rejectOffer(@RequestBody Contract contract){
        ResponseStatus response = new ResponseStatus(contractService.rejectOffer(contract));
        return response;
    }

}
