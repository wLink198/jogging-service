package com.svc.jogging.api.controller;

import com.svc.jogging.model.constant.ZoneIds;
import com.svc.jogging.model.dto.StepDto;
import com.svc.jogging.model.req.StepCountReq;
import com.svc.jogging.model.res.ResponseData;
import com.svc.jogging.service.facade.StepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "steps")
public class StepController {

    private final StepService stepService;

    @Operation(summary = "API ghi nhận số bước chân trong ngày hiện tại từ phía ứng dụng")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trả về StepDto với id của entity mới được tạo",
                    content = @Content(schema = @Schema(implementation = StepDto.class))),
            @ApiResponse(responseCode = "400", description = "Trả về message lỗi với tùy từng trường hợp:<br>" +
                    "Can not record new steps which are less than previous steps<br>" +
                    "Steps can not be greater than 0 for the first time recorded<br>" +
                    "Steps cannot be negative")
    })
    @PostMapping("")
    public ResponseData recordUserStepCountToday(
            @RequestHeader String userId,
            @RequestParam(required = false, defaultValue = ZoneIds.DEFAULT_ZONE_ID) String zoneId,
            @Valid @RequestBody StepCountReq req) {
        req.setUserId(userId);
        req.setZoneId(zoneId);
        return ResponseData.builder().data(stepService.recordUserStepCountToday(req)).build();
    }

    @Operation(summary = "API lấy bảng xếp hạng để hiển thị ở ứng dụng")
    @GetMapping("/top/{limit}")
    public ResponseData findTopUsersByDate(
            @Parameter(description = "xếp hạng top người đi bộ theo limit", schema = @Schema(type = "integer"))
            @PathVariable int limit,
            @Parameter(description = "xếp hạng theo ngày, định dạng yyyy-MM-dd", schema = @Schema(type = "string"))
            @RequestParam(required = false) String date,
            @RequestParam(required = false, defaultValue = ZoneIds.DEFAULT_ZONE_ID) String zoneId) {
        return ResponseData.builder().data(stepService.findTopUsersByDate(limit, date, zoneId)).build();
    }

    @Operation(summary = "API lấy tổng số bước chân của người dùng theo tuần hiện tại")
    @GetMapping("/count/week")
    public ResponseData countStepsByUserCurrentWeek(
            @RequestHeader String userId,
            @RequestParam(required = false, defaultValue = ZoneIds.DEFAULT_ZONE_ID) String zoneId) {
        return ResponseData.builder().data(stepService.countStepsByUserCurrentWeek(userId, zoneId)).build();
    }

    @Operation(summary = "API lấy tổng số bước chân của người dùng theo tháng hiện tại")
    @GetMapping("/count/month")
    public ResponseData countStepsByUserCurrentMonth(
            @RequestHeader String userId,
            @RequestParam(required = false, defaultValue = ZoneIds.DEFAULT_ZONE_ID) String zoneId) {
        return ResponseData.builder().data(stepService.countStepsByUserCurrentMonth(userId, zoneId)).build();
    }
}
