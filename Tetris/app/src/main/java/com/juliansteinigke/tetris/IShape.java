package com.juliansteinigke.tetris;

import android.content.Context;

public class IShape extends Block{

    public IShape(Context context){

//        System.out.println("______________________IShape[constructor]______________________");


        x1 = 3;
        x2 = 4;
        x3 = 5;
        x4 = 6;

        y1 = 18 + spawnLocation();
        y2 = 18 + spawnLocation();
        y3 = 18 + spawnLocation();
        y4 = 18 + spawnLocation();

        spin = 0;
        id = 0;


        graph1 = new Graphics(context,(float)MainTetrisActivity.Xgraphics[x1],(float)MainTetrisActivity.Ygraphics[y1],id);
        graph2 = new Graphics(context,(float)MainTetrisActivity.Xgraphics[x2],(float)MainTetrisActivity.Ygraphics[y2],id);
        graph3 = new Graphics(context,(float)MainTetrisActivity.Xgraphics[x2],(float)MainTetrisActivity.Ygraphics[y3],id);
        graph4 = new Graphics(context,(float)MainTetrisActivity.Xgraphics[x4],(float)MainTetrisActivity.Ygraphics[y4],id);

        pregraph1 = new Graphics(context,id);
        pregraph2 = new Graphics(context,id);
        pregraph3 = new Graphics(context,id);
        pregraph4 = new Graphics(context,id);
        blockUpdate();

//        System.out.println("SpawnLocation: " + spawnLocation());
//        System.out.println("y1: " + y1);

    }


    @Override
    public void turnRight(){
    super.turnRight();

    //Test 1
    switch (spin){

        case 0: x1++;x2++;x3++;x4++;
            break;

        case 1: y1--;y2--;y3--;y4--;
            break;

        case 2: x1--;x2--;x3--;x4--;
            break;

        case 3: y1++;y2++;y3++;y4++;
            break;
    }

        if(trySet()){

            switch (spin) {

                case 0: //0>>1
                    // Test 2
                    x1-=2;x2-=2;x3-=2;x4-=2;
                    if(trySet()){
                        //Test 3
                        x1+=3;x2+=3;x3+=3;x4+=3;
                        if(trySet()){
                            //Test 4
                            x1-=3;x2-=3;x3-=3;x4-=3;
                            y1--;y2--;x3--;
                            if(trySet()){
                                //Test 5
                                x1+=3;x2+=3;x3+=3;x4+=3;
                                y1+=3;y2+=3;y3+=3;y4+=3;
                                if(trySet()){
                                    spin--;reset();
                                }
                            }
                        }
                    }
                    break;

                case 1://1>>2
                    //Test 2
                    x1--;x2--;x3--;x4--;
                    if(trySet()){
                        //Test 3
                        x1+=3;x2+=3;x3+=3;x4+=3;
                        if(trySet()){
                            //Test 4
                            x1-=3;x2-=3;x3-=3;x4-=3;
                            y1--;y2--;x3--;
                            if(trySet()){
                                //Test 5
                                x1+=3;x2+=3;x3+=3;x4+=3;
                                y1+=3;y2+=3;y3+=3;y4+=3;
                                if(trySet()){
                                    spin--;reset();
                                }
                            }
                        }
                    }
                    break;

                case 2://2>>3
                    //Test 2
                    x1+=2;x2+=2;x3+=2;x4+=2;
                    if(trySet()){
                        //Test 3
                        x1-=3;x2-=3;x3-=3;x4-=3;
                        if(trySet()){
                            //Test 4
                            x1+=3;x2+=3;x3+=3;x4+=3;
                            y1++;y2++;x3++;
                            if(trySet()){
                                //Test 5
                                x1-=3;x2-=3;x3-=3;x4-=3;
                                y1-=3;y2-=3;y3-=3;y4-=3;
                                if(trySet()){
                                    spin--;reset();
                                }
                            }
                        }
                    }
                    break;

                case 3://3>>0
                    //Test 2
                    x1++;x2++;x3++;x4++;
                    if(trySet()){
                        //Test 3
                        x1-=3;x2-=3;x3-=3;x4-=3;
                        if(trySet()){
                            //Test 4
                            x1+=3;x2+=3;x3+=3;x4+=3;
                            y1++;y2++;x3++;y4++;
                            if(trySet()){
                                //Test 5
                                x1-=3;x2-=3;x3-=3;x4-=3;
                                y1-=3;y2-=3;y3-=3;y4-=3;
                                if(trySet()){
                                    spin--;reset();
                                }
                            }
                        }
                    }
                    break;
            }
        }
//        System.out.println("Spin: " + spin);
        if (spin < 3) {
            spin++;
        } else {
            spin=0;
        }
        blockUpdate();
    }

