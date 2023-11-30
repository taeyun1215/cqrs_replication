package com.example.demo.attendance.adapter.in;

import com.example.demo.attendance.adapter.in.request.SearchAttendanceRequest;
import com.example.demo.attendance.adapter.in.web.SearchAttendanceController;
import com.example.demo.attendance.application.port.in.query.SearchAttendanceQuery;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.domain.constant.DayType;
import com.example.demo.attendance.domain.constant.Department;
import com.example.demo.common.SuccessApiResponse;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SearchAttendanceController 단위 테스트")
public class SearchAttendanceControllerTest {

    @Mock
    private SearchAttendanceQuery searchAttendanceQuery;

    @InjectMocks // InjectMocks 사용한 클래스에 있는 모든 메소드는 when으로 가정을 해준다.
    private SearchAttendanceController searchAttendanceController;

    private SearchAttendanceRequest request;
    private List<Attendance> attendances = new ArrayList<>();

    @BeforeEach
    public void setup() {
        request = new SearchAttendanceRequest(
                LocalDate.now(),
                LocalDate.now(),
                Department.DED,
                "테스트"
        );

        for (int i = 0; i < 5; i++) {
            Attendance attendance = Attendance.builder()
                    .attendanceId(new Attendance.AttendanceId((long) i))
                    .userId((long) i)
                    .department(Department.DED)
                    .name("John Doe " + i)
                    .workDate(LocalDate.now())
                    .dayType(DayType.WEEKDAY)
                    .startTime(LocalTime.now())
                    .endTime(LocalTime.now())
                    .build();
            attendances.add(attendance);
        }
    }

    @Test
    @DisplayName("searchAttendance 행동 판단")
    public void searchAttendanceTest_searchAttendanceQuery() throws Exception {
        // given
        when(searchAttendanceQuery.searchAttendance(any())).thenReturn(attendances);

        // when
        searchAttendanceController.searchAttendance(request);

        // then
        Mockito.verify(searchAttendanceQuery, times(1)).searchAttendance(any());
    }

    @Test
    @DisplayName("searchAttendance 값 비교")
    public void searchAttendanceTest_SuccessApiResponseReturn() throws Exception {
        // given
        when(searchAttendanceQuery.searchAttendance(any())).thenReturn(attendances);

        // when
        SuccessApiResponse<List<Attendance>> successApiResponse = searchAttendanceController.searchAttendance(request);

        // then
        assertEquals(successApiResponse.getData().size(), 5);
    }

}
