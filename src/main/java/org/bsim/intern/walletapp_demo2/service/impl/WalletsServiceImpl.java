package org.bsim.intern.walletapp_demo2.service.impl;

import org.bsim.intern.walletapp_demo2.io.entity.UserEntity;
import org.bsim.intern.walletapp_demo2.io.entity.WalletsEntity;
import org.bsim.intern.walletapp_demo2.io.irepository.UserRepository;
import org.bsim.intern.walletapp_demo2.io.irepository.WalletsRepository;
import org.bsim.intern.walletapp_demo2.service.iservice.IWalletsService;
import org.bsim.intern.walletapp_demo2.shared.dto.UserDTO;
import org.bsim.intern.walletapp_demo2.shared.dto.WalletsDTO;
import org.bsim.intern.walletapp_demo2.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletsServiceImpl implements IWalletsService {

    @Autowired
    GenerateRandomPublicId generateRandomPublicId;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WalletsRepository walletsRepository;

    @Override
    public WalletsDTO addNewWalletData(String userid, WalletsDTO walletsDTO) {
        ModelMapper mapper = new ModelMapper();

        //Generate WalletID
        walletsDTO.setWalletId(generateRandomPublicId.generateUserId(30));

        //getUser
        UserEntity userData = userRepository.findByUserId(userid);

        //SetUser
        walletsDTO.setUser(mapper.map(userData, UserDTO.class));

        WalletsEntity entity = mapper.map(walletsDTO, WalletsEntity.class);

        //save data to database (table : walletTBL)
        WalletsEntity storedData = walletsRepository.save(entity);

        return mapper.map(storedData, WalletsDTO.class);
    }

    @Override
    public List<WalletsDTO> getAllWalletsData(String userid) {
        ModelMapper mapper = new ModelMapper();

        List<WalletsDTO> value = new ArrayList<>();

        //Get User
        UserEntity userEntity = userRepository.findByUserId(userid);

        List<WalletsEntity> walletsData = walletsRepository.findAllByUser(userEntity);

        for (WalletsEntity walletsEntity : walletsData){
            value.add(mapper.map(walletsEntity, WalletsDTO.class));
        }
        return value;
    }

    @Override
    public long getTotalBalance(String userid) {
        List<WalletsEntity> walletsData = walletsRepository.findAllByUser(userRepository.findByUserId(userid));
        if (walletsData == null)
            return 0L;
        long totalBalance=0;
        for (WalletsEntity walletsEntity:walletsData){
            totalBalance += walletsEntity.getBalance();
        }

        return totalBalance;
    }

    @Override
    public WalletsDTO updateWalletData(String userid, String walletid, WalletsDTO walletsDTO) {
        WalletsEntity walletData = walletsRepository.findByWalletid(walletid);
        if (walletData==null)
            return null;
        //update nomorhp / balance
        walletData.setNohp(walletsDTO.getNoHP());
        walletData.setBalance(walletsDTO.getBalance());

        WalletsEntity updateData = walletsRepository.save(walletData);

        return new ModelMapper().map(updateData, WalletsDTO.class);

    }
}
