package com.gamehub2.gamehub.entities.SystemReq;

import jakarta.persistence.*;

@Entity
public class Processor {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long processorId;
    @Basic
    private String processorName;
    @Basic
    private double performance;

    public Long getProcessorId() {
        return processorId;
    }

    public void setProcessorId(Long processorId) {
        this.processorId = processorId;
    }

    public String getProcessorName() {
        return processorName;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }
}
