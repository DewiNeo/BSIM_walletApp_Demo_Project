package org.bsim.intern.walletapp_demo2.io.irepository;

import org.bsim.intern.walletapp_demo2.io.entity.TransactionsEntity;
import org.bsim.intern.walletapp_demo2.io.entity.WalletsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Long> {
    List<TransactionsEntity> findAllByWalletsEntity(WalletsEntity walletsEntity);

    TransactionsEntity findByTransactionId(String transactionsid);
//    List<TransactionsEntity> findAllByWalletEntity(WalletsEntity walletsEntity);
}
