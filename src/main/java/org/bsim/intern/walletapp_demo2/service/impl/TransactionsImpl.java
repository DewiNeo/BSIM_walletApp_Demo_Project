package org.bsim.intern.walletapp_demo2.service.impl;

import org.bsim.intern.walletapp_demo2.io.entity.TransactionsEntity;
import org.bsim.intern.walletapp_demo2.io.entity.WalletsEntity;
import org.bsim.intern.walletapp_demo2.io.irepository.TransactionsRepository;
import org.bsim.intern.walletapp_demo2.io.irepository.WalletsRepository;
import org.bsim.intern.walletapp_demo2.service.iservice.IServiceTransactions;
import org.bsim.intern.walletapp_demo2.shared.dto.TransactionDTO;
import org.bsim.intern.walletapp_demo2.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsImpl implements IServiceTransactions {
    private final TransactionsRepository transactionsRepository;

    private final WalletsRepository walletsRepository;
    private final GenerateRandomPublicId generateRandomPublicId;

    public TransactionsImpl(TransactionsRepository transactionsRepository, WalletsRepository walletsRepository, GenerateRandomPublicId generateRandomPublicId) {
        this.transactionsRepository = transactionsRepository;
        this.walletsRepository = walletsRepository;
        this.generateRandomPublicId = generateRandomPublicId;
    }


    @Override
    public List<TransactionDTO> getAllTransactions() {
        //dari entitiy ke DTO
        ModelMapper modelMapper = new ModelMapper();

        //wadah untuk mapping dari entity ->ke DTO
        List<TransactionDTO> returnValue = new ArrayList<>();

        List<TransactionsEntity> transactionsEntities = transactionsRepository.findAll();

        //loop untun maukn 1 1 dri entitu ke dto
        for (TransactionsEntity entity: transactionsEntities){
            returnValue.add(modelMapper.map(entity, TransactionDTO.class));
        }

        return returnValue;
    }

    @Override
    public TransactionDTO addNewTransaction(String walletid, TransactionDTO transactionDTO) {
        ModelMapper modelMapper = new ModelMapper();
        TransactionsEntity entity = modelMapper.map(transactionDTO, TransactionsEntity.class);

        WalletsEntity walletsEntity = walletsRepository.findByWalletid(walletid);
        entity.setWalletsEntity(walletsEntity);
        entity.setTransactionId(generateRandomPublicId.generateUserId(35));

        TransactionsEntity storedValue = transactionsRepository.save(entity);

        TransactionDTO returnValue = modelMapper.map(storedValue, TransactionDTO.class);

        return returnValue;
    }

    @Override
    public List<TransactionDTO> getAllTransactionsByWalletid(String walletid) {
        List<TransactionDTO> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        WalletsEntity walletsEntity = walletsRepository.findByWalletid(walletid);

        List<TransactionsEntity> transactionsEntities = transactionsRepository.findAllByWalletsEntity(walletsEntity);

        for (TransactionsEntity entity: transactionsEntities){
            returnValue.add(modelMapper.map(entity, TransactionDTO.class));
        }
        return returnValue;
    }

    @Override
    public TransactionDTO updateTransactionByTransactionId(String walletid, String transactionsid, TransactionDTO transactionDTO) {
        ModelMapper modelMapper = new ModelMapper();

//        long balanceInit = 0;
//        long amountInit = 0;
//        long amountUpdate = transactionDTO.getAmount();


        WalletsEntity walletsEntity = walletsRepository.findByWalletid(transactionsid);
//        balanceInit = walletsEntity.getBalance();

        TransactionsEntity transactionsEntity = transactionsRepository.findByTransactionId(transactionsid);
//        amountInit = transactionsEntity.getAmount();

        TransactionsEntity entity = modelMapper.map(transactionDTO, TransactionsEntity.class);
        entity.setWalletsEntity(walletsEntity);
        entity.setId(transactionsEntity.getId());
        entity.setTransactionId(transactionsEntity.getTransactionId());

        TransactionsEntity storedValue = transactionsRepository.save(entity);

//        if (amountInit - amountUpdate > 0){
//            walletsEntity.setBalance(balanceInit + (amountUpdate -amountInit));
//        }else{
//            walletsEntity.setBalance(balanceInit - (amountInit-amountUpdate));
//        }

//        walletsRepository.save(walletsEntity);
//        TransactionsEntity = storedValue.w

        return modelMapper.map(storedValue, TransactionDTO.class);
    }

    @Override
    public TransactionDTO deleteTransactions(String walletid, String transactionid) {
        WalletsEntity walletsEntity = walletsRepository.findByWalletid(walletid);
        TransactionsEntity transactionsEntity = transactionsRepository.findByTransactionId(transactionid);
        transactionsEntity.setWalletsEntity(walletsEntity);
        transactionsEntity.setDeleted(true);

        TransactionsEntity storedValue = transactionsRepository.save(transactionsEntity);

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(storedValue, TransactionDTO.class);
    }
}
