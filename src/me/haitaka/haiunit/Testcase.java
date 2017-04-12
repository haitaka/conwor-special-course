package me.haitaka.haiunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Testcase {
    private final Method method;

    Testcase(Method method) {
        this.method = method;

    }

    String getName() {
        return method.getName();
    }

    Class<? extends Throwable> getExpectedException() {
        return method.getAnnotation(Test.class).expected();
    }

    void run(Object object)
            throws Throwable {
        try {
            method.invoke(object);
        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }
    }

    class IllegalTestCaseException extends Exception {
    }
}
