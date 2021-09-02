package testutils;

import nl.tudelft.cse1110.andy.result.Result;
import org.assertj.core.api.Condition;

public class ResultTestAssertions {

    public static Condition<Result> successfulCompilation() {
        return new Condition<>() {
            @Override
            public boolean matches(Result value) {
                return value.getCompilation().successful();
            }
        };
    }

    public static Condition<Result> failedCompilation() {
        return new Condition<>() {
            @Override
            public boolean matches(Result value) {
                return !value.getCompilation().successful();
            }
        };
    }

    public static Condition<Result> configurationError() {
        return new Condition<>() {
            @Override
            public boolean matches(Result value) {
                return value.getCompilation().hasConfigurationError();
            }
        };
    }

    public static Condition<Result> compilationErrorInLine(int line) {
        return new Condition<>() {
            @Override
            public boolean matches(Result value) {
                return value.getCompilation().getErrors().stream().anyMatch(e -> e.getLineNumber() == line);
            }
        };
    }

    public static Condition<Result> finalGrade(int grade) {
        return new Condition<>() {
            @Override
            public boolean matches(Result value) {
                return value.getFinalGrade() == grade;
            }
        };
    }

    public static Condition<Result> testsExecuted() {
        return new Condition<>() {
            @Override
            public boolean matches(Result value) {
                return value.getTests().wasExecuted();
            }
        };
    }

    public static Condition<Result> compilationErrorType(String error) {
        return new Condition<>() {
            @Override
            public boolean matches(Result value) {
                return value.getCompilation().getErrors().stream().anyMatch(e -> e.getMessage().equals(error));
            }
        };
    }
    public static Condition<Result> compilationErrorMoreTimes(String errorType, int times) {
        return new Condition<>() {
            @Override
            public boolean matches(Result value) {
                return value.getCompilation().getErrors().stream().filter(e -> e.getMessage().contains(errorType)).count() == times;
            }
        };
    }


}
