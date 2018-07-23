package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.dto.ResponseStatus;
import com.hrssc.domain.jacksonview.ApplianceView;
import com.hrssc.domain.jacksonview.ContractView;
import com.hrssc.entities.ChatLog;
import com.hrssc.entities.Contract;
import com.hrssc.entities.ContractVersion;
import com.hrssc.entities.Interaction;
import com.hrssc.service.ChatService;
import com.hrssc.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {



    @Autowired
    ContractService contractService;

    @Autowired
    ChatService chatService;

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

    @JsonView(ContractView.ChatLog.class)
    @GetMapping("/get-chat-logs/{contractId}")
    public List<ChatLog> getChatLogByContractId(@PathVariable("contractId") int contractId){
        return chatService.getMessageListByContractId(contractId);
    }

    @JsonView(ContractView.VersionView.class)
    @GetMapping("/get-version/{contractId}")
    public List<ContractVersion> getContractVersionBycontracId(@PathVariable(value = "contractId") int contractId){
        return contractService.getContractVersionByContractId(contractId);
    }

}
