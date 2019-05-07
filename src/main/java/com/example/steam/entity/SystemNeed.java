package com.example.steam.entity;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-06
 * @time: 22:32
 */
@Component
public class SystemNeed {

    private Long id;

    private String operatingSystem;

    private String cpu;

    private String ram;

    private String graphicsCard;

    private String directx;

    private String network;

    private String rom;

    private String soundCard;

    public SystemNeed(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public String getDirectx() {
        return directx;
    }

    public void setDirectx(String directx) {
        this.directx = directx;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getSoundCard() {
        return soundCard;
    }

    public void setSoundCard(String soundCard) {
        this.soundCard = soundCard;
    }
}
