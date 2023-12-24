package com.miics.server.service;

import com.miics.server.dao.dto.QuestionDto;
import com.miics.server.dao.dto.TestDto;
import com.miics.server.dao.dto.UserAnswerDto;
import com.miics.server.dao.dto.UserTestResultsDto;
import com.miics.server.dao.mappers.IQuestionMapper;
import com.miics.server.dao.mappers.ITestMapper;
import com.miics.server.dao.models.Question;
import com.miics.server.dao.models.Test;
import com.miics.server.dao.models.UserAnswer;
import com.miics.server.dao.repositories.IQuestionRepository;
import com.miics.server.dao.repositories.IResultRepository;
import com.miics.server.dao.repositories.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestTakingService {

    private final ITestRepository testRepository;

    private final IResultRepository resultRepository;

    private final IQuestionRepository questionRepository;

    private final ITestMapper testMapper;

    private final IQuestionMapper questionMapper;

    @Autowired
    public TestTakingService(ITestRepository testRepository, IResultRepository resultRepository, ITestMapper testMapper, IQuestionRepository questionRepository, IQuestionMapper questionMapper) {
        this.testRepository = testRepository;
        this.resultRepository = resultRepository;
        this.testMapper = testMapper;
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

//    public void submitTest(Long testId, User student, int score) {
//        Test test = testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
//        Result result = new Result();
//        result.setTest(test);
//        result.setUser(student);
//        result.setScore(score);
//        resultRepository.save(result);
//    }

    public TestDto createTestAndQuestions(TestDto testDto) {
        Test test = testMapper.unDto(testDto);
        Test result = testRepository.save(test);
        for (QuestionDto item : testDto.getQuestions()) {
            Question question = questionMapper.unDto(item);
            question.setTest(result);
            questionRepository.save(question);
        }

            return testMapper.toDto(testRepository.findById(result.getId()).get());
    }

    public TestDto getTestById(Long testId){

        Optional<Test> testOptional = testRepository.findById(testId);
        if(testOptional.isPresent()){
            Test test = testOptional.get();
            test.setQuestions(questionRepository.findAllByTestId(testId));
            List<QuestionDto> questionDtos = questionMapper.toDtos(questionRepository.findAllByTestId(testId));
            TestDto testDto = testMapper.toDto(test);
            testDto.setQuestions(questionDtos);
            return testDto;
        }else throw new NullPointerException("Теста не существует");
    }

    public UserTestResultsDto calculateResults(UserTestResultsDto userTestResultsDto) {
        List<UserAnswerDto> userAnswerDtos = userTestResultsDto.getUserAnswerDtos();

        userTestResultsDto.setTotalQuestions(userAnswerDtos.size());

        int correctAnswers = 0;
        for(UserAnswerDto userAnswerDto : userAnswerDtos){
            Boolean isAnswerCorrect = true;
            Optional<Question> question = questionRepository.findById(userAnswerDto.getId());
            if (question.isPresent()){
                for(String userAnswer : userAnswerDto.getUserAnswers()){
                    int index = question.get().getAnswers().indexOf(userAnswer);
                    if(index == -1) isAnswerCorrect = false;
                    else{
                        Boolean isCorrect = question.get().getIsCorrect().get(index);
                        if(!isCorrect){
                            isAnswerCorrect = false;
                        }
                    }

                }

            }else throw new NullPointerException("Вопроса не существует");

            if(isAnswerCorrect){
                correctAnswers++;
                userAnswerDto.setPassed(true);
            }else userAnswerDto.setPassed(false);
        }

        userTestResultsDto.setCorrectAnswers(correctAnswers);
        userTestResultsDto.setWrongAnswers(userAnswerDtos.size() - correctAnswers);
        userTestResultsDto.setUserAnswerDtos(userAnswerDtos);
        return userTestResultsDto;
    }

    public List<TestDto> getAllTests() {
        List<Test> tests = testRepository.findAll();
        List<TestDto> testDtos = testMapper.toDtos(tests);
        List<QuestionDto> questionDtos = new ArrayList<>();
        for(TestDto test : testDtos){
            List<Question> questions = questionRepository.findAllByTestId(test.getId());
            questionDtos = questionMapper.toDtos(questions);
            test.setQuestions(questionDtos);
        }

        return testDtos;
    }
}
