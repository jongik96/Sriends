package com.project.autonomous.team.controller;

import com.project.autonomous.team.dto.request.PostingReq;
import com.project.autonomous.team.dto.request.SchedulePostReq;
import com.project.autonomous.team.dto.response.CalendarRes;
import com.project.autonomous.team.entity.Calendar;
import com.project.autonomous.team.service.TeamCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/teams/calendar")
public class TeamCalendarController {

    @Autowired
    TeamCalendarService teamCalendarService;

    @PostMapping("/{teamId}")
    public ResponseEntity<Calendar> createCalendar(@PathVariable("teamId") long teamId, @RequestBody SchedulePostReq schedulePostReq){
        System.out.println("일정 등록");

        return ResponseEntity.ok(teamCalendarService.create(schedulePostReq, teamId));
    }

    @PutMapping("/{calendarId}")
    public ResponseEntity<Boolean> modifyCalendar(@PathVariable("calendarId") long calendarId, @RequestBody SchedulePostReq schedulePostReq){
        System.out.println("일정 수정");

        return ResponseEntity.ok(teamCalendarService.modify(schedulePostReq, calendarId));
    }

    @DeleteMapping("/{calendarId}")
    public ResponseEntity<Boolean> deleteCalendar(@PathVariable("calendarId") long calendarId){
        System.out.println("일정 삭제");

        return ResponseEntity.ok(teamCalendarService.delete(calendarId));
    }

    @GetMapping("/{calendarId}")
    public ResponseEntity<CalendarRes> getCalendar(@PathVariable("calendarId") long calendarId){
        System.out.println("일정 조회");

        return ResponseEntity.ok(teamCalendarService.getSchedule(calendarId));
    }

    @GetMapping("/{teamId}/{year}/{month}")
    public ResponseEntity<ArrayList<CalendarRes>> getCalendarList(@PathVariable("teamId") long teamId, @PathVariable("year") int year, @PathVariable("month") int month){
        System.out.println("일정 월별 조회");

        return ResponseEntity.ok(teamCalendarService.getScheduleList(teamId, year, month));
    }
}
