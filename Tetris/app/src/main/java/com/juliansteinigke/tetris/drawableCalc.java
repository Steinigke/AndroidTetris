package com.juliansteinigke.tetris;

public class drawableCalc {

    private int first, second, third, fourth, fifth, sixth, seventh, eighth, ninth;

    private void setDigits(int number){

        int i = 1;
        first = 0; second = 0; third = 0; fourth = 0; fifth = 0; sixth = 0; seventh = 0; eighth = 0; ninth = 0;

        while (number > 0) {


            switch (i){

                case 1: first = number % 10; break;
                case 2: second = number % 10; break;
                case 3: third = number % 10; break;
                case 4: fourth = number % 10; break;
                case 5: fifth = number % 10; break;
                case 6: sixth = number % 10; break;
                case 7: seventh = number % 10; break;
                case 8: eighth = number % 10; break;
                case 9: ninth = number % 10; break;
                default: first = 9; second = 9; third = 9; fourth = 9; fifth = 9; sixth = 9; seventh = 9; eighth = 9; ninth = 9;

            }
            number = number / 10;
            i++;
        }

    }

    private int drawableNumber(int digit){

        switch (digit){

            case 0: return R.drawable.numzero;
            case 1: return R.drawable.numone;
            case 2: return R.drawable.numtwo;
            case 3: return R.drawable.numthree;
            case 4: return R.drawable.numfour;
            case 5: return R.drawable.numfive;
            case 6: return R.drawable.numsix;
            case 7: return R.drawable.numseven;
            case 8: return R.drawable.numeight;
            default: return R.drawable.numnine;


        }

    }

    public int getdrawable(int digit, int number){

        setDigits(number);

        switch (digit){

            case 0: return drawableNumber(first);
            case 1: return drawableNumber(second);
            case 2: return drawableNumber(third);
            case 3: return drawableNumber(fourth);
            case 4: return drawableNumber(fifth);
            case 5: return drawableNumber(sixth);
            case 6: return drawableNumber(seventh);
            case 7: return drawableNumber(eighth);
            default: return drawableNumber(ninth);

        }

    }

    public int getNextpice(int id){

        switch(id){

            case 0: return R.drawable.ishape;
            case 1: return R.drawable.lshape;
            case 2: return R.drawable.jshape;
            case 3: return R.drawable.sshape;
            case 4: return R.drawable.tshape;
            case 5: return R.drawable.zshape;
            case 6: return R.drawable.oshape;
            default: return R.drawable.error;

        }

    }

}
