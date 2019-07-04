package com.vn.react.duplicatecode;

public class CheckCodeDuplication {

    public int  factorial(int number){
        if(number < 1){
            return 1;
        }else{
            return number * factorial(number -1);
        }
    }

    public void function(int number) {
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
        return fibonacci1(number - 1) + fibonacci1(number - 2);
    }

    private int array_a[] = new int[5];
    private  int array_b[] =new int [5];

    public void showDuplicate() {

        int sum_a = 0;

        for (int i = 0; i < 4; i++)
            sum_a += array_a[i];

        int average_a = sum_a / 4;

        int sum_b = 0;
        for (int i = 0; i < 4; i++)
            sum_b += array_b[i];

        int average_b = sum_b / 4;

    }



    public void showDuplicate1() {

        int sum_a = 0;

        for (int i = 0; i < 4; i++)
            sum_a += array_a[i];

        int average_a = sum_a / 4;

        int sum_b = 0;
        for (int i = 0; i < 4; i++)
            sum_b += array_b[i];

        int average_b = sum_b / 4;

    }

}
