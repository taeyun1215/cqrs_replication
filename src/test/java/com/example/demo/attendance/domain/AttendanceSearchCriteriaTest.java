package com.example.demo.attendance.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.example.demo.attendance.domain.constant.Department;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@DisplayName("AttendanceSearchCriteria 단위 테스트")
public class AttendanceSearchCriteriaTest {

    @Test
    @DisplayName("AttendanceSearchPeriod의 equals와 hashCode 메서드 검증")
    public void AttendanceSearchPeriod_EqualsAndHashCodeTest() {
        LocalDate startDate1 = LocalDate.of(2022, 5, 1);
        LocalDate endDate1 = LocalDate.of(2022, 5, 31);
        Department department1 = Department.DED;
        String name1 = "test1";

        LocalDate startDate2 = LocalDate.of(2022, 6, 1);
        LocalDate endDate2 = LocalDate.of(2022, 6, 30);
        Department department2 = Department.DAE;
        String name2 = "test2";

        AttendanceSearchCriteria criteria1 = AttendanceSearchCriteria.builder()
                .startDate(startDate1)
                .endDate(endDate1)
                .department(department1)
                .name(name1)
                .build();

        AttendanceSearchCriteria criteria2 = AttendanceSearchCriteria.builder()
                .startDate(startDate2)
                .endDate(endDate2)
                .department(department2)
                .name(name2)
                .build();

        AttendanceSearchCriteria criteria3 = AttendanceSearchCriteria.builder()
                .startDate(startDate1)
                .endDate(endDate1)
                .department(department1)
                .name(name1)
                .build();

        assertNotEquals(criteria1, criteria2);
        assertNotEquals(criteria1.hashCode(), criteria2.hashCode());

        assertEquals(criteria1, criteria3);
        assertEquals(criteria1.hashCode(), criteria3.hashCode());
    }
}