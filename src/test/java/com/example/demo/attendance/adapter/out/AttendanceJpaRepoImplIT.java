package com.example.demo.attendance.adapter.out;

import com.example.demo.attendance.adapter.out.persistence.Attendance.AttendanceJpaEntity;
import com.example.demo.attendance.adapter.out.persistence.Attendance.AttendanceJpaRepo;
import com.example.demo.attendance.adapter.out.persistence.Attendance.AttendanceJpaRepoImpl;
import com.example.demo.attendance.domain.AttendanceSearchCriteria;
import com.example.demo.attendance.domain.FixedStartTime;
import com.example.demo.attendance.domain.constant.DayType;
import com.example.demo.attendance.domain.constant.Department;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("AttendanceJpaRepoImpl 통합 테스트")
// 클래스 명 끝에 IT로 쓴건 통합 테스트
public class AttendanceJpaRepoImplIT {

    @Autowired
    private AttendanceJpaRepoImpl attendanceJpaRepoImpl;

    @Autowired
    private AttendanceJpaRepo attendanceJpaRepo;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    @Transactional
    @DisplayName("출결 JPA 저장소 구현 테스트")
    void saveAttendanceTest() {
        // given
        AttendanceJpaEntity attendanceJpaEntity = AttendanceJpaEntity.builder()
                .userId(1L)
                .department(Department.DED)
                .name("Test User")
                .workDate(LocalDate.now())
                .dayType(DayType.WEEKDAY)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now().plusHours(8))
                .build();

        // when
        attendanceJpaRepoImpl.saveAttendance(attendanceJpaEntity);

        // then
        assertNotNull(attendanceJpaEntity.getId());
    }

    @Test
    @Transactional
    @DisplayName("기간별 출결 정보 검색 테스트")
    void searchAttendanceByPeriodTest() {
        // given
        AttendanceJpaEntity attendanceJpaEntity = AttendanceJpaEntity.builder()
                .userId(1L)
                .department(Department.DED)
                .name("Test User")
                .workDate(LocalDate.now())
                .dayType(DayType.WEEKDAY)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now().plusHours(8))
                .build();

        attendanceJpaRepo.save(attendanceJpaEntity);

        AttendanceSearchCriteria criteria = AttendanceSearchCriteria.builder()
                .startDate(LocalDate.now().minusDays(5))
                .endDate(LocalDate.now().plusDays(5))
                .build();

        // when
        List<AttendanceJpaEntity> result = attendanceJpaRepoImpl.searchAttendanceByCriteria(criteria);

        // then
        assertEquals(1, result.size());
    }

    @Test
    @Transactional
    @DisplayName("존재하지 않는 엔티티로 출결 정보 저장 테스트")
    void saveAttendanceTest_WithNonExistingEntity() {
        // given
        AttendanceJpaEntity attendanceJpaEntity = AttendanceJpaEntity.builder()
                .userId(9999L) // userId가 들어올 경우를 대비함.
                .department(Department.DED)
                .name("Test User")
                .workDate(LocalDate.now())
                .dayType(DayType.WEEKDAY)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now().plusHours(8))
                .build();

        // when
        attendanceJpaRepoImpl.saveAttendance(attendanceJpaEntity);

        // then
//        assertNull(attendanceJpaEntity.getId());
    }

    @Test
    @Transactional
    @DisplayName("결과 없는 기간의 출결 정보 검색 테스트")
    void searchAttendanceByPeriodTest_NoResult() {
        // request
        AttendanceSearchCriteria criteria = AttendanceSearchCriteria.builder()
                .startDate(LocalDate.now().plusYears(10))
                .endDate(LocalDate.now().plusYears(10).plusDays(5))
                .department(Department.DED)
                .name("test")
                .build();

        // when
        List<AttendanceJpaEntity> result = attendanceJpaRepoImpl.searchAttendanceByCriteria(criteria);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("출근 시간을 기준으로 지각 상태 업데이트 테스트")
    public void updateAttendanceStatusTest_Late() {
        // given
        LocalTime startTime = LocalTime.of(10, 0); // 지각한 시간으로 가정
        AttendanceJpaEntity attendanceJpaEntity = AttendanceJpaEntity.builder()
                .userId(1L)
                .department(Department.DED)
                .name("Test User")
                .workDate(LocalDate.now())
                .dayType(DayType.WEEKDAY)
                .startTime(startTime)
                .endTime(LocalTime.now().plusHours(8))
                .build();

        attendanceJpaRepo.save(attendanceJpaEntity);

        FixedStartTime fixedStartTime = FixedStartTime.builder()
                .fixedStartTime(LocalTime.of(9, 0))
                .userId(1L)
                .build();

        // when
        attendanceJpaRepo.updateAttendanceStatus(fixedStartTime);

        // then
        AttendanceJpaEntity updatedEntity = attendanceJpaRepo.findById(attendanceJpaEntity.getId()).orElse(null);
        assertNotNull(updatedEntity);
        assertEquals("지각", updatedEntity.getAttendanceStatus());
    }

}
