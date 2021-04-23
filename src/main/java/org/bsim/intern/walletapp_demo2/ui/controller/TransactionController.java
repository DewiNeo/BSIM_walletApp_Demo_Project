package org.bsim.intern.walletapp_demo2.ui.controller;


import org.bsim.intern.walletapp_demo2.service.iservice.IServiceTransactions;
import org.bsim.intern.walletapp_demo2.shared.dto.TransactionDTO;
import org.bsim.intern.walletapp_demo2.ui.model.request.TransactionRequest;
import org.bsim.intern.walletapp_demo2.ui.model.response.TransactionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final IServiceTransactions iServiceTransactions;

    public TransactionController(IServiceTransactions iServiceTransactions) {
        this.iServiceTransactions = iServiceTransactions;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TransactionResponse> getAllTransactions(){
        ModelMapper modelMapper = new ModelMapper();
        List<TransactionResponse> returnValue = new ArrayList<>();

        List<TransactionDTO> transactionDTO = iServiceTransactions.getAllTransactions();

        for (TransactionDTO dto : transactionDTO){
            returnValue.add(modelMapper.map(dto, TransactionResponse.class));
        }
        return returnValue;
    }


    @PostMapping(path = "/{walletid}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public TransactionResponse addNewTransaction(@RequestBody TransactionRequest transactionRequest,
                                                 @PathVariable String walletid){
        ModelMapper modelMapper = new ModelMapper();

        TransactionDTO transactionDTO = modelMapper.map(transactionRequest, TransactionDTO.class);

        TransactionDTO storedValue = iServiceTransactions.addNewTransaction(walletid, transactionDTO);

        TransactionResponse returnValue = modelMapper.map(storedValue, TransactionResponse.class);


        return returnValue;
    }

    @GetMapping(path = "/{walletid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TransactionResponse> getAllTransactionWalletId(@PathVariable String walletid){   //awalan namanya selalu huruf kecil
        //path itu yg di address jadi kaya id gtuu
        ModelMapper modelMapper = new ModelMapper();
        List<TransactionResponse>  returnValue = new ArrayList<>();
        List<TransactionDTO> allTrasancation= iServiceTransactions.getAllTransactionsByWalletid(walletid);
        for (TransactionDTO dto : allTrasancation){
            returnValue.add(modelMapper.map(dto, TransactionResponse.class));
        }
        return returnValue;
    }

    //g pake consume krena
    @PutMapping(path = "/{walletid}/{transactionsid}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public TransactionResponse updateTransactionsByTransactionId(@PathVariable String walletid,
                                                                 @PathVariable String transactionsid,
                                                                 @RequestBody TransactionRequest transactionRequest) {
        //katanya yg reuest body itu kek mo post ato put gtu kan kita mau isi bodynya itu kegunaan request body
        ModelMapper modelMapper = new ModelMapper();
        TransactionDTO transactionDTO = modelMapper.map(transactionRequest, TransactionDTO.class);
        TransactionDTO updatedData = iServiceTransactions.updateTransactionByTransactionId(walletid, transactionsid, transactionDTO);

        return modelMapper.map(updatedData, TransactionResponse.class);
    }

    @DeleteMapping(path = "/{walletid}/{transactionid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public  TransactionResponse deleteTransaction(@PathVariable String walletid,
                                                  @PathVariable String transactionid){
        ModelMapper modelMapper = new ModelMapper();
        TransactionDTO transactionDTO = iServiceTransactions.deleteTransactions(walletid, transactionid);
        return  modelMapper.map(transactionDTO, TransactionResponse.class);
    }

}
