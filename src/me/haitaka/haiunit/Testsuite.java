package me.haitaka.haiunit;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class Testsuite {
    private final Class<?> classObj;
    private final List<Testcase> testcases;

    Testsuite(Class<?> classObj) {
        this.classObj = classObj;
        this.testcases = new ArrayList<>();

        for (Method method : classObj.getMethods()) {
            if (method.getAnnotation(Test.class) != null) {
                testcases.add(new Testcase(method));
            }
        }
    }

    void run() throws IllegalTestException {
        Object testObject;
        try {
            testObject = classObj.newInstance();
        } catch (IllegalAccessException|InstantiationException e) {
            throw new IllegalTestException();
        }

        TestsuiteResult suiteResult = new TestsuiteResult(this);

        for (Testcase testcase : testcases) {
            Throwable throwed = null;
            Throwable failureReason = null;
            // TODO : catch output

            long startTime = System.nanoTime();
            try {
                testcase.run(testObject);
            } catch (Throwable e) {
                throwed = e;
            }
            long finishTime = System.nanoTime();

            if (throwed instanceof Testcase.IllegalTestCaseException) {
                failureReason = new IllegalTestException(); // TODO : process
            }
            Class<? extends Throwable> expectedException = testcase.getExpectedException();
            if (expectedException != Test.None.class) {
                if (expectedException.isInstance(throwed)) {
                    failureReason = null; // it's OK
                } else {
                    failureReason = throwed;
                }
            }

            long timeElapsed = finishTime - startTime;
            suiteResult.addCaseResult(new TestcaseResult(testcase, timeElapsed, failureReason));
        }
    }

    class IllegalTestException extends Throwable {

    }
}
