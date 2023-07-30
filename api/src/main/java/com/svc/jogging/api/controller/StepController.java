package com.svc.jogging.api.controller;

import com.svc.jogging.model.constant.ZoneIds;
import com.svc.jogging.model.req.StepCountReq;
import com.svc.jogging.model.res.ResponseData;
import com.svc.jogging.service.facade.StepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "steps")
public class StepController {

    private final StepService stepService;

    @PostMapping("")
    public ResponseData recordUserStepCountToday(
            @RequestHeader String userId,
            @RequestParam(required = false, defaultValue = ZoneIds.DEFAULT_ZONE_ID) String zoneId,
            @Valid @RequestBody StepCountReq req) {
        req.setUserId(userId);
        req.setZoneId(zoneId);
        return ResponseData.builder().data(stepService.recordUserStepCountToday(req)).build();
    }

    @GetMapping("/top/{limit}")
    public ResponseData findTopUsersByDate(
            @PathVariable int limit,
            @RequestParam(required = false) String date,
            @RequestParam(required = false, defaultValue = ZoneIds.DEFAULT_ZONE_ID) String zoneId) {
        return ResponseData.builder().data(stepService.findTopUsersByDate(limit, date, zoneId)).build();
    }

    @GetMapping("/count/week")
    public ResponseData countStepsByUserCurrentWeek(
            @RequestHeader String userId,
            @RequestParam(required = false, defaultValue = ZoneIds.DEFAULT_ZONE_ID) String zoneId) {
        return ResponseData.builder().data(stepService.countStepsByUserCurrentWeek(userId, zoneId)).build();
    }

    @GetMapping("/count/month")
    public ResponseData countStepsByUserCurrentMonth(
            @RequestHeader String userId,
            @RequestParam(required = false, defaultValue = ZoneIds.DEFAULT_ZONE_ID) String zoneId) {
        return ResponseData.builder().data(stepService.countStepsByUserCurrentMonth(userId, zoneId)).build();
    }
}
