package com.miics.server.service;

import com.miics.server.dao.dto.*;
import com.miics.server.dao.mappers.IUserAnswerMapper;
import com.miics.server.dao.mappers.IUserMapper;
import com.miics.server.dao.mappers.IResultMapper;
import com.miics.server.dao.models.User;
import com.miics.server.dao.repositories.IUserAnswerRepository;
import com.miics.server.dao.repositories.IUserRepository;
import com.miics.server.dao.repositories.IResultRepository;
import com.miics.server.service.configuration.JwtUtilities;
import com.miics.server.service.configuration.UserSecurity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = {"com.miics.server.dao.mappers"})
public class UserService /*implements UserDetailsService*/{

    private final AuthenticationManager authenticationManager ;
    private final IUserMapper userMapper;
    private final IUserRepository userRepository;
    private final IUserAnswerRepository userAnswerRepository;
    private final IResultRepository resultsRepository;
    private final IResultMapper resultsMapper;
    private final IUserAnswerMapper userAnswerMapper;
    private final PasswordEncoder passwordEncoder ;
    private final JwtUtilities jwtUtilities ;

    public UserService(IUserMapper userMapper, @Qualifier("UserRepositoryBean") IUserRepository userRepository, IResultRepository resultsRepository, IResultMapper resultsMapper, IUserAnswerRepository userAnswerRepository, IUserAnswerMapper userAnswerMapper, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtilities jwtUtilities) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.resultsMapper = resultsMapper;
        this.resultsRepository = resultsRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.userAnswerMapper = userAnswerMapper;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtilities = jwtUtilities;
    }

    public UserDto register(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

//    public ResponseEntity<?> register(UserDto userDto) {
//        if(iUserRepository.existsByEmail(userDto.getEmail()))
//        { return  new ResponseEntity<>("email is already taken !", HttpStatus.SEE_OTHER); }
//        else
//        { User user = new User();
//            user.setEmail(registerDto.getEmail());
//            user.setFirstName(registerDto.getFirstName());
//            user.setLastName(registerDto.getLastName());
//            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//            //By Default , he/she is a simple user
//            Role role = iRoleRepository.findByRoleName(RoleName.USER);
//            user.setRoles(Collections.singletonList(role));
//            iUserRepository.save(user);
//            String token = jwtUtilities.generateToken(registerDto.getEmail(),Collections.singletonList(role.getRoleName()));
//            return new ResponseEntity<>(new BearerToken(token , "Bearer "),HttpStatus.OK);
//
//        }
//    }

    public String login(UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getEmail(),
                        userDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(userDto.getUserName());
        UserSecurity userSecurity = new UserSecurity(user.getEmail(), user.getPassword(), user.getRole().grantedAuthorities());
        String token = jwtUtilities.generateToken(userSecurity.getUserEmail(), userSecurity.getAuthorities());
        return token;
    }

    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtos(users);
    }

    public UserDto deleteUser(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getUserName());
        if (user != null) {
            userRepository.delete(user);
            return userMapper.toDto(user);
        }
        throw new RuntimeException("User not found");
    }

    public UserDto deleteUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return userMapper.toDto(user);
    }

    public UserResultDto getUserPage(Long id) {
        UserResultDto userResultDto = new UserResultDto();
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        userResultDto.setRole(user.getRole());
        userResultDto.setName(user.getUserName());
        userResultDto.setEmail(user.getEmail());
        userResultDto.setIsuNumber(user.getIsuNumber());

        // Получение результатов тестов пользователя
        List<ResultDto> resultDtos = resultsMapper.toDtos(resultsRepository.findAllByIsuNumber(id));

        for (ResultDto resultDto : resultDtos) {
            List<UserAnswerDto> userAnswerDtos = userAnswerMapper.toDtos(userAnswerRepository.getUserAnswersByResult_Id(resultDto.getId()));
            resultDto.setUserAnswerDtos(userAnswerDtos);
        }

        // Подсчет общего количества пройденных тестов
        Long testsPassed = (long) resultDtos.size();
        userResultDto.setTestsPassed(testsPassed);

        // Расчет среднего результата прохождения тестов
        double averageTestsResult = resultDtos.stream()
                .mapToDouble(result -> (double) result.getCorrectAnswers() / result.getTotalQuestions() * 100)
                .average()
                .orElse(0.0);
        userResultDto.setAverageTestsResult(averageTestsResult);

        // Добавление результатов тестов в DTO пользователя
        userResultDto.setResultDtoList(resultDtos);

        return userResultDto;
    }

    public ResultDto getTestResultForUserByTestIdAndUserId(Long testId, Long userId){
        return resultsMapper.toDto(resultsRepository.findByTestIdAndIsuNumber(testId, userId));
    }

    public TeacherResultDto getTestResultsForTeacherById(Long testId){
        TeacherResultDto teacherResultDto = new TeacherResultDto();

        List<ResultDto> resultDtos = resultsMapper.toDtos(resultsRepository.findAllByTestId(testId));

        for (ResultDto resultDto : resultDtos) {
            List<UserAnswerDto> userAnswerDtos = userAnswerMapper.toDtos(userAnswerRepository.getUserAnswersByResult_Id(resultDto.getId()));
            resultDto.setUserAnswerDtos(userAnswerDtos);
        }

        teacherResultDto.setResultDtoList(resultDtos);
        teacherResultDto.setTestId(testId);
        teacherResultDto.setTestTitle(resultsRepository.findAllByTestId(testId).getFirst().getTestTitle());

        double averageTestsResult = resultDtos.stream()
                .mapToDouble(result -> (double) result.getCorrectAnswers() / result.getTotalQuestions() * 100)
                .average()
                .orElse(0.0);
        teacherResultDto.setAverageTestsResult(averageTestsResult);

        teacherResultDto.setUsersPassed((long) resultDtos.size());

        return teacherResultDto;
    }
}