/* Generic definitions */




/* Assertions (useful to generate conditional code) */
/* Current type and class (and size, if applicable) */
/* Value methods */
/* Interfaces (keys) */
/* Interfaces (values) */
/* Abstract implementations (keys) */
/* Abstract implementations (values) */
/* Static containers (keys) */
/* Static containers (values) */
/* Implementations */
/* Synchronized wrappers */
/* Unmodifiable wrappers */
/* Other wrappers */
/* Methods (keys) */
/* Methods (values) */
/* Methods (keys/values) */
/* Methods that have special names depending on keys (but the special names depend on values) */
/* Equality */
/* Object/Reference-only definitions (keys) */
/* Primitive-type-only definitions (keys) */
/* Object/Reference-only definitions (values) */
/*		 
 * Copyright (C) 2003-2012 Paolo Boldi and Sebastiano Vigna 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package it.unimi.dsi.fastutil.ints;
import it.unimi.dsi.fastutil.ints.IntArrays;
/** A class providing static methods and objects that do useful things with indirect heaps.
 *
 * <P>An indirect heap is an extension of a semi-indirect heap using also an
 * <em>inversion array</em> of the same length as the reference array,
 * satisfying the relation <code>heap[inv[i]]==i</code> when
 * <code>inv[i]>=0</code>, and <code>inv[heap[i]]==i</code> for all elements in the heap.
 */
public class IntIndirectHeaps {
 private IntIndirectHeaps() {}
 /** Moves the given element down into the indirect heap until it reaches the lowest possible position.
	 *
	 * @param refArray the reference array.
	 * @param heap the indirect heap (starting at 0).
	 * @param inv the inversion array.
	 * @param size the number of elements in the heap.
	 * @param i the index in the heap of the element to be moved down.
	 * @param c a type-specific comparator, or <code>null</code> for the natural order.
	 * @return the new position in the heap of the element of heap index <code>i</code>.
	 */
 @SuppressWarnings("unchecked")
 public static int downHeap( final int[] refArray, final int[] heap, final int[] inv, final int size, int i, final IntComparator c ) {
  if ( i >= size ) throw new IllegalArgumentException( "Heap position (" + i + ") is larger than or equal to heap size (" + size + ")" );
  final int e = heap[ i ];
  final int E = refArray[ e ];
  int child;
  if ( c == null )
   while ( ( child = 2 * i + 1 ) < size ) {
    if ( child + 1 < size && ( (refArray[ heap[ child + 1 ] ]) < (refArray[ heap[ child ] ]) ) ) child++;
    if ( ( (E) <= (refArray[ heap[ child ] ]) ) ) break;
    heap[ i ] = heap[ child ];
    inv[ heap[ i ] ] = i;
    i = child;
   }
  else
   while ( ( child = 2 * i + 1 ) < size ) {
    if ( child + 1 < size && c.compare( refArray[ heap[ child + 1 ] ], refArray[ heap[ child ] ] ) < 0 ) child++;
    if ( c.compare( E, refArray[ heap[ child ] ] ) <= 0 ) break;
    heap[ i ] = heap[ child ];
    inv[ heap[ i ] ] = i;
    i = child;
   }
  heap[ i ] = e;
  inv[ e ] = i;
  return i;
 }
 /** Moves the given element up in the indirect heap until it reaches the highest possible position.
	 *
	 * Note that in principle after this call the heap property may be violated.
	 * 
	 * @param refArray the reference array.
	 * @param heap the indirect heap (starting at 0).
	 * @param inv the inversion array.
	 * @param size the number of elements in the heap.
	 * @param i the index in the heap of the element to be moved up.
	 * @param c a type-specific comparator, or <code>null</code> for the natural order.
	 * @return the new position in the heap of the element of heap index <code>i</code>.
	 */
 @SuppressWarnings("unchecked")
 public static int upHeap( final int[] refArray, final int[] heap, final int[] inv, final int size, int i, final IntComparator c ) {
  if ( i >= size ) throw new IllegalArgumentException( "Heap position (" + i + ") is larger than or equal to heap size (" + size + ")" );
  final int e = heap[ i ];
  final int E = refArray[ e ];
  int parent;
  if ( c == null )
   while ( i != 0 && ( parent = ( i - 1 ) / 2 ) >= 0 ) {
    if ( ( (refArray[ heap[ parent ] ]) <= (E) ) ) break;
    heap[ i ] = heap[ parent ];
    inv[ heap[ i ] ] = i;
    i = parent;
   }
  else
   while ( i != 0 && ( parent = ( i - 1 ) / 2 ) >= 0 ) {
    if ( c.compare( refArray[ heap[ parent ] ], E ) <= 0 ) break;
    heap[ i ] = heap[ parent ];
    inv[ heap[ i ] ] = i;
    i = parent;
   }
  heap[ i ] = e;
  inv[ e ] = i;
  return i;
 }
 /** Creates an indirect heap in the given array.
	 *
	 * @param refArray the reference array.
	 * @param offset the first element of the reference array to be put in the heap.
	 * @param length the number of elements to be put in the heap.
	 * @param heap the array where the heap is to be created.
	 * @param inv the inversion array.
	 * @param c a type-specific comparator, or <code>null</code> for the natural order.
	 */
 public static void makeHeap( final int[] refArray, final int offset, final int length, final int[] heap, final int[] inv, final IntComparator c ) {
  IntArrays.ensureOffsetLength( refArray, offset, length );
  if ( heap.length < length ) throw new IllegalArgumentException( "The heap length (" + heap.length + ") is smaller than the number of elements (" + length + ")" );
  if ( inv.length < refArray.length ) throw new IllegalArgumentException( "The inversion array length (" + heap.length + ") is smaller than the length of the reference array (" + refArray.length + ")" );
  IntArrays.fill( inv, 0, refArray.length, -1 );
  int i = length;
  while( i-- != 0 ) inv[ heap[ i ] = offset + i ] = i;
  i = length / 2;
  while( i-- != 0 ) downHeap( refArray, heap, inv, length, i, c );
 }


 /** Creates an indirect heap from a given index array.
	 *
	 * @param refArray the reference array.
	 * @param heap an array containing indices into <code>refArray</code>.
	 * @param inv the inversion array.
	 * @param size the number of elements in the heap.
	 * @param c a type-specific comparator, or <code>null</code> for the natural order.
	 */

 public static void makeHeap( final int[] refArray, final int[] heap, final int[] inv, final int size, final IntComparator c ) {
  int i = size / 2;
  while( i-- != 0 ) downHeap( refArray, heap, inv, size, i, c );
 }
}
