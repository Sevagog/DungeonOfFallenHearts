package com.example.mygame;

public class Hero {
    private byte selectedHero = -1;
    final private short[] weaponX = {740, 740, 740}, weaponXReverted = {860, 860, 860}, weaponY = {410, 410, 410};
    private short getWeaponX, getWeaponXReverted, getWeaponY;
    final private short[] headX = {740, 790, 790}, headXReverted = {870, 880, 880}, headY = {412, 402, 402};
    private short getHeadX, getHeadXReverted, getHeadY;

    public void setSelectedHero(byte selectedHero) {
        this.selectedHero = selectedHero;
        if(selectedHero != -1){
            getWeaponX = weaponX[selectedHero];
            getWeaponXReverted = weaponXReverted[selectedHero];
            getWeaponY = weaponY[selectedHero];
            getHeadX = headX[selectedHero];
            getHeadXReverted = headXReverted[selectedHero];
            getHeadY = headY[selectedHero];
        }
    }

    public byte getSelectedHero() {
        return selectedHero;
    }

    public short getWeaponX() {
        return getWeaponX;
    }

    public short getWeaponXReverted() {
        return getWeaponXReverted;
    }

    public short getWeaponY() {
        return getWeaponY;
    }

    public short getHeadX() {
        return getHeadX;
    }

    public short getHeadXReverted() {
        return getHeadXReverted;
    }

    public short getHeadY() {
        return getHeadY;
    }

    public short getBodyY() {
        return 440;
    }

    public short getFootY() {
        return 537;
    }
}
