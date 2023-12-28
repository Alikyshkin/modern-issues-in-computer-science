package com.miics.server.service;

import com.miics.server.dao.dto.*;
import com.miics.server.dao.mappers.IResultMapper;
import com.miics.server.dao.mappers.IUserAnswerMapper;
import com.miics.server.dao.mappers.IUserMapper;
import com.miics.server.dao.models.User;
import com.miics.server.dao.repositories.IResultRepository;
import com.miics.server.dao.repositories.IUserAnswerRepository;
import com.miics.server.dao.repositories.IUserRepository;
import com.miics.server.service.configuration.UserSecurity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = {"com.miics.server.dao.mappers"})
public class UserService implements UserDetailsService {

    private final AuthenticationManager authenticationManager ;
    private final IUserMapper userMapper;
    private final IUserRepository userRepository;
    private final IUserAnswerRepository userAnswerRepository;
    private final IResultRepository resultsRepository;
    private final IResultMapper resultsMapper;
    private final IUserAnswerMapper userAnswerMapper;
    private final PasswordEncoder passwordEncoder ;

    public UserService(IUserMapper userMapper, @Qualifier("UserRepositoryBean") IUserRepository userRepository, IResultRepository resultsRepository, IResultMapper resultsMapper, IUserAnswerRepository userAnswerRepository, IUserAnswerMapper userAnswerMapper, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.resultsMapper = resultsMapper;
        this.resultsRepository = resultsRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.userAnswerMapper = userAnswerMapper;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return "Cool";
    }

    public String login(UserDto userDto){
        // Здесь вы должны получить данные из запроса, проверить их и, если все хорошо, вернуть токен авторизации
        // Например:
        String username = userDto.getEmail();
        String password = userDto.getPassword();
        // Проверяем данные пользователя
        // Если все хорошо, возвращаем токен авторизации
        String token = "your_token";
        return  token;
    }

    public String logout(HttpServletRequest request, HttpServletResponse response){
        // Здесь вы должны выполнить выход пользователя из системы
        // Например:
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "Successful";
    }

    public boolean checkToken(String token) {
        // Здесь вы должны проверить валидность токена
        // Например:
        boolean isValid = "your_token".equals(token);
        return isValid;
    }

//    public String login(UserDto userDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        userDto.getEmail(),
//                        userDto.getPassword()
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        User user = userRepository.findByEmail(userDto.getEmail());
//        UserSecurity userSecurity = new UserSecurity(user.getEmail(), user.getPassword(), user.getRole().grantedAuthorities());
//        return
//    }

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return  UserSecurity.fromUser(user) ;
    }
}