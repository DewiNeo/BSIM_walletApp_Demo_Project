package org.bsim.intern.walletapp_demo2.service.impl;

import org.bsim.intern.walletapp_demo2.io.entity.UserEntity;
import org.bsim.intern.walletapp_demo2.io.irepository.UserRepository;
import org.bsim.intern.walletapp_demo2.service.iservice.IUserInterface;
import org.bsim.intern.walletapp_demo2.shared.dto.UserDTO;
import org.bsim.intern.walletapp_demo2.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GenerateRandomPublicId generateRandomPublicId;


    @Override
    public List<UserDTO> getListUser() {
        List<UserDTO> value = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        List<UserEntity> users= userRepository.findAll();

        for (UserEntity userEntity : users){
            value.add(mapper.map(userEntity, UserDTO.class));
        }
        return value;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        UserEntity getUser = userRepository.findByUserName(username);
        if (getUser == null)
            return null;
        //else
        return new ModelMapper().map(getUser, UserDTO.class);
    }

    @Override
    public UserDTO addNewData(UserDTO user) {
        user.setUserId(generateRandomPublicId.generateUserId(30));//generate user id
        ModelMapper mapper = new ModelMapper();
        //UserDTO --> UserEntity
        UserEntity entity = mapper.map(user, UserEntity.class);
        UserEntity storedData = userRepository.save(entity);
        //UserEntity --> UserDTO (return value)
        UserDTO value = mapper.map(storedData, UserDTO.class);
        return value;
    }



}
