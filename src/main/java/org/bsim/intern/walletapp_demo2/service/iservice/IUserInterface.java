package org.bsim.intern.walletapp_demo2.service.iservice;

import org.bsim.intern.walletapp_demo2.shared.dto.UserDTO;

import java.util.List;

public interface IUserInterface {
    //get all user
    List<UserDTO> getListUser();

    //get single value by username
    UserDTO addNewData(UserDTO userDTO);

    //get user by username
    UserDTO getUserByUsername(String username);

}
