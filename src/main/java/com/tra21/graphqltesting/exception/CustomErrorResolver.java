package com.tra21.graphqltesting.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class CustomErrorResolver extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        Throwable t = NestedExceptionUtils.getMostSpecificCause(ex);

        if(t instanceof ConstraintViolationException constraintViolationException){
            return validationError(constraintViolationException, env);
        }else if (t instanceof NotFoundException exception) {
            return GraphqlErrorBuilder.newError(env)
                    .errorType(ErrorClassification.errorClassification(HttpStatus.BAD_REQUEST.name()))
                    .message(exception.getMessage())
                    .build();
        }else if (t instanceof RuntimeException exception) {
            return GraphqlErrorBuilder.newError(env)
                    .errorType(ErrorClassification.errorClassification(HttpStatus.INTERNAL_SERVER_ERROR.name()))
                    .message("Unexpected Error!")
                    .build();
        }

        // other exceptions not yet caught
        return GraphqlErrorBuilder.newError(env)
                .message("Error occurred: Ensure request is valid ")
                .errorType(ErrorClassification.errorClassification(HttpStatus.INTERNAL_SERVER_ERROR.name()))
                .build();
    }

    private GraphQLError validationError(ConstraintViolationException exception, DataFetchingEnvironment env){
        String invalidFields = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));

        return GraphqlErrorBuilder.newError(env)
                .errorType(ErrorClassification.errorClassification(HttpStatus.INTERNAL_SERVER_ERROR.name()))
                .message(invalidFields)
                .build();
    }
}