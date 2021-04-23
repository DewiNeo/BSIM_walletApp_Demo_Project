package org.bsim.intern.walletapp_demo2.io.irepository;

import org.bsim.intern.walletapp_demo2.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByUserName(String username);
}
