package com.ilyaselmabrouki.test_service.test;

import org.springframework.stereotype.Service;

@Service
public class TestMapper {

    public Test toTest(TestRequest request){
        return Test.builder()
                .candidateId(request.getCandidateId())
                .offerId(request.getOfferId())
                .applicationId(request.getApplicationId())
                .score(0.0)
                .build();
    }

    public TestResponse fromTest(Test test){
        return new TestResponse(
                test.getId(),
                null,
                null,
                test.getScore(),
                test.getCreatedAt()
        );
    }
}
