package com.ilyaselmabrouki.test_service.test;

import com.ilyaselmabrouki.test_service.application.ApplicationClient;
import com.ilyaselmabrouki.test_service.candidate.CandidateClient;
import com.ilyaselmabrouki.test_service.candidate.CandidateResponse;
import com.ilyaselmabrouki.test_service.exception.OfferNotFoundException;
import com.ilyaselmabrouki.test_service.exception.TestNotFoundException;
import com.ilyaselmabrouki.test_service.offer.OfferClient;
import com.ilyaselmabrouki.test_service.offer.OfferResponse;
import com.ilyaselmabrouki.test_service.question.QuestionClient;
import com.ilyaselmabrouki.test_service.question.QuestionRequest;
import com.ilyaselmabrouki.test_service.question.QuestionResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    public Integer createTest(TestRequest request) {
        //Fetch candidate details from the Candidate Service
        candidateClient.findCandidateById(request.getCandidateId());

        //Fetch offer details from the Offer Service
        offerClient.findOfferById(request.getOfferId());

        //Fetch application details from the Application Service
        applicationClient.findApplicationById(request.getApplicationId());

        //Store test in database
        Test test = mapper.toTest(request);
        return repository.save(test).getId();
    }

    public Page<TestResponse> getTests(Integer candidateId, int page, int size) {
        // Check candidate ID (add error handling if candidate not found)
        candidateClient.findCandidateById(candidateId);

        // Fetch applications
        Pageable pageable = PageRequest.of(page, size);
        Page<Test> tests = repository.findAllByCandidateId(candidateId, pageable);

        // Fetch all offer responses in a single call (to avoid N+1 problem)
        List<Integer> offerIds = tests.stream()
                .map(Test::getOfferId)
                .collect(Collectors.toList());

        List<OfferResponse> offers = offerClient.findOffersByIds(offerIds);

        Map<Integer, OfferResponse> offerMap = offers.stream()
                .collect(Collectors.toMap(OfferResponse::getId, offer -> offer));

        // Map applications to responses
        return tests.map(test -> {
            OfferResponse offerResponse = offerMap.get(test.getOfferId());
            if (offerResponse == null) {
                throw new OfferNotFoundException("Offer not found");
            }
            TestResponse testResponse = mapper.fromTest(test);
            testResponse.setOffer(offerResponse);
            return testResponse;
        });
    }

    public List<QuestionResponse> findTest(Integer id) {
        Test test = repository.findById(id)
                .orElseThrow(()-> new TestNotFoundException("No test found"));

        //Generate questions from question service
        QuestionRequest questionRequest = new QuestionRequest(id, test.getOfferId());
        return questionClient.getQuestions(questionRequest);
    }

    public Double calculateResult(Integer id, List<ResultRequest> answers) {
        Double result = 0.0;
        for (ResultRequest answer:answers) {
            if (answer.getTestAnswer().equals(answer.getCandidateAnswer())) result++;
        }

        //Update score of the test
        Test test = repository.findById(id).orElseThrow(() -> new TestNotFoundException("No test found"));
        test.setScore((result * 100) / answers.size());
        repository.save(test);

        return test.getScore();
    }
}
