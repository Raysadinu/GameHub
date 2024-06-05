package com.gamehub2.gamehub.dto.SystemReq;

import com.gamehub2.gamehub.entities.SystemReq.Processor;

public class ProcessorDto {
    private Long processorId;
    private String processorName;
    private double performance;

    public ProcessorDto(Processor processor){
        this.processorId=processor.getProcessorId();
        this.processorName=processor.getProcessorName();
        this.performance=processor.getPerformance();
    }

    public ProcessorDto() {
    }

    public ProcessorDto(Long processorId, String processorName, double performance) {
        this.processorId = processorId;
        this.processorName = processorName;
        this.performance = performance;
    }


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
