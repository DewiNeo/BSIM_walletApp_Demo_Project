package org.bsim.intern.walletapp_demo2.ui.controller;

import org.bsim.intern.walletapp_demo2.service.iservice.IWalletsService;
import org.bsim.intern.walletapp_demo2.shared.dto.WalletsDTO;
import org.bsim.intern.walletapp_demo2.ui.model.request.WalletRequest;
import org.bsim.intern.walletapp_demo2.ui.model.response.WalletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wallets")
public class WalletsController {

    @Autowired
    IWalletsService walletsService;

    //ad new data
    @PostMapping(path = "/{userid}",
                consumes = {MediaType.APPLICATION_JSON_VALUE},
                produces = {MediaType.APPLICATION_JSON_VALUE})
    public WalletResponse AddNewWallet(@PathVariable String userid, @RequestBody WalletRequest walletRequest){
        ModelMapper mapper = new ModelMapper();

        WalletsDTO walletsDTO = mapper.map(walletRequest, WalletsDTO.class);
        WalletsDTO createWallet = walletsService.addNewWalletData(userid, walletsDTO);
        return mapper.map(createWallet, WalletResponse.class);
    }
    @GetMapping(path = "/{userId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<WalletResponse> getAllWallet(@PathVariable String userId){
        List<WalletResponse> value = new ArrayList<>();

        ModelMapper mapper = new ModelMapper();
        List<WalletsDTO> walletsData = walletsService.getAllWalletsData(userId);

        for (WalletsDTO walletsDTO : walletsData){
            value.add(mapper.map(walletsDTO, WalletResponse.class));
        }
        return value;
    }

    @GetMapping(path = "/{userid}/balance", produces = {MediaType.APPLICATION_JSON_VALUE})
    public long getTotalBalance(@PathVariable String userid){
        long totalBalance = walletsService.getTotalBalance(userid);
        return totalBalance;
    }

    //
    @PutMapping(path = "/{userid}/{walletid}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public WalletResponse updateWalletAmount(@PathVariable String userid,
                                             @PathVariable String walletid,
                                             @RequestBody WalletRequest walletRequest){
        ModelMapper mapper = new ModelMapper();
        // mau kirim dari wallet request -> WalletDTO
        WalletsDTO walletsDTO = mapper.map(walletRequest, WalletsDTO.class);
        WalletsDTO updateWallet = walletsService.updateWalletData(userid, walletid,walletsDTO);
//        WalletsDTO updateWallet = walletsService.updateWalletData(mapper.map(walletRequest, WalletsDTO.class); //bleh pake cra ini

        return mapper.map(updateWallet, WalletResponse.class);
    }
}
