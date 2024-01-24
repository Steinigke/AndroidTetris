package com.juliansteinigke.tetris;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.widget.ImageView;

//androidx.appcompat.widget.AppCompatImageView
@SuppressLint("ViewConstructor")
public class Graphics extends androidx.appcompat.widget.AppCompatImageView {

    static int scale;

    public Graphics(Context context,int id){
        super(context);


        switch (id) {

            case 0://IShape
                this.setImageResource(R.drawable.lightblueoutline);
                break;

            case 1://LShape
                this.setImageResource(R.drawable.orangeoutline);
                break;

            case 2://JShape
                this.setImageResource(R.drawable.darkblueoutline);
                break;

            case 3://SShape
                this.setImageResource(R.drawable.greenoutline);
                break;

            case 4://TShape
                this.setImageResource(R.drawable.purpeloutline);
                break;

            case 5://ZShape
                this.setImageResource(R.drawable.redoutline);
                break;

            case 6://OShape
                this.setImageResource(R.drawable.yellowoutline);
                break;


            default: this.setImageResource(R.drawable.error);
        }

        this.setAdjustViewBounds(true);
        this.setMaxHeight(scale);

    }

    public Graphics(Context context,float x,float y,int id) {
        super(context);

//        System.out.println("____________________________Graphics________________________________");

        this.setX(x);
        this.setY(y);

        switch (id) {

            case 0://IShape
                this.setImageResource(R.drawable.lightblueblock);
                break;

            case 1://LShape
                this.setImageResource(R.drawable.orang);
                break;

            case 2://JShape
                this.setImageResource(R.drawable.darkblueblock);
                break;

            case 3://SShape
                this.setImageResource(R.drawable.greenblock);
                break;

            case 4://TShape
                this.setImageResource(R.drawable.purpelblock);
                break;

            case 5://ZShape
                this.setImageResource(R.drawable.redblock);
                break;

            case 6://OShape
                this.setImageResource(R.drawable.yellowlblock);
                break;


            default: this.setImageResource(R.drawable.error);
        }

        this.setAdjustViewBounds(true);
        this.setMaxHeight(scale);


    }

}
