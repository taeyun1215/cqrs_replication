package com.example.demo.user.adapter.out.persistence;

import com.example.demo.common.exception.UserNotFoundException;
import com.example.demo.common.utils.Token;
import com.example.demo.common.utils.TokenProvider;
import com.example.demo.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("유저관련 조회 어댑터 테스트")
@ExtendWith(MockitoExtension.class)
class UserLoadPersistenceAdapterTest {
    @Mock
    private SpringDataUserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private TokenProvider tokenProvider;
    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserLoadPersistenceAdapter userLoadPersistenceAdapter;

    private User user;
    private UserJpaEntity userJpaEntity;
    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(new User.UserId(1L))
                .email("zxc@naver.com")
                .nickname("hongjunland")
                .name("홍길동")
                .build();
        userJpaEntity = UserJpaEntity.builder()
                .id(1L)
                .email("zxc@naver.com")
                .nickname("hongjunland")
                .build();

    }
    @DisplayName("사용자를 찾을 수 없을 때 예외 발생")
    @Test
    public void givenUserNotfound_whenLoadUser_thenThrowException(){
        // given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // when
        assertThrows(UserNotFoundException.class, () -> {
            userLoadPersistenceAdapter.loadById(1L);
        });
        // then
        verify(userRepository, times(1)).findById(user.getId().getValue());
    }
    @DisplayName("아이디로 사용자 로드 성공")
    @Test
    public void givenUser_whenLoadUserById_thenUserIsCreated(){
        // Given
        Long id = 1L;

        // Setting up the behavior of the Mock objects
        when(userRepository.findById(id)).thenReturn(Optional.of(userJpaEntity));
        when(userMapper.mapToDomainEntity(userJpaEntity)).thenReturn(user);

        // When
        User result = userLoadPersistenceAdapter.loadById(id);

        // Then
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(id);
        verify(userMapper, times(1)).mapToDomainEntity(userJpaEntity);
    }
    @DisplayName("이메일로 사용자 로드 성공")
    @Test
    public void givenUser_whenLoadUserByEmail_thenUserIsCreated(){
        String email = "zxc@naver.com";
        // Given
        // Setting up the behavior of the Mock objects
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userJpaEntity));
        when(userMapper.mapToDomainEntity(userJpaEntity)).thenReturn(user);

        // When
        User result = userLoadPersistenceAdapter.loadByEmail(email);

        // Then
        assertEquals(email, result.getEmail());
        verify(userRepository, times(1)).findByEmail(email);
        verify(userMapper, times(1)).mapToDomainEntity(userJpaEntity);
    }
    @DisplayName("이메일로 사용자 여부 성공 테스트")
    @Test
    public void givenTrue_whenUserExistByEmail_thenUserIsCreated(){
        // given
        String email = "zxc123@naver.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        boolean expectedTrue = userLoadPersistenceAdapter.existsByEmail(email);
        boolean expectedFalse = userLoadPersistenceAdapter.existsByEmail("zxczxc55@nate.com");

        assertEquals(true, expectedTrue);
        assertEquals(false, expectedFalse);
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @DisplayName("닉네임으로 사용자 여부 성공 테스트")
    @Test
    public void givenTrue_whenUserExistByNickname_thenUserIsCreated(){
        // given
        String nickname = "홍길동짱";
        when(userRepository.existsByNickname(nickname)).thenReturn(true);

        boolean expectedTrue = userLoadPersistenceAdapter.existsByNickname(nickname);
        boolean expectedFalse = userLoadPersistenceAdapter.existsByNickname(nickname+"1");

        assertEquals(true, expectedTrue);
        assertEquals(false, expectedFalse);
        verify(userRepository, times(1)).existsByNickname(nickname);
    }




    @DisplayName("닉네임으로 사용자 로드 성공")
    @Test
    public void givenUser_whenLoadUserByNickname_thenUserIsCreated(){
        String nickname = "hongjunland";
        // Given
        // Setting up the behavior of the Mock objects
        when(userRepository.findByNickname(nickname)).thenReturn(Optional.of(userJpaEntity));
        when(userMapper.mapToDomainEntity(userJpaEntity)).thenReturn(user);

        // When
        User result = userLoadPersistenceAdapter.loadByNickname(nickname);

        // Then
        assertEquals(nickname, result.getNickname());
        verify(userRepository, times(1)).findByNickname(nickname);
        verify(userMapper, times(1)).mapToDomainEntity(userJpaEntity);
    }
}
