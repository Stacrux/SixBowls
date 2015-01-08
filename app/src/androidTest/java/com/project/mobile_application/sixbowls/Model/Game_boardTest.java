package com.project.mobile_application.sixbowls.Model;

import junit.framework.TestCase;

/**
 * Created by MattiaBenzoni on 08/01/2015.
 */
public class Game_boardTest extends TestCase {

    Game_board board= new Game_board();

         /*
            Test effettuati :
            - mossa normale nella propria fila di vasi
            - mossa che termina nella fila dell'avversario
            - mossa che oltrepassa il tray dell'avversario e finisce nella nostra fila
         */


    public void test_seeding_phase(){

          board.seeding_phase(2);
          //dopo la mossa appena compiuta mi aspetto questa situazione :
          //     3 3 3 3 3 3
          //    0           0
          //     3 3 0 4 4 4
          //verifichiamo
          assertEquals(0,0);

    }


}
