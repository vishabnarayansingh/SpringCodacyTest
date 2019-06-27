package com.vn.react.duplicatecode;

public class CheckCodeDuplication1 {

    int factorial(int number){
        if(number < 1){
            return 1;
        }else{
            return number * factorial(number -1);
        }
    }

    void function(int number) {
        if(number == 0)
            return;

        function(number/2);
        System.out.print("%d" + number);
    }

    int fibonacci(int number) {
        if ( number <= 1) {
            return number;
        }
        return fibonacci(number - 1) + fibonacci(number - 2);
    }
    int fibonacci1(int number) {
        if ( number <= 1) {
            return number;
        }
        return fibonacci(number - 1) + fibonacci(number - 2);
    }
    int fibonacci2(int number) {
        if ( number <= 1) {
            return number;
        }
        return fibonacci(number - 1) + fibonacci(number - 2);
    }

    int fibonacci3(int number) {
        if ( number <= 1) {
            return number;
        }
        return fibonacci(number - 1) + fibonacci(number - 2);
    }
    int fibonacci4(int number) {
        if ( number <= 1) {
            return number;
        }
        return fibonacci(number - 1) + fibonacci(number - 2);
    }

}
