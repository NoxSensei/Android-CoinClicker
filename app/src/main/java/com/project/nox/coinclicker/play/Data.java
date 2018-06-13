package com.project.nox.coinclicker.play;


import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class Data implements Serializable{

    private AtomicLong coinsValue;

    private AtomicLong seriousClickNum;

    private AtomicLong greedyPiggyNum;

    private AtomicLong littleTommyNum;

    private AtomicLong businessPackNum;

    private AtomicLong slyMarioNum;

    private AtomicLong maxCoinsValue;

    public Data()
    {
        coinsValue=new AtomicLong(0);
        seriousClickNum = new AtomicLong(1);
        greedyPiggyNum=new AtomicLong(0);
        littleTommyNum =new AtomicLong(0);
        businessPackNum =new AtomicLong(0);
        slyMarioNum=new AtomicLong(0);
        maxCoinsValue=new AtomicLong(1000000000);
    }

    public long getCoinsValue() {
        return coinsValue.get();
    }

    public void setCoinsValue(long coinsValue) {
        this.coinsValue.set(coinsValue);
    }

    public long getSeriousClickNum() {
        return seriousClickNum.get();
    }

    public void setSeriousClickNum(long seriousClickNum) {
        this.seriousClickNum.set(seriousClickNum);
    }

    public long getGreedyPiggyNum() {
        return greedyPiggyNum.get();
    }

    public void setGreedyPiggyNum(long greedyPiggyNum) {
        this.greedyPiggyNum.set(greedyPiggyNum);
    }

    public long getLittleTommyNum() {
        return littleTommyNum.get();
    }

    public void setLittleTommyNum(long littleTommyNum) {
        this.littleTommyNum.set(littleTommyNum);
    }

    public long getBusinessPackNum() {
        return businessPackNum.get();
    }

    public void setBusinessPackNum(long businessPackNum) {
        this.businessPackNum.set(businessPackNum);
    }

    public long getSlyMarioNum() {
        return slyMarioNum.get();
    }

    public void setSlyMarioNum(long slyMarioNum) {
        this.slyMarioNum.set(slyMarioNum);
    }

    public long getMaxCoinsValue() {
        return maxCoinsValue.get();
    }

    public void setMaxCoinsValue(long maxCoinsValue) {
        this.maxCoinsValue.set(maxCoinsValue);
    }
}
