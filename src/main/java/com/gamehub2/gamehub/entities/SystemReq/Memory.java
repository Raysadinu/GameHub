package com.gamehub2.gamehub.entities.SystemReq;

import jakarta.persistence.*;

@Entity
public class Memory {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long memoryId;
    @Basic
    private int memory;

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
