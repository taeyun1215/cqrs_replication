package com.example.demo.attendance.adapter.out;

import com.example.demo.attendance.adapter.out.persistence.Attendance.AttendanceJpaEntity;
import com.example.demo.attendance.adapter.out.persistence.Attendance.AttendanceJpaRepo;
import com.example.demo.attendance.adapter.out.persistence.Attendance.AttendancePersistenceAdapter;
import com.example.demo.attendance.adapter.out.persistence.Attendance.AttendancePersistenceMapper;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AttendancePersistenceAdapter 단위 테스트")
public class AttendancePersistenceAdapterTest {

    @Mock
    private AttendanceJpaRepo attendanceJpaRepo;

    @Mock
    private AttendancePersistenceMapper mapper;

    @InjectMocks
    private AttendancePersistenceAdapter adapter;

    private Attendance attendance;
    private AttendanceSearchCriteria attendanceSearchCriteria;
    private AttendanceJpaEntity attendanceJpaEntity;

    @BeforeEach
    void setUp() {
        // saveAttendance 메소드에 대한 request
        attendance = Attendance.builder()
                .userId(1L)
                .department(Department.DED)
                .name("John Doe")
                .workDate(LocalDate.now())
                .dayType(DayType.WEEKDAY)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now())
                .build();

        // searchAttendanceByPeriod 메소드에 대한 request
        attendanceSearchCriteria = AttendanceSearchCriteria.builder()
                .startDate(LocalDate.of(2022, 1, 1))
                .endDate(LocalDate.of(2022, 1, 31))
                .department(Department.DED)
                .name("테스트")
                .build();

        attendanceJpaEntity = AttendanceJpaEntity.builder()
                .id(1L)
                .userId(2L)
                .department(Department.DED)
                .name("John Doe")
                .workDate(LocalDate.of(2023, 8, 1))
                .dayType(DayType.WEEKDAY)
                .startTime(LocalTime.of(9, 0))
                .endTime(LocalTime.of(18, 0))
                .build();
    }

    @Test
    @DisplayName("Attendance 저장 및 업데이트 검증")
    void saveAttendanceTest() {
        // given -> 의존성을 갖는 메소드들에서 해주는데 단, 반환을 하지 않으면 가정으로 안 넣어도 됨.
        when(mapper.mapToJpaEntity(attendance)).thenReturn(attendanceJpaEntity);

        // when
        adapter.saveAttendance(attendance);

        // then
        verify(mapper, times(1)).mapToJpaEntity(attendance);
        verify(attendanceJpaRepo, times(1)).saveAttendance(attendanceJpaEntity);
    }

    @Test
    @DisplayName("Attendance 기간별 검색 검증")
    public void SearchAttendanceByPeriodTest() {
        // given
        when(attendanceJpaRepo.searchAttendanceByCriteria(attendanceSearchCriteria))
                .thenReturn(Collections.singletonList(attendanceJpaEntity));
        when(mapper.mapToDomainEntities(anyList())) // 해당 메소드를 검증하는게 아니므로 any()를 사용해줘도 무방함.
                .thenReturn(Collections.singletonList(attendance));

        // when
        List<Attendance> attendances = adapter.searchAttendanceByCriteria(attendanceSearchCriteria);

        // then
        verify(attendanceJpaRepo, times(1)).searchAttendanceByCriteria(attendanceSearchCriteria);
        verify(mapper, times(1)).mapToDomainEntities(anyList());

        assertFalse(attendances.isEmpty());
        assertEquals(1, attendances.size());
        assertEquals(attendance, attendances.get(0));

    }
}
