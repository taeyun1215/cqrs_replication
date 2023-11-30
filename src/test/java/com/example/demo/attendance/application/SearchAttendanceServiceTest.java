package com.example.demo.attendance.application;

import com.example.demo.attendance.application.port.in.query.SearchAttendanceQueryParameters;
import com.example.demo.attendance.application.port.out.SearchAttendancePort;
import com.example.demo.attendance.application.service.SearchAttendanceService;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.domain.AttendanceSearchCriteria;
import com.example.demo.attendance.domain.constant.DayType;
import com.example.demo.attendance.domain.constant.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SearchAttendanceService 단위 테스트")
class SearchAttendanceServiceTest {

    @Mock
    private SearchAttendancePort searchAttendancePort;

    @InjectMocks
    private SearchAttendanceService searchAttendanceService;

    private SearchAttendanceQueryParameters queryParameters;
    private Attendance mockAttendance;

    @BeforeEach
    void setUp() {
        queryParameters = SearchAttendanceQueryParameters.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .department(Department.DED)
                .name("테스트")
                .build();

        mockAttendance = Attendance.builder()
                .attendanceId(new Attendance.AttendanceId(1L))
                .userId(123L)
                .department(Department.DED)
                .name("John Doe")
                .workDate(LocalDate.now())
                .dayType(DayType.WEEKDAY)
                .startTime(LocalTime.of(9, 0))
                .endTime(LocalTime.of(18, 0))
                .build();
    }

    @Test
    @DisplayName("날짜 검색 행동 및 결과 값 테스트")
    void searchAttendanceTest() {
        // Given
        when(searchAttendancePort.searchAttendanceByCriteria(any(AttendanceSearchCriteria.class)))
                .thenReturn(Collections.singletonList(mockAttendance));

        // When
        List<Attendance> result = searchAttendanceService.searchAttendance(queryParameters);

        // Then
        verify(searchAttendancePort, times(1)).searchAttendanceByCriteria(any(AttendanceSearchCriteria.class));
        assertFalse(result.isEmpty());
        assertEquals(mockAttendance, result.get(0));

    }
}
