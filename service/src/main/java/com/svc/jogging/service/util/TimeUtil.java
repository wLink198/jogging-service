package com.svc.jogging.service.util;

import lombok.Data;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    public static String getDateOfTime(Instant time, String zoneId) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.of(zoneId));
        return formatter.format(time);
    }

    public static TimeOfDay getTimeOfDay(Instant time, String zoneId) {
        ZoneId zone = ZoneId.of(zoneId);
        ZonedDateTime localDateTime = ZonedDateTime.ofInstant(time, zone);
        ZonedDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        ZonedDateTime endOfDay = startOfDay.plusDays(1);

        TimeOfDay result = new TimeOfDay();
        result.setStart(startOfDay.toInstant());
        result.setEnd(endOfDay.toInstant());

        return result;
    }

    public static TimeOfDay getTimeOfDay(String date, String zoneId) {
        LocalDate localDate = LocalDate.parse(date);
        ZoneId zone = ZoneId.of(zoneId);
        ZonedDateTime startOfDay = localDate.atStartOfDay(zone);
        ZonedDateTime endOfDay = startOfDay.plusDays(1);

        TimeOfDay result = new TimeOfDay();
        result.setStart(startOfDay.toInstant());
        result.setEnd(endOfDay.toInstant());

        return result;
    }

    public static Instant getFirstDayOfWeek(Instant now, String zoneId) {
        ZonedDateTime nowWithZone = now.atZone(ZoneId.of(zoneId));
        ZonedDateTime firstDayOfWeek = nowWithZone.with(DayOfWeek.MONDAY).withHour(0).withMinute(0).withSecond(0).withNano(0);

        return firstDayOfWeek.toInstant();
    }


    public static Instant getFirstDayOfMonth(Instant now, String zoneId) {
        ZonedDateTime nowWithZone = now.atZone(ZoneId.of(zoneId));
        ZonedDateTime firstDayOfMonth = nowWithZone.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);

        return firstDayOfMonth.toInstant();
    }

    @Data
    public static class TimeOfDay {
        private Instant start;
        private Instant end;
    }
}