    @Override
    public void turnLeft(){
        super.turnLeft();
        //Test 1
        switch (spin){

            case 0: y1--;y2--;y3--;y4--;
                break;

            case 1: x1--;x2--;x3--;x4--;
                break;

            case 2: y1++;y2++;y3++;y4++;
                break;

            case 3: x1++;x2++;x3++;x4++;
                break;
        }

        if(trySet()) {

            switch (spin) {

                case 0: //0>>3
                    // Test 2
                    x1--;x2--;x3--;x4--;
                    if(trySet()){
                        //Test 3
                        x1+=3;x2+=3;x3+=3;x4+=3;
                        if(trySet()){
                            //Test 4
                            x1-=3;x2-=3;x3-=3;x4-=3;
                            y1--;y2--;x3--;
                            if(trySet()){
                                //Test 5
                                x1+=3;x2+=3;x3+=3;x4+=3;
                                y1+=3;y2+=3;y3+=3;y4+=3;
                                if(trySet()){
                                    spin++;reset();
                                }
                            }
                        }
                    }
                    break;

                case 1://1>>0
                    //Test 2
                    x1+=2;x2+=2;x3+=2;x4+=2;
                    if(trySet()){
                        //Test 3
                        x1-=3;x2-=3;x3-=3;x4-=3;
                        if(trySet()){
                            //Test 4
                            x1+=3;x2+=3;x3+=3;x4+=3;
                            y1++;y2++;x3++;
                            if(trySet()){
                                //Test 5
                                x1-=3;x2-=3;x3-=3;x4-=3;
                                y1-=3;y2-=3;y3-=3;y4-=3;
                                if(trySet()){
                                    spin++;reset();
                                }
                            }
                        }
                    }
                    break;

                case 2://2>>1
                    //Test 2

                    x1++;x2++;x3++;x4++;
                    if(trySet()){
                        //Test 3
                        x1-=3;x2-=3;x3-=3;x4-=3;
                        if(trySet()){
                            //Test 4
                            x1+=3;x2+=3;x3+=3;x4+=3;
                            y1++;y2++;x3++;y4++;
                            if(trySet()){
                                //Test 5
                                x1-=3;x2-=3;x3-=3;x4-=3;
                                y1-=3;y2-=3;y3-=3;y4-=3;
                                if(trySet()){
                                    spin++;reset();
                                }
                            }
                        }
                    }
                    break;

                case 3://3>>2
                    // Test 2
                    x1-=2;x2-=2;x3-=2;x4-=2;
                    if(trySet()){
                        //Test 3
                        x1+=3;x2+=3;x3+=3;x4+=3;
                        if(trySet()){
                            //Test 4
                            x1-=3;x2-=3;x3-=3;x4-=3;
                            y1--;y2--;x3--;
                            if(trySet()){
                                //Test 5
                                x1+=3;x2+=3;x3+=3;x4+=3;
                                y1+=3;y2+=3;y3+=3;y4+=3;
                                if(trySet()){
                                    spin++;reset();
                                }
                            }
                        }
                    }
                    break;
            }
        }

        if (spin > 0) {
            spin--;
        } else {
            spin=3;
        }
        blockUpdate();

    }



}
