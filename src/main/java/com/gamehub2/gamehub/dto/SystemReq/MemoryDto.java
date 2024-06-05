package com.gamehub2.gamehub.dto.SystemReq;

import com.gamehub2.gamehub.entities.SystemReq.Memory;

public class MemoryDto {
    private Long memoryId;
    private int memory;

    public MemoryDto() {
    }
    public MemoryDto(Memory memory){
        this.memoryId=memory.getMemoryId();
        this.memory=memory.getMemory();
    }
    public MemoryDto(Long memoryId, int memory) {
        this.memoryId = memoryId;
        this.memory = memory;
    }

    public Long getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(Long memoryId) {
        this.memoryId = memoryId;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
