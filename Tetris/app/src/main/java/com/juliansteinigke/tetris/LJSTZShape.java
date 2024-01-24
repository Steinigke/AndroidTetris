package com.juliansteinigke.tetris;

import android.content.Context;

public class LJSTZShape extends Block {




    public LJSTZShape(Context context,int id){

//        System.out.println("______________________LShape[Constructor]______________________");

        switch(id) {

            case 1://LShape
                x1 = 3;
                x2 = 4;
                x3 = 5;
                x4 = 5;

                y1 = 18 + spawnLocation();
                y2 = 18 + spawnLocation();
                y3 = 18 + spawnLocation();
                y4 = 19 + spawnLocation();
                break;

            case 2://JShape
                x1 = 3;
                x2 = 4;
                x3 = 5;
                x4 = 3;

                y1 = 18 + spawnLocation();
                y2 = 18 + spawnLocation();
                y3 = 18 + spawnLocation();
                y4 = 19 + spawnLocation();
                break;

            case 3://SShape
                x1 = 3;
                x2 = 4;
                x3 = 4;
                x4 = 5;

                y1 = 18 + spawnLocation();
                y2 = 18 + spawnLocation();
                y3 = 19 + spawnLocation();
                y4 = 19 + spawnLocation();
                break;

            case 4://TShape
                x1 = 3;
                x2 = 4;
                x3 = 5;
                x4 = 4;

                y1 = 18 + spawnLocation();
                y2 = 18 + spawnLocation();
                y3 = 18 + spawnLocation();
                y4 = 19 + spawnLocation();
                break;

            case 5://ZShape
                x1 = 4;
                x2 = 4;
                x3 = 5;
                x4 = 3;

                y1 = 19 + spawnLocation();
                y2 = 18 + spawnLocation();
                y3 = 18 + spawnLocation();
                y4 = 19 + spawnLocation();
                break;
        }

        spin = 0;
        this.id = id;

//        graph1 = new Graphics(context,(float)MainTetrisActivity.Xgraphics[x1],(float)MainTetrisActivity.Ygraphics[y1],id);
        graph1 = new Graphics(context,(float)MainTetrisActivity.Xgraphics[x1],(float)MainTetrisActivity.Ygraphics[1],id);
        graph2 = new Graphics(context,(float)MainTetrisActivity.Xgraphics[x2],(float)MainTetrisActivity.Ygraphics[y2],id);
        graph3 = new Graphics(context,(float)MainTetrisActivity.Xgraphics[x2],(float)MainTetrisActivity.Ygraphics[y3],id);
        graph4 = new Graphics(context,(float)MainTetrisActivity.Xgraphics[x4],(float)MainTetrisActivity.Ygraphics[y4],id);

        pregraph1 = new Graphics(context,id);
        pregraph2 = new Graphics(context,id);
        pregraph3 = new Graphics(context,id);
        pregraph4 = new Graphics(context,id);
        blockUpdate();

    }

