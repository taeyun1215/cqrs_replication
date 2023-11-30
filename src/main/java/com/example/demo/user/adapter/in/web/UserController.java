package com.example.demo.user.adapter.in.web;

import com.example.demo.common.SuccessApiResponse;
import com.example.demo.common.annotaion.CurrentUser;
import com.example.demo.user.adapter.in.web.request.CreateUserRequest;
import com.example.demo.user.application.port.in.CreateUserUseCase;
import com.example.demo.user.application.port.in.GetUserQuery;
import com.example.demo.user.application.port.in.command.CreateUserCommand;
import com.example.demo.common.annotaion.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final GetUserQuery getUserQuery;
    @PostMapping
    public SuccessApiResponse<?> createUser(@RequestBody CreateUserRequest createUserRequest){
        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .email(createUserRequest.getEmail())
                .name(createUserRequest.getName())
                .nickname(createUserRequest.getNickname())
                .password(createUserRequest.getPassword())
                .build();
        createUserUseCase.createUser(createUserCommand);
        return SuccessApiResponse.of();
    }
    @GetMapping
    public SuccessApiResponse getCurrentUser(@CurrentUser Long userId){
        return SuccessApiResponse.of(getUserQuery.getUser(userId));
    }
    @GetMapping("/{userId}")
    public SuccessApiResponse getUserById(@PathVariable Long userId){
        return SuccessApiResponse.of(getUserQuery.getUser(userId));
    }

}
