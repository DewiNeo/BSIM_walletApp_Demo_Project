package org.bsim.intern.walletapp_demo2.service.iservice;

import org.bsim.intern.walletapp_demo2.shared.dto.TransactionDTO;

import java.util.List;

public interface IServiceTransactions {
    List<TransactionDTO> getAllTransactions();



    TransactionDTO addNewTransaction(String walletid, TransactionDTO transactionDTO);

    List<TransactionDTO> getAllTransactionsByWalletid(String walletid);

    TransactionDTO updateTransactionByTransactionId(String walletid, String transactionsid, TransactionDTO transactionDTO);

    TransactionDTO deleteTransactions(String walletid, String transactionid);

}
