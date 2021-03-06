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
/* Primitive-type-only definitions (values) */
/*		 
 * Copyright (C) 2002-2012 Sebastiano Vigna 
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
package it.unimi.dsi.fastutil.floats;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Map;
/** An abstract class providing basic methods for sorted maps implementing a type-specific interface. */
public abstract class AbstractFloat2DoubleSortedMap extends AbstractFloat2DoubleMap implements Float2DoubleSortedMap {
 public static final long serialVersionUID = -1773560792952436569L;
 protected AbstractFloat2DoubleSortedMap() {}
 /** Delegates to the corresponding type-specific method. */
 public Float2DoubleSortedMap headMap( final Float to ) {
  return headMap( ((to).floatValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Float2DoubleSortedMap tailMap( final Float from ) {
  return tailMap( ((from).floatValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Float2DoubleSortedMap subMap( final Float from, final Float to ) {
  return subMap( ((from).floatValue()), ((to).floatValue()) );
 }
 /** Delegates to the corresponding type-specific method. */
 public Float firstKey() {
  return (Float.valueOf(firstFloatKey()));
 }
 /** Delegates to the corresponding type-specific method. */
 public Float lastKey() {
  return (Float.valueOf(lastFloatKey()));
 }
 /** Returns a type-specific-sorted-set view of the keys of this map.
	 *
	 * <P>The view is backed by the sorted set returned by {@link #entrySet()}. Note that
	 * <em>no attempt is made at caching the result of this method</em>, as this would
	 * require adding some attributes that lightweight implementations would
	 * not need. Subclasses may easily override this policy by calling
	 * this method and caching the result, but implementors are encouraged to
	 * write more efficient ad-hoc implementations.
	 *
	 * @return a sorted set view of the keys of this map; it may be safely cast to a type-specific interface.
	 */
 public FloatSortedSet keySet() {
  return new KeySet();
 }
 /** A wrapper exhibiting the keys of a map. */
 protected class KeySet extends AbstractFloatSortedSet {
  public boolean contains( final float k ) { return containsKey( k ); }
  public int size() { return AbstractFloat2DoubleSortedMap.this.size(); }
  public void clear() { AbstractFloat2DoubleSortedMap.this.clear(); }
  public FloatComparator comparator() { return AbstractFloat2DoubleSortedMap.this.comparator(); }
  public float firstFloat() { return firstFloatKey(); }
  public float lastFloat() { return lastFloatKey(); }
  public FloatSortedSet headSet( final float to ) { return headMap( to ).keySet(); }
  public FloatSortedSet tailSet( final float from ) { return tailMap( from ).keySet(); }
  public FloatSortedSet subSet( final float from, final float to ) { return subMap( from, to ).keySet(); }

  public FloatBidirectionalIterator iterator( final float from ) { return new KeySetIterator ( entrySet().iterator( new BasicEntry ( from, (0) ) ) ); }
  public FloatBidirectionalIterator iterator() { return new KeySetIterator ( entrySet().iterator() ); }


 }
 /** A wrapper exhibiting a map iterator as an iterator on keys.
	 *
	 * <P>To provide an iterator on keys, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */

 protected static class KeySetIterator extends AbstractFloatBidirectionalIterator {
  protected final ObjectBidirectionalIterator<Map.Entry <Float, Double>> i;

  public KeySetIterator( ObjectBidirectionalIterator<Map.Entry <Float, Double>> i ) {
   this.i = i;
  }

  public float nextFloat() { return ((i.next().getKey()).floatValue()); };
  public float previousFloat() { return ((i.previous().getKey()).floatValue()); };

  public boolean hasNext() { return i.hasNext(); }
  public boolean hasPrevious() { return i.hasPrevious(); }
 }



 /** Returns a type-specific collection view of the values contained in this map.
	 *
	 * <P>The view is backed by the sorted set returned by {@link #entrySet()}. Note that
	 * <em>no attempt is made at caching the result of this method</em>, as this would
	 * require adding some attributes that lightweight implementations would
	 * not need. Subclasses may easily override this policy by calling
	 * this method and caching the result, but implementors are encouraged to
	 * write more efficient ad-hoc implementations.
	 *
	 * @return a type-specific collection view of the values contained in this map.
	 */

 public DoubleCollection values() {
  return new ValuesCollection();
 }

 /** A wrapper exhibiting the values of a map. */
 protected class ValuesCollection extends AbstractDoubleCollection {
  public DoubleIterator iterator() { return new ValuesIterator ( entrySet().iterator() ); }
  public boolean contains( final double k ) { return containsValue( k ); }
  public int size() { return AbstractFloat2DoubleSortedMap.this.size(); }
  public void clear() { AbstractFloat2DoubleSortedMap.this.clear(); }

 }

 /** A wrapper exhibiting a map iterator as an iterator on values.
	 *
	 * <P>To provide an iterator on values, just create an instance of this
	 * class using the corresponding iterator on entries.
	 */

 protected static class ValuesIterator extends AbstractDoubleIterator {
  protected final ObjectBidirectionalIterator<Map.Entry <Float, Double>> i;

  public ValuesIterator( ObjectBidirectionalIterator<Map.Entry <Float, Double>> i ) {
   this.i = i;
  }

  public double nextDouble() { return ((i.next().getValue()).doubleValue()); };
  public boolean hasNext() { return i.hasNext(); }
 }

 @SuppressWarnings({ "unchecked", "rawtypes" })
 public ObjectSortedSet<Map.Entry<Float, Double>> entrySet() {
  return (ObjectSortedSet)float2DoubleEntrySet();
 }
}
