package com.krayc.vo;

public class MemberInfoVO {

    private int mid;
    private String email;
    private String bankAccount;
    private String level;
    private String isTerminated;
    private String isEmailPassed;

    public MemberInfoVO(int mid, String email, String bankAccount, String level, String isTerminated, String isEmailPassed) {
        this.mid = mid;
        this.email = email;
        this.bankAccount = bankAccount;
        this.level = level;
        this.isTerminated = isTerminated;
        this.isEmailPassed = isEmailPassed;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIsTerminated() {
        return isTerminated;
    }

    public void setIsTerminated(String isTerminated) {
        this.isTerminated = isTerminated;
    }

    public String getIsEmailPassed() {
        return isEmailPassed;
    }

    public void setIsEmailPassed(String isEmailPassed) {
        this.isEmailPassed = isEmailPassed;
    }
}
