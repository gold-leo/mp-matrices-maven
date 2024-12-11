package edu.grinnell.csc207.util;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.security.KeyPair;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Leo Goldman
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The array - internal.
   */
  public AssociativeArray<Integer[], T> arr = new AssociativeArray<Integer[], T>();

  /**
   * The default value.
   */
  private T def;

  /**
   * The width array.
   */
  public ADT width;

  /**
   * The height array.
   */
  public ADT height;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    // Create the array for the width.
    this.width = new ADT(width);

    // Create the array for the height.
    this.height = new ADT(height);

    // Set the default value.
    this.def = def;
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    if (row < 0 || row >= this.height()) {
      throw new IndexOutOfBoundsException(col);
    } // Row preconditions
    if (col < 0 || col >= this.width()) {
      throw new IndexOutOfBoundsException(row);
    } // Col preconditions
    try {
      T val = arr.get(new Integer[]{this.row(row), this.col(col)});
      return val;
    } catch (KeyNotFoundException e) {
      return this.def;
    } // Try to get the value from the index.
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (row < 0 || row >= this.height()) {
      throw new IndexOutOfBoundsException(col);
    } // Row preconditions
    if (col < 0 || col >= this.width()) {
      throw new IndexOutOfBoundsException(row);
    } // Col preconditions
    try {
      arr.set(new Integer[]{this.row(row), this.col(col)}, val);
    } catch (Exception e) {
      // Serious issue if this is ever entered.
    } // Set the value to the index.
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.height.size();
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.width.size();
  } // width()

  /**
   * Returns the value used in AA to specify the correct row.
   * @param i
   *    The real row index.
   * @return
   *    The AA row index.
   */
  private int row(int i) {
    return height.get(i);
  } // row

  /**
   * Returns the value used in AA to specify the correct col.
   * @param i
   *    The real col index.
   * @return
   *    The AA col index.
   */
  private int col(int i) {
    return width.get(i);
  } // row

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if (row > this.height()) {
      throw new IndexOutOfBoundsException(row);
    } // if

    // Insert the row.
    try {
      height.insert(row);
    } catch (Exception e) {
      throw new IndexOutOfBoundsException();
    }

    // Add to the AA.
    for (int i = 0; i < width(); i++) {
      set(row, i, def);
    } // for
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (row > this.height()) {
      throw new IndexOutOfBoundsException(row);
    } // if
    if (vals.length != width()) {
      throw new ArraySizeException();
    } // if

    // Insert in the row ADT.
    try {
      height.insert(row);
    } catch (Exception e) {
      throw new IndexOutOfBoundsException();
    } // try
    
    // Add to the AA.
    for (int i = 0; i < vals.length; i++) {
      set(row, i, vals[i]);
    } // for
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if (col > this.width()) {
      throw new IndexOutOfBoundsException(col);
    } // if

    // Insert the row.
    try {
      width.insert(col);
    } catch (Exception e) {
      throw new IndexOutOfBoundsException();
    }

    // Add to the AA.
    for (int i = 0; i < height(); i++) {
      set(i, col, def);
    } // for
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (col > this.width()) {
      throw new IndexOutOfBoundsException(col);
    } // if
    if (vals.length != height()) {
      throw new ArraySizeException();
    } // if

    // Insert in the row ADT.
    try {
      width.insert(col);
    } catch (Exception e) {
      throw new IndexOutOfBoundsException();
    } // try
    
    // Add to the AA.
    for (int i = 0; i < vals.length; i++) {
      set(i, col, vals[i]);
    } // for
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if (row >= this.height() || row < 0) {
      throw new IndexOutOfBoundsException();
    } // if

    for (int i = 0; i < width(); i++) {
      arr.remove(new Integer[]{row(row), col(i)});
    } // for

    try {
      this.height.remove(row);
    } catch (Exception e) {
      // This is very bad if happens!!
    } // try/catch
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if (col >= this.width() || col < 0) {
      throw new IndexOutOfBoundsException();
    } // if

    for (int i = 0; i < height(); i++) {
      arr.remove(new Integer[]{row(i), col(col)});
    } // for


    try {
      this.width.remove(col);
    } catch (Exception e) {
      throw new IndexOutOfBoundsException("This is very bad " + col + width());
    } // try/catch
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
    T val) {
      if (startRow < 0 || startRow >= height() || startCol < 0 || startCol >= width() ||
      endCol < startCol || endCol > width() || endRow < startRow || endRow > height()) {
        throw new IndexOutOfBoundsException();
    } // if

    for (int i = startRow; i < endRow; i++) {
      for (int n = startCol; n < endCol; n++) {
        try {
          set(i, n, val);
        } catch (Exception e) {
          throw new IndexOutOfBoundsException(i + " " + n + " | " + endCol);
        }
        
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    if (startRow < 0 || startRow >= height() || startCol < 0 || startCol >= width() ||
      endCol < startCol || endCol > width() || endRow < startRow || endRow > height()) {
        throw new IndexOutOfBoundsException();
    } // if

    for (; startRow < endRow && startCol < endCol;) {
      set(startRow, startCol, val);
      startRow += deltaRow;
      startCol += deltaCol;
    } // for
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    MatrixV0<T> clone = new MatrixV0<T>(width(), height(), def);

    // We do some evil
    clone.arr = arr.clone();
    clone.height = height.clone();
    clone.width = width.clone();
    // This is evil. Don't do it unless for some reason you 
    // decided to use AAs for this MP like an idiot
    // and then chose to overcomplicate everything even further

    return clone;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    return (other instanceof MatrixV0) && (other.hashCode() == this.hashCode());
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
