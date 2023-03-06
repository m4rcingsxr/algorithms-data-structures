package com.marcinseweryn.algorithms.datastructures.stack;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.stream.Stream;

public class ArgumentProvider {
    private static Stream<Arguments> pushIncreaseValueOnFullStack(){
        return Stream.of(
                Arguments.arguments(new Integer[]{1,2,3,4,5}, 5),
                Arguments.arguments(new Integer[]{1,2,3,4,5,6,5,4}, 8),
                Arguments.arguments(new Integer[]{1,2,3,4,5,6,45,4}, 8)
        );
    }

    private static Stream<Arguments> pushTest() {
        return Stream.of(
                Arguments.arguments(new Integer[]{1,2,3,4,5}, 5),
                Arguments.arguments(new Integer[]{2,3,4,5}, 4),
                Arguments.arguments(new Integer[]{1,2,3}, 3),
                Arguments.arguments(new Integer[]{}, 0)
        );
    }

    private static Stream<Arguments> popTest() {
        return Stream.of(
                Arguments.arguments(new Integer[]{1,2,3,4,5}, 5, 4),
                Arguments.arguments(new Integer[]{2,3,4,5}, 5, 3),
                Arguments.arguments(new Integer[]{1,2,3}, 3, 2)
        );
    }

    private static Stream<Arguments> peekTest() {
        return Stream.of(
                Arguments.arguments(new Integer[]{1,2,3,4,8}, 8),
                Arguments.arguments(new Integer[]{2,3,4,5},5),
                Arguments.arguments(new Integer[]{1,2,3}, 3)
        );
    }

    private static Stream<Arguments> toStringTest() {
        return Stream.of(
                Arguments.arguments(new Integer[]{1, 2, 3, 4, 8}, Arrays.toString(new Integer[]{1, 2, 3, 4, 8})),
                Arguments.arguments(new Integer[]{2, 3, 4, 5}, Arrays.toString(new Integer[]{2, 3, 4, 5})),
                Arguments.arguments(new Integer[]{1, 2, 3}, Arrays.toString(new Integer[]{1, 2, 3}))
        );
    }

    private static Stream<Arguments> popToEmptyTest() {
        return Stream.of(
                Arguments.arguments(new Integer[]{1,2,3,4,8}, new Integer[]{8,4,3,2,1}),
                Arguments.arguments(new Integer[]{2,3,4,5},new Integer[]{5,4,3,2}),
                Arguments.arguments(new Integer[]{1,2,3}, new Integer[]{3,2,1}),
                Arguments.arguments(new Integer[]{}, new Integer[]{})
        );
    }
}
