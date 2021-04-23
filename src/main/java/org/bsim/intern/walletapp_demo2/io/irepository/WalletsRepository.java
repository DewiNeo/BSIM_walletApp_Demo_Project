package org.bsim.intern.walletapp_demo2.io.irepository;

import org.bsim.intern.walletapp_demo2.io.entity.UserEntity;
import org.bsim.intern.walletapp_demo2.io.entity.WalletsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletsRepository extends JpaRepository<WalletsEntity, Long> {
    List<WalletsEntity> findAllByUser(UserEntity userEntity);
    WalletsEntity findByWalletid(String walletid);
}
