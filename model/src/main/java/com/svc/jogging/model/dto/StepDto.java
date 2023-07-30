package com.svc.jogging.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class StepDto {
    @JsonProperty("id")
    private String _id;
    private String userId;
    private int steps;
    private String fromLocation;
    private String toLocation;
    private String recordedDate;
    private String zoneId;

    private Instant startedAt;
    private Instant endedAt;
}
