package com.orcl.hashcode.hash;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-23 10:54
 * @history: 2022-07-23 10:54 created by orcl
 */
public class RateInfo {

    /**
     * 最大 hash
     */
    private int maxHash;

    /**
     * 最小 hash
     */
    private int minHash;

    /**
     * hash 计算乘积因子
     */
    private int multiplier;

    /**
     * 碰撞数
     */
    private int collisionCount;

    /**
     * 碰撞比率
     */
    private double collisionRate;

    public RateInfo() {
    }

    public RateInfo(int maxHash, int minHash, int multiplier, int collisionCount, double collisionRate) {
        this.maxHash = maxHash;
        this.minHash = minHash;
        this.multiplier = multiplier;
        this.collisionCount = collisionCount;
        this.collisionRate = collisionRate;
    }

    public int getMaxHash() {
        return maxHash;
    }

    public void setMaxHash(int maxHash) {
        this.maxHash = maxHash;
    }

    public int getMinHash() {
        return minHash;
    }

    public void setMinHash(int minHash) {
        this.minHash = minHash;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getCollisionCount() {
        return collisionCount;
    }

    public void setCollisionCount(int collisionCount) {
        this.collisionCount = collisionCount;
    }

    public double getCollisionRate() {
        return collisionRate;
    }

    public void setCollisionRate(double collisionRate) {
        this.collisionRate = collisionRate;
    }
}
