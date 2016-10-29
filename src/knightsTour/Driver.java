/**
 * 
 */
package knightsTour;

import java.util.ArrayList;

/**
 * @author grahamw0
 *
 */
public class Driver {

  /**
   * @param args
   */
  public static void main(String[] args) {
    /*long startTime = System.currentTimeMillis();
    Tour test = new Tour(10, 0, 0);
    long stopTime = System.currentTimeMillis();
    double elapsedTime = (stopTime - startTime) * 0.001;
    System.out.println(elapsedTime + " seconds");*/
    
    new TourV2(20, 5, 7);
    /*ArrayList<Boolean> list = new ArrayList<>();
    for(int i = 0; i < 500000; i++) {
      list.add(TourV2.run(10, 0, 0));
    }
    
    int falses = 0;
    for(Boolean b : list) {
      if (!b)
        falses++;
    }
    System.out.println(falses);*/
    
  }

}
