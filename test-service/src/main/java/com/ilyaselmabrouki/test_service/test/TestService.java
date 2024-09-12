package com.ilyaselmabrouki.test_service.test;

import com.ilyaselmabrouki.test_service.application.ApplicationClient;
import com.ilyaselmabrouki.test_service.candidate.CandidateClient;
import com.ilyaselmabrouki.test_service.candidate.CandidateResponse;
import com.ilyaselmabrouki.test_service.exception.TestNotFoundException;
import com.ilyaselmabrouki.test_service.offer.OfferClient;
import com.ilyaselmabrouki.test_service.question.QuestionClient;
import com.ilyaselmabrouki.test_service.question.QuestionRequest;
import com.ilyaselmabrouki.test_service.question.QuestionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TestService {

    private final TestRepository repository;
    private final CandidateClient candidateClient;
    private final OfferClient offerClient;
    private final QuestionClient questionClient;
    private final ApplicationClient applicationClient;
    private final TestMapper mapper;

    public List<QuestionResponse> createTest(TestRequest request) {
        //Fetch candidate details from the Candidate Service
        candidateClient.findCandidateById(request.getCandidateId());

        //Fetch offer details from the Offer Service
        offerClient.findOfferById(request.getOfferId());

        //Fetch application details from the Application Service
        applicationClient.findApplicationById(request.getApplicationId());

        //Store test in database
        Test test = mapper.toTest(request);
        Integer testId = repository.save(test).getId();

        //Generate questions from question service
        QuestionRequest questionRequest = new QuestionRequest(testId, request.getOfferId());

        return questionClient.getQuestions(questionRequest);
    }

    public List<TestResponse> getTests() {
        return repository.findAll()
                .stream()
                .map(mapper::fromTest)
                .collect(Collectors.toList());
    }

    public TestResponse findTest(Integer id) {
        return repository.findById(id)
                .map(mapper::fromTest)
                .orElseThrow(()-> new TestNotFoundException("No test found"));
    }

    public ResultResponse calculateResult(Integer id, List<ResultRequest> answers) {
        Integer result = 0;
        for (ResultRequest answer:answers) {
            if (answer.getTestAnswer().equals(answer.getCandidateAnswer())) result++;
        }

        //Update score of the test
        Test test = repository.findById(id).orElseThrow(() -> new TestNotFoundException("No test found"));
        test.setScore(result);
        repository.save(test);

        return new ResultResponse(answers.size(), result);
    }
}
