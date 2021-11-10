package com.project.autonomous.team.service;

import com.project.autonomous.common.exception.CustomException;
import com.project.autonomous.common.exception.ErrorCode;
import com.project.autonomous.jwt.util.SecurityUtil;
import com.project.autonomous.team.dto.request.SchedulePostReq;
import com.project.autonomous.team.dto.response.CalendarRes;
import com.project.autonomous.team.entity.Calendar;
import com.project.autonomous.team.repository.CalendarRepository;
import com.project.autonomous.user.dto.response.UserSimpleInfoRes;
import com.project.autonomous.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeamCalendarService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CalendarRepository calendarRepository;


    public Calendar create(SchedulePostReq schedulePostReq, long teamId) {
        long userId = SecurityUtil.getCurrentMemberId();

        Calendar calendar = new Calendar();
        calendar.setWriterId(userId);
        calendar.setTeamId(teamId);
        calendar.setName(schedulePostReq.getName());
        calendar.setContent(schedulePostReq.getContent());
        calendar.setSchedule(schedulePostReq.getSchedule());
        calendar.setYear(schedulePostReq.getSchedule().getYear());
        calendar.setMonth(schedulePostReq.getSchedule().getMonthValue());
        calendar.setDay(schedulePostReq.getSchedule().getDayOfMonth());

        return calendarRepository.save(calendar);
    }

    public Calendar modify(SchedulePostReq schedulePostReq, long calendarId) {
        long userId = SecurityUtil.getCurrentMemberId();

        Calendar calendar = calendarRepository.findById(calendarId).get();

        if(calendar.getWriterId() != userId)
            throw new CustomException(ErrorCode.AUTHORITY_NOT_FOUND);

        calendar.setName(schedulePostReq.getName());
        calendar.setContent(schedulePostReq.getContent());
        calendar.setSchedule(schedulePostReq.getSchedule());
        calendar.setYear(schedulePostReq.getSchedule().getYear());
        calendar.setMonth(schedulePostReq.getSchedule().getMonthValue());
        calendar.setDay(schedulePostReq.getSchedule().getDayOfMonth());

        return calendarRepository.save(calendar);
    }

    public boolean delete(long calendarId) {

        long userId = SecurityUtil.getCurrentMemberId();

        Calendar calendar = calendarRepository.findById(calendarId).get();

        if(calendar.getWriterId() != userId)
            throw new CustomException(ErrorCode.AUTHORITY_NOT_FOUND);

        calendarRepository.delete(calendar);
        return true;
    }


    public CalendarRes getSchedule(long calendarId) {

        Calendar calendar = calendarRepository.findById(calendarId).get();

        CalendarRes calendarRes = new CalendarRes();
        calendarRes.setId(calendarId);
        calendarRes.setWriter(UserSimpleInfoRes.from(userRepository.findById(calendar.getWriterId()).get()));
        calendarRes.setName(calendar.getName());
        calendarRes.setContent(calendar.getContent());
        calendarRes.setSchedule(calendar.getSchedule());

        return calendarRes;
    }

    public ArrayList<CalendarRes> getScheduleList(long teamId, int year, int month) {
        ArrayList<CalendarRes> calendarList = new ArrayList<>();

        ArrayList<Calendar> calendars = calendarRepository.findAllByTeamIdAndYearAndMonth(teamId,year,month);

        for (Calendar calendar : calendars){
            CalendarRes calendarRes = new CalendarRes();
            calendarRes.setId(calendar.getId());
            calendarRes.setWriter(UserSimpleInfoRes.from(userRepository.findById(calendar.getWriterId()).get()));
            calendarRes.setName(calendar.getName());
            calendarRes.setContent(calendar.getContent());
            calendarRes.setSchedule(calendar.getSchedule());

            calendarList.add(calendarRes);
        }

        return calendarList;
    }
}
