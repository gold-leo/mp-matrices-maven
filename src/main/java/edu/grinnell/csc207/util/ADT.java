package edu.grinnell.csc207.util;

/**
 * Basic ADT for the MatrixV0 to make AAs faster.
 *
 * The design decision used is that it is less likely for
 * an insert to occur than a get(). O(n) sucks, but O(logn)
 * four times is probably worse most of the time. At least, for
 * this use case.
 *
 * @author Leo Goldman
 */
public class ADT {
  /**
   * The array.
   */
  private int[] arr;

  /**
   * The size of the ADT.
   */
  int size;

  /**
   * A new UID.
   */
  int uid;

  /**
   * Construct an ADT.
   * @param s
   *    Size of the ADT.
   */
  public ADT(int s) {
    this.size = s;
    this.uid = s;
    arr = new int[(int) Math.pow(2, s)];
    for (int i = 0; i < s; i++) {
      arr[i] = i;
    } // for
  } // Constriuctor

  /**
   * Get the size of the ADT.
   * @return
   *    The size
   */
  public int size() {
    return size;
  } // size()

  /**
   * Get a value from index i.
   * @param i
   *    The index
   * @return
   *    The value
   */
  public int get(int i) {
    return arr[i];
  } // get()

  /**
   * Insert value v into the ADT.
   * @param i
   *    The index to insert. Starts at 0!
   * @throws Exception
   */
  public void insert(int i) throws Exception {
    // Check if possible
    if (i > this.size || i < 0) {
      throw new Exception("Inserting a row/col that cannot exist");
    } // if

    // Increase the size.
    this.size++;
    this.uid++;


    // Check if the size is getting close to the limit.
    if (this.size == this.arr.length) {
      expand();
    } // if

    // Iterate through, setting each item to the one above.
    // Ends at i.
    for (int n = this.size; n > i; n--) {
      arr[n] = arr[n - 1];
    } // for

    // Set v to the ith element.
    arr[i] = this.uid;
  } // insert()

  /**
   * Remove an element.
   * @param i
   *    The index.
   * @throws Exception
   */
  public void remove(int i) throws Exception {
    if (i >= this.size || i < 0) {
      throw new Exception("Removing a row/col that does not exist");
    } // if

    for (int n = i; n < this.size; n++) {
      arr[n] = arr[n + 1];
    } // for

    this.size--;
  } // remove()

  /**
   * Clone the ADT.
   *
   * @return ADT Clone
   */
  public ADT clone() {
    ADT clone = new ADT(size);

    clone.setup(this.arr.clone());

    return clone;
  } // clone()

  /**
   * Easy setup for cloning.
   * @param a
   *    The array.
   */
  public void setup(int[] a) {
    this.arr = a;
  } // setup

  /**
   * Expand the underlying array.
   */
  void expand() {
    this.arr = java.util.Arrays.copyOf(this.arr, this.arr.length * 2);
  } // expand()
} // ADT
