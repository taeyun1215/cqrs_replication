package com.example.demo.attendance.application;

import com.example.demo.attendance.application.port.in.command.RegisterAttendanceCommand;
import com.example.demo.attendance.application.port.out.SaveAttendancePort;
import com.example.demo.attendance.application.service.RegisterAttendanceService;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.domain.constant.DayType;
import com.example.demo.attendance.domain.constant.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.times;

// 단위 테스트를 진행하기 위함, junit5는 MockitoExtension를 사용해야함.
@ExtendWith(MockitoExtension.class)
@DisplayName("RegisterAttendanceService 단위 테스트")
public class RegisterAttendanceServiceTest {

    @Mock // mock 객체 주입을 해주기 위함. -> 빈 객체 주입은 아님(스프링 컨텍스트에서 관리 안 함)
    private SaveAttendancePort saveAttendancePort;

    @InjectMocks // InjectMocks는 mock 어노테이션이 붙은 객체들을 의존할 객체.
    private RegisterAttendanceService registerAttendanceService;

    private RegisterAttendanceCommand command;

    @BeforeEach
    public void setup() {
        command = RegisterAttendanceCommand.builder()
                .userId(1L)
                .department(Department.DED)
                .name("John Doe")
                .workDate(LocalDate.now())
                .dayType(DayType.WEEKDAY)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now())
                .build();
    }

    @Test
    @DisplayName("registerAttendance의 saveAttendance 호출 검사")
    public void registerAttendanceTest() {
        // when
        registerAttendanceService.registerAttendance(command);

        // then
        Mockito.verify(saveAttendancePort, times(1)).saveAttendance(Mockito.any(Attendance.class));
    }

}