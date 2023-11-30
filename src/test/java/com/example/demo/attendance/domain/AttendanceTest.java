package com.example.demo.attendance.domain;

import com.example.demo.attendance.adapter.out.persistence.Attendance.AttendanceJpaEntity;
import com.example.demo.attendance.domain.constant.DayType;
import com.example.demo.attendance.domain.constant.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Mockist로 작성하려고 했는데 의부 의존성을 가지고 있지 않아 Classicist로 작성 하였습니다.
@DisplayName("Attendance 단위 테스트")
class AttendanceTest {

    private Attendance attendance;

    @BeforeEach
    public void setup() {
        attendance = Attendance.builder()
                .attendanceId(new Attendance.AttendanceId(1L))
                .userId(1L)
                .department(Department.DED)
                .name("John Doe")
                .workDate(LocalDate.now())
                .dayType(DayType.WEEKDAY)
                .startTime(LocalTime.of(9, 0))
                .endTime(LocalTime.of(18, 0))
                .build();
    }

    @Test
    @DisplayName("Domain -> Jpa Entity 변환 테스트")
    public void toJpaEntityTest() {
        AttendanceJpaEntity attendanceJpaEntity = attendance.toJpaEntity();

        assertEquals(1L, attendanceJpaEntity.getUserId());
        assertEquals(Department.DED, attendanceJpaEntity.getDepartment());
        assertEquals("John Doe", attendanceJpaEntity.getName());
        assertEquals(LocalDate.now(), attendanceJpaEntity.getWorkDate());
        assertEquals(DayType.WEEKDAY, attendanceJpaEntity.getDayType());
        assertEquals(LocalTime.of(9, 0), attendanceJpaEntity.getStartTime());
        assertEquals(LocalTime.of(18, 0), attendanceJpaEntity.getEndTime());
    }
}