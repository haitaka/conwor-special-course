package me.haitaka.haiunit;

import java.util.ArrayList;
import java.util.List;

class TestsuiteResult {
    private final Testsuite testsute;
    private final List<TestcaseResult> caseResults;

    TestsuiteResult(Testsuite testSute) {
        this.testsute = testSute;
        caseResults = new ArrayList<>();
    }

    void addCaseResult(TestcaseResult caseResult) {
        caseResults.add(caseResult);
    }
}
