package me.haitaka.haiunit;

class TestcaseResult {
    private final Testcase testcase;
    private final long time;
    private final Throwable failureReason;

    TestcaseResult(Testcase testcase, long time, Throwable failureReason) {
        this.testcase = testcase;
        this.time = time;
        this.failureReason = failureReason;
    }

    String getName() {
        return testcase.getName();
    }

    long getTime() {
        return time;
    }

    Throwable getFailureReason() {
        return failureReason;
    }

    boolean isFailed() {
        return (failureReason != null);
    }
}
