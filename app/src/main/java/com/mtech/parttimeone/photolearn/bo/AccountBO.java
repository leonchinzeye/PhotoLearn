package com.mtech.parttimeone.photolearn.bo;

import java.math.BigInteger;

/**
 * @author Leon
 * @date 3/12/18
 */

public class AccountBO {

    private String uid;
    private BigInteger accountNumber;
    private String accountName;
    private TrainerBO trainerBO;
    private ParticipantBO participantBO;

    public BigInteger getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(BigInteger accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public TrainerBO getTrainerBO() {
        return trainerBO;
    }

    public void setTrainerBO(TrainerBO trainerBO) {
        this.trainerBO = trainerBO;
    }

    public ParticipantBO getParticipantBO() {
        return participantBO;
    }

    public void setParticipantBO(ParticipantBO participantBO) {
        this.participantBO = participantBO;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
