package org.bsim.intern.walletapp_demo2.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userTBL")
@SequenceGenerator(name = "seqUSR", initialValue = 100, allocationSize = 10)
public class UserEntity implements Serializable {
    private static final long serialVersionUID= -4669714256020060248L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUSR")
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(unique = false, columnDefinition = "VARCHAR(50", length = 50)
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalletsEntity> walletsentity = new ArrayList<>();

    public List<WalletsEntity> getWalletsentity() {
        return walletsentity;
    }

    public void setWalletsentity(List<WalletsEntity> walletsentity) {
        this.walletsentity = walletsentity;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
