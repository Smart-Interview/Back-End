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
    //private final QuestionClient questionClient;
    private final ApplicationClient applicationClient;
    private final TestMapper mapper;

    public Integer createTest(TestRequest request) {
        //Fetch candidate details from the Candidate Service
        candidateClient.findCandidateById(request.getCandidateId());

        //Fetch offer details from the Offer Service
        offerClient.findOfferById(request.getOfferId());

        //Fetch application details from the Application Service
        applicationClient.findApplicationById(request.getApplicationId());

        Test test = mapper.toTest(request);

        return repository.save(test).getId();
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

//    public List<QuestionResponse> getQuestions(Integer id) {
//        //  Fetch offer details from the Offer Service
//        Integer offerId = 1;
//        QuestionRequest request = new QuestionRequest(id, offerId);
//        return questionClient.getQuestions(request);
//    }
}
