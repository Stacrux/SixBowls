package com.project.mobile_application.sixbowls.Model;

import junit.framework.TestCase;

/**
 * Created by MattiaBenzoni on 08/01/2015.
 */
public class Game_boardTest extends TestCase {

    Game_board board;
    int[]b1=new int[6];
    int[]b2=new int[6];
    int t1;
    int t2;

         /*
            Test effettuati :
            - mossa normale nella propria fila di vasi
            - mossa che termina nella fila dell'avversario
            - mossa che oltrepassa il tray dell'avversario e finisce nella nostra fila
         */


    public void test_seeding_phase(){

          b1=inizbowls(3,3,3,3,3,3);
          b2=inizbowls(3,3,3,3,3,3);

          board=inizialization(b1,b2);

          int[] lastbowl=board.seeding_phase(2);
          //dopo la mossa appena compiuta mi aspetto questa situazione :
          //     3 3 3 3 3 3
          //    0           0
          //     3 3 0 4 4 4
          //verifichiamo
          int[] x=null;
          x[0]=0;
          x[1]=5;
          assertEquals(lastbowl,x);
    }

      public void test_seeding_phase1(){

       // board=inizialization();
        int[] lastbowl=board.seeding_phase(2);
        //dopo la mossa appena compiuta mi aspetto questa situazione :
        //     3 3 3 3 3 3
        //    0           0
        //     3 3 0 4 4 4
        //verifichiamo
        int[] x=null;
        x[0]=0;
        x[1]=5;
        assertEquals(lastbowl,x);
    }

    private Game_board inizialization(int[] b1, int[] b2) {

        board=new Game_board();
        return board;
    }

    private int[] inizbowls(int b1,int b2,int b3,int b4,int b5,int b6) {

        int x[]=new int[6];
        x[0]=b1; x[1]=b2; x[2]=b3; x[3]=b4; x[4]=b5; x[5]=b6;
        return x;
    }



}
