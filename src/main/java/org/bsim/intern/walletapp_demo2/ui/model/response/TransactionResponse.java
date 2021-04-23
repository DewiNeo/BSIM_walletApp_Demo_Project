package org.bsim.intern.walletapp_demo2.ui.model.response;

import org.bsim.intern.walletapp_demo2.shared.dto.WalletsDTO;

import java.time.LocalDateTime;

public class TransactionResponse {
    private String name;
    private long amount;
    private LocalDateTime tanggal;
    private String note;
    private boolean isDeleted;
    private WalletsDTO walletsDTO;
    private String transactionid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDateTime getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDateTime tanggal) {
        this.tanggal = tanggal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public WalletsDTO getWalletsDTO() {
        return walletsDTO;
    }

    public void setWalletsDTO(WalletsDTO walletsDTO) {
        this.walletsDTO = walletsDTO;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }
}
