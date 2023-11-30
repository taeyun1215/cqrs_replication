package com.example.demo.attendance.adapter.in;

import com.example.demo.attendance.adapter.in.request.RegisterAttendanceRequest;
import com.example.demo.attendance.adapter.in.web.RegisterAttendanceController;
import com.example.demo.attendance.application.port.in.command.RegisterAttendanceCommand;
import com.example.demo.attendance.application.port.in.usecase.RegisterAttendanceUseCase;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("RegisterAttendanceController 단위 테스트")
public class RegisterAttendanceControllerTest {

    @Mock
    private RegisterAttendanceUseCase registerAttendanceUseCase;

    @InjectMocks
    private RegisterAttendanceController registerAttendanceController;

    private List<RegisterAttendanceRequest> requests = new ArrayList<>();

    @BeforeEach
    public void setup() {
        for (int i = 0; i < 5; i++) {
            RegisterAttendanceRequest request = new RegisterAttendanceRequest(
                    (long)i,
                    Department.DED,
                    "John Doe " + i,
                    LocalDate.now(),
                    DayType.WEEKDAY,
                    LocalTime.now(),
                    LocalTime.now()
            );
            requests.add(request);
        }
    }

    @Test
    @DisplayName("registerAttendance의 RegisterAttendanceUseCase 호출 검사")
    public void registerAttendanceTest() throws Exception {
        // when
        registerAttendanceController.registerAttendance(requests);

        // then
        Mockito.verify(registerAttendanceUseCase, times(requests.size())).registerAttendance(Mockito.any(RegisterAttendanceCommand.class));
    }

}
