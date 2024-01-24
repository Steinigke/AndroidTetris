package com.juliansteinigke.tetris;



public class Block {

    protected int x1,x2,x3,x4;
    protected int y1,y2,y3,y4;
    protected int dx1,dx2,dx3,dx4;
    protected int dy1,dy2,dy3,dy4;

    protected int px1,px2,px3,px4;
    protected int py1,py2,py3,py4;
    protected int id;
    protected int spin;

   Graphics graph1;
   Graphics graph2;
   Graphics graph3;
   Graphics graph4;

   Graphics pregraph1;
   Graphics pregraph2;
   Graphics pregraph3;
   Graphics pregraph4;

    public void setdx(){

        dx1 = x1;
        dx2 = x2;
        dx3 = x3;
        dx4 = x4;

    }

    public void setdy(){

        dy1 = y1;
        dy2 = y2;
        dy3 = y3;
        dy4 = y4;

    }

    public boolean down(){

        setdy();
        setdx();

        y1--;
        y2--;
        y3--;
        y4--;

        if(trySet()){

            y1 = dy1;
            y2 = dy2;
            y3 = dy3;
            y4 = dy4;

            MainTetrisActivity.con[x1][y1] = true;
            MainTetrisActivity.con[x2][y2] = true;
            MainTetrisActivity.con[x3][y3] = true;
            MainTetrisActivity.con[x4][y4] = true;

            return true;

        }else {

            blockUpdate();

            return false;
        }

    }

    public void right(){

        setdx();
        setdy();

        x1++;
        x2++;
        x3++;
        x4++;

        reset();
        blockUpdate();
    }

    public void left(){

        setdx();
        setdy();

        x1--;
        x2--;
        x3--;
        x4--;

        reset();
        blockUpdate();

    }

    public void turnRight(){

        setdy();
        setdx();

        x1 = dy1 - dy2 + dx2;
        y1 = -(dx1 - dx2) + dy2;

        x3 = dy3 - dy2 + dx2;
        y3 = -(dx3 - dx2) + dy2;

        x4 = dy4 - dy2 + dx2;
        y4 = -(dx4 - dx2) + dy2;

    }

    public void turnLeft(){

        setdy();
        setdx();

        x1 = -(dy1 - dy2) + dx2;
        y1 = dx1 - dx2 + dy2;

        x3 = -(dy3 - dy2) + dx2;
        y3 = dx3 - dx2 + dy2;

        x4 = -(dy4 - dy2) + dx2;
        y4 = dx4 - dx2 + dy2;
    }

    public int harddrop(){
        int l = 0;
        while(!down()){
            l++;
        }
        return l;
    }

    public void blockUpdate(){

        graph1.setX((float)MainTetrisActivity.Xgraphics[x1]);
        graph2.setX((float)MainTetrisActivity.Xgraphics[x2]);
        graph3.setX((float)MainTetrisActivity.Xgraphics[x3]);
        graph4.setX((float)MainTetrisActivity.Xgraphics[x4]);

        graph1.setY((float)MainTetrisActivity.Ygraphics[y1]);
        graph2.setY((float)MainTetrisActivity.Ygraphics[y2]);
        graph3.setY((float)MainTetrisActivity.Ygraphics[y3]);
        graph4.setY((float)MainTetrisActivity.Ygraphics[y4]);

        setdx();
        setdy();

        do{

            y1--;
            y2--;
            y3--;
            y4--;

        }while(!trySet());

        y1++;
        y2++;
        y3++;
        y4++;

        pregraph1.setX((float)MainTetrisActivity.Xgraphics[x1]);
        pregraph2.setX((float)MainTetrisActivity.Xgraphics[x2]);
        pregraph3.setX((float)MainTetrisActivity.Xgraphics[x3]);
        pregraph4.setX((float)MainTetrisActivity.Xgraphics[x4]);

        pregraph1.setY((float)MainTetrisActivity.Ygraphics[y1]);
        pregraph2.setY((float)MainTetrisActivity.Ygraphics[y2]);
        pregraph3.setY((float)MainTetrisActivity.Ygraphics[y3]);
        pregraph4.setY((float)MainTetrisActivity.Ygraphics[y4]);

        y1 = dy1;
        y2 = dy2;
        y3 = dy3;
        y4 = dy4;

    }

    public int spawnLocation(){

        for(int h = 1; h<3;h++) {

            for(int l = 0; l < 10; l++) {

                if (MainTetrisActivity.con[l][16 + h]) {

                    return h;

                }


            }
        }
        return 0;

    }

    public boolean trySet() {

        try {


            if (MainTetrisActivity.con[x1][y1] || MainTetrisActivity.con[x2][y2] || MainTetrisActivity.con[x3][y3] || MainTetrisActivity.con[x4][y4]) {

                return true;
            }else {

                return false;
            }
        }catch(java.lang.ArrayIndexOutOfBoundsException e){
//            System.out.println("catch = true");
            return true;

        }

    }

    public void reset(){

        if(trySet()){

            x1 = dx1;
            x2 = dx2;
            x3 = dx3;
            x4 = dx4;

            y1 = dy1;
            y2 = dy2;
            y3 = dy3;
            y4 = dy4;

        }

    }
}



























