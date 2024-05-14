package com.gamehub2.gamehub.common.SystemReq;

import com.gamehub2.gamehub.entities.SystemReq.Memory;
import com.gamehub2.gamehub.entities.SystemReq.Platform;

public class PlatformDto {
    private Long platformId;
    private String platformName;

    public PlatformDto(Platform platform){
        this.platformId=platform.getPlatformId();
        this.platformName=platform.getPlatformName();
    }
    public PlatformDto() {
    }

    public PlatformDto(Long platformId, String platformName) {
        this.platformId = platformId;
        this.platformName = platformName;
    }


    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
}
