package com.example.demo.user.adapter.out.persistence;

import com.example.demo.common.annotaion.PersistenceAdapter;
import com.example.demo.common.exception.UserNotFoundException;
import com.example.demo.common.utils.TokenProvider;
import com.example.demo.user.application.port.out.LoadUserPort;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class UserLoadPersistenceAdapter implements LoadUserPort {
    private final SpringDataUserRepository userRepository;
    private final UserMapper userMapper;
    private final TokenProvider tokenProvider;

    @Override
    public User loadById(Long id) {
        UserJpaEntity userJpaEntity =
                userRepository.findById(id)
                        .orElseThrow(UserNotFoundException::new);
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public User loadByEmail(String email) {
        UserJpaEntity userJpaEntity =
                userRepository.findByEmail(email)
                        .orElseThrow(UserNotFoundException::new);
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public User loadByNickname(String nickname) {
        UserJpaEntity userJpaEntity =
                userRepository.findByNickname(nickname)
                        .orElseThrow(UserNotFoundException::new);
        return userMapper.mapToDomainEntity(userJpaEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }


}
