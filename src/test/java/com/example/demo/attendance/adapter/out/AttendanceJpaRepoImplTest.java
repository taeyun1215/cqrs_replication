package com.example.demo.attendance.adapter.out;

import com.example.demo.attendance.adapter.out.persistence.Attendance.AttendanceJpaEntity;
import com.example.demo.attendance.adapter.out.persistence.Attendance.AttendanceJpaRepoImpl;
import com.example.demo.attendance.adapter.out.persistence.Attendance.QAttendanceJpaEntity;
import com.example.demo.attendance.domain.AttendanceSearchCriteria;
import com.example.demo.attendance.domain.FixedStartTime;
import com.example.demo.attendance.domain.constant.DayType;
import com.example.demo.attendance.domain.constant.Department;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AttendanceJpaRepoImpl 단위 테스트")
class AttendanceJpaRepoImplTest {
    @InjectMocks
    private AttendanceJpaRepoImpl attendanceJpaRepo;

    @Mock
    private JPAQueryFactory jpaQueryFactory;

    @Mock
    private JPAUpdateClause jpaUpdateClause;

    private AttendanceJpaEntity attendanceJpaEntity;
    private QAttendanceJpaEntity qAttendanceJpaEntity = QAttendanceJpaEntity.attendanceJpaEntity;

    @BeforeEach
    public void setup() {
        attendanceJpaEntity = AttendanceJpaEntity.builder()
                .userId(1L)
                .department(Department.DED)
                .name("Test User")
                .workDate(LocalDate.now())
                .dayType(DayType.WEEKDAY)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now().plusHours(8))
                .build();
    }

    @Test
    @DisplayName("AttendanceSearchPeriod이 있을 경우 검색 검증")
    void SearchAttendanceByCriteriaTest() {
        // given
        AttendanceSearchCriteria criteria = AttendanceSearchCriteria.builder()
                .startDate(LocalDate.now().minusDays(5))
                .endDate(LocalDate.now().plusDays(5))
                .department(Department.DED)
                .name("테스트")
                .build();

        JPAQuery<AttendanceJpaEntity> jpaQuery = mock(JPAQuery.class);
        when(jpaQueryFactory.select(qAttendanceJpaEntity)).thenReturn(jpaQuery);
        when(jpaQuery.from(qAttendanceJpaEntity)).thenReturn(jpaQuery);
        when(jpaQuery.where((Predicate) any())).thenReturn(jpaQuery);
        when(jpaQuery.fetch()).thenReturn(Collections.singletonList(attendanceJpaEntity));

        // when
        List<AttendanceJpaEntity> result = attendanceJpaRepo.searchAttendanceByCriteria(criteria);

        // then
        verify(jpaQueryFactory, times(1)).select((Expression<Object>) any());
        verify(jpaQuery, times(1)).from((EntityPath<?>) any());
        verify(jpaQuery, times(1)).where((Predicate) any());
        verify(jpaQuery, times(1)).fetch();
        assertEquals(1, result.size());
        assertEquals(attendanceJpaEntity, result.get(0));
    }

}