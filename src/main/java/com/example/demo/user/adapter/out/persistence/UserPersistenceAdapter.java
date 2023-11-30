package com.example.demo.user.adapter.out.persistence;

import com.example.demo.common.annotaion.PersistenceAdapter;
import com.example.demo.common.exception.UserAlreadyExistsException;
import com.example.demo.user.application.port.out.CreateUserPort;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class UserPersistenceAdapter implements CreateUserPort {
    private final SpringDataUserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public User createUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(o -> {throw new UserAlreadyExistsException();});
        userRepository.findByNickname(user.getNickname())
                .ifPresent(o -> {throw new UserAlreadyExistsException();});
        UserJpaEntity userJpaEntity = userMapper.mapToJpaEntity(user);
        return userMapper.mapToDomainEntity(userRepository.save(userJpaEntity));
    }
}