    @Override
    public void turnRight(){

        //Test 1
        super.turnRight();

        if(trySet()){

            switch (spin) {

                case 0://0>>1
                    //Test 2
                    x1--; x2--; x3--; x4--;
                    if(trySet()){
                        //Test 3
                        y1++;y2++;y3++;y4++;

                        if(trySet()){
                            //Test 4
                            x1++; x2++; x3++; x4++;
                            y1 -= 3;y2 -= 3;y3 -= 3;y4 -= 3;

                            if(trySet()){
                                //Test 5
                                x1--; x2--; x3--; x4--;
                                if(trySet()){
                                    spin--; reset();
                                }
                            }
                        }
                    }
                    break;

                case 1://1>>2
                    //Test 2
                    x1++; x2++; x3++; x4++;
                    if(trySet()){
                        //Test 3
                        y1--;y2--;y3--;y4--;
                        if(trySet()){
                            //Test 4
                            x1--; x2--; x3--; x4--;
                            y1 += 3;y2 += 3;y3 += 3;y4 += 3;
                            if(trySet()){
                                //Test 5
                                x1++; x2++; x3++; x4++;
                                if(trySet()){
                                    spin--; reset();
                                }
                            }
                        }
                    }
                    break;

                case 2://2>>3
                    //Test 2
                    x1++; x2++; x3++; x4++;
                    if(trySet()){
                        //Test 3
                        y1++;y2++;y3++;y4++;
                        if(trySet()){
                            //Test 4
                            x1--; x2--; x3--; x4--;
                            y1 -= 3;y2 -= 3;y3 -= 3;y4 -= 3;
                            if(trySet()){
                                //Test 5
                                x1++; x2++; x3++; x4++;
                                if(trySet()){
                                    spin--; reset();
                                }
                            }
                        }
                    }
                    break;

                case 3://3>>0
                    //Test 2
                    x1--; x2--; x3--; x4--;
                    if(trySet()){
                        //Test 3
                        y1--;y2--;y3--;y4--;
                        if(trySet()){
                            //Test 4
                            x1++; x2++; x3++; x4++;
                            y1 += 3;y2 += 3;y3 += 3;y4 += 3;
                            if(trySet()){
                                //Test 5
                                x1--; x2--; x3--; x4--;
                                if(trySet()){
                                    spin--; reset();
                                }
                            }
                        }
                    }
                    break;
            }
        }

        if (spin == 3) {
            spin = 0;
        } else {
            spin++;
        }
        blockUpdate();
    }//End of RightTurn

     @Override
    public void turnLeft(){

         if(trySet()) {

             switch (spin) {

                 case 0: //0>>3
                     // Test 2
                     x1++; x2++; x3++; x4++;
                     if(trySet()){
                         //Test 3
                         y1++;y2++;y3++;y4++;
                         if(trySet()){
                             //Test 4
                             x1--; x2--; x3--; x4--;
                             y1 -= 3;y2 -= 3;y3 -= 3;y4 -= 3;
                             if(trySet()){
                                 //Test 5
                                 x1++; x2++; x3++; x4++;
                                 if(trySet()){
                                     spin++; reset();
                                 }
                             }
                         }
                     }
                     break;

                 case 1://1>>0
                     //Test 2

                     x1++; x2++; x3++; x4++;
                     if(trySet()){
                         //Test 3
                         y1--;y2--;y3--;y4--;
                         if(trySet()){
                             //Test 4
                             x1--; x2--; x3--; x4--;
                             y1 += 3;y2 += 3;y3 += 3;y4 += 3;
                             if(trySet()){
                                 //Test 5
                                 x1++; x2++; x3++; x4++;
                                 if(trySet()){
                                     spin++; reset();
                                 }
                             }
                         }
                     }
                     break;

                 case 2://2>>1
                     //Test 2
                     x1--; x2--; x3--; x4--;
                     if(trySet()){
                         //Test 3
                         y1++;y2++;y3++;y4++;

                         if(trySet()){
                             //Test 4
                             x1++; x2++; x3++; x4++;
                             y1 -= 3;y2 -= 3;y3 -= 3;y4 -= 3;

                             if(trySet()){
                                 //Test 5
                                 x1--; x2--; x3--; x4--;
                                 if(trySet()){
                                     spin++; reset();
                                 }
                             }
                         }
                     }
                     break;

                 case 3://3>>2
                     //Test 2
                     x1--; x2--; x3--; x4--;
                     if(trySet()){
                         //Test 3
                         y1--;y2--;y3--;y4--;
                         if(trySet()){
                             //Test 4
                             x1++; x2++; x3++; x4++;
                             y1 += 3;y2 += 3;y3 += 3;y4 += 3;
                             if(trySet()){
                                 //Test 5
                                 x1--; x2--; x3--; x4--;
                                 if(trySet()){
                                     spin++; reset();
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
