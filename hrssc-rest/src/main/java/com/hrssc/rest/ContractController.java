package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.dto.ResponseStatus;
import com.hrssc.domain.jacksonview.ApplianceView;
import com.hrssc.domain.jacksonview.ContractView;
import com.hrssc.entities.*;
import com.hrssc.service.AuthorizationService;
import com.hrssc.service.ChatService;
import com.hrssc.service.ContractService;
import javassist.NotFoundException;
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

    @Autowired
    AuthorizationService authorizationService;

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

    @GetMapping("/reject-offer/{contractId}")
    public ResponseStatus rejectOffer(@PathVariable(value = "contractId") int contractId){
        ResponseStatus response = new ResponseStatus(contractService.rejectOffer(contractId));
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

    @GetMapping("/cancel-contract/{jobId}/{userId}")
    public boolean cancelContract(@PathVariable(value = "jobId") int jobId,
                                 @PathVariable(value = "userId") int userId){
        if(!authorizationService.checkRoleReject(jobId, userId)){
            return false;
        }
        return contractService.endContract(jobId, userId);
    }

    @JsonView(ContractView.overview.class)
    @GetMapping("/load-all-resource-contract/{userId}")
    public List<Job> loadResourceContract(@PathVariable(value = "userId") int userId){
        return contractService.loadAllContractResource(userId);
    }

    @JsonView(ContractView.overview.class)
    @GetMapping("/load-all-project-contract/{userId}")
    public List<Job> loadProjectContract(@PathVariable(value = "userId") int userId){
        return contractService.loadAllContractProject(userId);
    }

    @JsonView(ContractView.detail.class)
    @GetMapping("/get-job-contract/{jobId}")
    public Job loadJobContract(@PathVariable(value = "jobId") int jobId) throws NotFoundException {
        return contractService.loadJobContract(jobId);
    }
}
