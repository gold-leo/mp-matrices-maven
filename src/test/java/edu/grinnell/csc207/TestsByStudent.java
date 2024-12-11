package edu.grinnell.csc207;

import edu.grinnell.csc207.util.MatrixV0;
import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.ADT;
import edu.grinnell.csc207.util.AssociativeArray;
import edu.grinnell.csc207.util.KVPair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TestsByStudent {
  /**
   * A simple test.
   */
  @Test
  public void alwaysPass() throws Exception {
  } // alwaysPass()

  /**
   * Tests the equal method for KVPair.
   */
  @Test
  public void equalsKVPair() throws Exception {
    KVPair<Integer, Integer> a = new KVPair<Integer, Integer>(1, 1);
    KVPair<Integer, Integer> b = new KVPair<Integer, Integer>(1, 1);
    assertTrue(a.equals(b));
  } // equalsKVPair()

  /**
   * A simple test.
   */
  @Test
  public void setAndGet() throws Exception {
    MatrixV0<String> arr = new MatrixV0<String>(3, 3);
    arr.set(1, 1, "Hello");
    assertEquals("Hello", arr.get(1, 1));
  } // alwaysPass()

  /**
   * A simple test.
   */
  @Test
  public void insertRow() throws Exception {
    MatrixV0<String> arr = new MatrixV0<String>(4, 3, "X");
    assertEquals(3, arr.height());
    arr.insertRow(0, new String[]{"H", "I", "J", "K"});
    assertEquals(4, arr.height());
    assertEquals(arr.get(0, 1), "I");
  } 

  /**
   * A simple test.
   */
  @Test
  public void insertCol() throws Exception {
    MatrixV0<String> arr = new MatrixV0<String>(3, 4, "X");
    assertEquals(3, arr.width());
    arr.insertCol(0, new String[]{"H", "I", "J", "K"});
    assertEquals(4, arr.width());
    assertEquals(arr.get(1, 0), "I");
  } 

    /**
   * A simple test.
   */
  @Test
  public void ADT() throws Exception {
    ADT a = new ADT(2);
    assertEquals(a.size(), 2);
    a.insert(2);
    assertEquals(a.size(), 3);
  } 

  // @Test
  // public void Test1() throws Exception {
  //   Integer i0 = Integer.valueOf(0);
  //   Integer i1 = Integer.valueOf(1);
  //   Integer i2 = Integer.valueOf(2);
  //   Integer i3 = Integer.valueOf(3);
  //   Integer i4 = Integer.valueOf(4);
  //   Integer i5 = Integer.valueOf(5);
  //   Matrix<Integer> horizA = new MatrixV0<Integer>(5, 1);
  //   horizA.insertCol(0);
  // }
}
