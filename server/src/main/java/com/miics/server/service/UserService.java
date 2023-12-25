package com.miics.server.service;

import com.miics.server.dao.dto.*;
import com.miics.server.dao.mappers.IUserAnswerMapper;
import com.miics.server.dao.mappers.IUserMapper;
import com.miics.server.dao.mappers.IResultMapper;
import com.miics.server.dao.models.User;
import com.miics.server.dao.repositories.IUserAnswerRepository;
import com.miics.server.dao.repositories.IUserRepository;
import com.miics.server.dao.repositories.IResultRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = {"com.miics.server.dao.mappers"})
public class UserService /*implements UserDetailsService*/{

    private final IUserMapper userMapper;

    private final IUserRepository userRepository;

    private final IUserAnswerRepository userAnswerRepository;

    private final IResultRepository resultsRepository;

    private final IResultMapper resultsMapper;

    private final IUserAnswerMapper userAnswerMapper;

    public UserService(IUserMapper userMapper, @Qualifier("UserRepositoryBean") IUserRepository userRepository, IResultRepository resultsRepository, IResultMapper resultsMapper, IUserAnswerRepository userAnswerRepository, IUserAnswerMapper userAnswerMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.resultsMapper = resultsMapper;
        this.resultsRepository = resultsRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.userAnswerMapper = userAnswerMapper;
    }

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public UserDto register(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

//    public UserDto login(UserDto userDto) {
//        User user = userRepository.findByUserName(userDto.getUserName());
////        if (user != null && passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
////            return userMapper.toDto(user);
////        }
//        throw new RuntimeException("Invalid username or password");
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
        User user = userRepository.findByUserName(userDto.getUserName());
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
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }