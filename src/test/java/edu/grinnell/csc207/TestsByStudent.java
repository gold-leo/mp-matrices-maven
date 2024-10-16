package edu.grinnell.csc207;

import edu.grinnell.csc207.util.MatrixV0;
import edu.grinnell.csc207.util.Matrix;
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
    assertEquals("Hello", arr.arr.get(new KVPair<Integer, Integer>(1, 1)));
    assertEquals("Hello", arr.get(1, 1));
  } // alwaysPass()
}
