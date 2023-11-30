package com.example.demo.attendance.adapter.in;

import com.example.demo.attendance.adapter.in.request.RegisterFixedStartTimeRequest;
import com.example.demo.attendance.adapter.in.web.RegisterFixedStartTimeController;
import com.example.demo.attendance.application.port.in.command.RegisterFixedStartTimeCommand;
import com.example.demo.attendance.application.port.in.usecase.RegisterFixedStartTimeUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("RegisterFixedStartTimeController 단위 테스트")
public class RegisterFixedStartTimeControllerTest {

    @Mock
    private RegisterFixedStartTimeUseCase registerFixedStartTimeUseCase;

    @InjectMocks
    private RegisterFixedStartTimeController registerFixedStartTimeController;

    private RegisterFixedStartTimeRequest request;

    @BeforeEach
    public void setup() {
        request = new RegisterFixedStartTimeRequest(
                LocalTime.now(),
                1L
        );
    }

    @Test
    @DisplayName("RegisterFixedStartTimeController의 RegisterFixedStartTimeUseCase 호출 검사")
    public void registerFixedStartTime() {
        // when
        registerFixedStartTimeController.registerFixedStartTime(request);

        // then
        Mockito.verify(registerFixedStartTimeUseCase, times(1)).RegisterFixedStartTime(Mockito.any(RegisterFixedStartTimeCommand.class));
    }
}
