package com.miics.server.service;

import com.miics.server.dao.dto.QuestionDto;
import com.miics.server.dao.dto.TestDto;
import com.miics.server.dao.dto.UserAnswerDto;
import com.miics.server.dao.dto.ResultDto;
import com.miics.server.dao.mappers.IQuestionMapper;
import com.miics.server.dao.mappers.ITestMapper;
import com.miics.server.dao.mappers.IResultMapper;
import com.miics.server.dao.mappers.IUserAnswerMapper;
import com.miics.server.dao.models.Question;
import com.miics.server.dao.models.Result;
import com.miics.server.dao.models.Test;
import com.miics.server.dao.models.UserAnswer;
import com.miics.server.dao.repositories.IQuestionRepository;
import com.miics.server.dao.repositories.ITestRepository;
import com.miics.server.dao.repositories.IResultRepository;
import com.miics.server.dao.repositories.IUserAnswerRepository;
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

    private final IResultMapper resultsMapper;

    private final ITestMapper testMapper;

    private final IQuestionMapper questionMapper;

    private final IUserAnswerMapper userAnswerMapper;
    private final IUserAnswerRepository userAnswerRepository;

    @Autowired
    public TestTakingService(ITestRepository testRepository, IResultRepository resultRepository, ITestMapper testMapper,
                             IQuestionRepository questionRepository, IQuestionMapper questionMapper,
                             IResultMapper resultsMapper, IUserAnswerMapper userAnswerMapper, IUserAnswerRepository userAnswerRepository) {
        this.testRepository = testRepository;
        this.resultRepository = resultRepository;
        this.testMapper = testMapper;
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.resultsMapper = resultsMapper;
        this.userAnswerMapper = userAnswerMapper;
        this.userAnswerRepository = userAnswerRepository;
    }

    public TestDto createTestAndQuestions(TestDto testDto) {
        Test test = testMapper.toEntity(testDto);
        Test result = testRepository.save(test);
        for (QuestionDto item : testDto.getQuestions()) {
            Question question = questionMapper.toEntity(item);
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

    public ResultDto calculateResults(ResultDto resultDto) {
        List<UserAnswerDto> userAnswerDtos = resultDto.getUserAnswerDtos();

        resultDto.setTotalQuestions(userAnswerDtos.size());

        int correctAnswers = 0;
        for(UserAnswerDto userAnswerDto : userAnswerDtos){
            Boolean isAnswerCorrect = true;
            Optional<Question> question = questionRepository.findById(userAnswerDto.getId());
            if (question.isPresent()){
                for(String answer : userAnswerDto.getUserAnswers()){
                    int index = question.get().getAnswers().indexOf(answer);
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

        resultDto.setCorrectAnswers(correctAnswers);
        resultDto.setWrongAnswers(userAnswerDtos.size() - correctAnswers);
        resultDto.setUserAnswerDtos(userAnswerDtos);
        Result result = resultRepository.save(resultsMapper.toEntity(resultDto));

        for(UserAnswerDto userAnswerDto : userAnswerDtos){
            for(String answer : userAnswerDto.getUserAnswers()){
                UserAnswer userAnswer = userAnswerMapper.toEntity(userAnswerDto);
                userAnswer.setResult(result);
                userAnswerRepository.save(userAnswer);
            }
        }
        return resultDto;
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
