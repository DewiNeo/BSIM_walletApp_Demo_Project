package org.bsim.intern.walletapp_demo2.service.iservice;

import org.bsim.intern.walletapp_demo2.shared.dto.WalletsDTO;

import java.util.List;

public interface IWalletsService {
    WalletsDTO addNewWalletData (String userid, WalletsDTO walletsDTO);

    //    List<WalletsDTO> getListWallet (String userid);
    List<WalletsDTO> getAllWalletsData (String userid);

//    WalletsBalanceDTO getTotalBalance (String userid);

    //?
    long getTotalBalance(String userid);

    //untuk userid/wallet id
    WalletsDTO updateWalletData(String userid,String walletid, WalletsDTO walletsDTO);

}
