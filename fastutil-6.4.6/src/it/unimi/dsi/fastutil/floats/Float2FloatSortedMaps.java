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
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.NoSuchElementException;
/** A class providing static methods and objects that do useful things with type-specific sorted maps.
 *
 * @see java.util.Collections
 */
public class Float2FloatSortedMaps {
 private Float2FloatSortedMaps() {}
 /** Returns a comparator for entries based on a given comparator on keys.
	 *
	 * @param comparator a comparator on keys.
	 * @return the associated comparator on entries.
	 */
 public static Comparator<? super Map.Entry<Float, ?>> entryComparator( final FloatComparator comparator ) {
  return new Comparator<Map.Entry<Float, ?>>() {
   public int compare( Map.Entry<Float, ?> x, Map.Entry<Float, ?> y ) {
    return comparator.compare( x.getKey(), y.getKey() );
   }
  };
 }
 /** An immutable class representing an empty type-specific sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
 public static class EmptySortedMap extends Float2FloatMaps.EmptyMap implements Float2FloatSortedMap , java.io.Serializable, Cloneable {
  public static final long serialVersionUID = -7046029254386353129L;
  protected EmptySortedMap() {}
  public FloatComparator comparator() { return null; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Float2FloatMap.Entry > float2FloatEntrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Map.Entry<Float, Float>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public FloatSortedSet keySet() { return FloatSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public Float2FloatSortedMap subMap( final float from, final float to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Float2FloatSortedMap headMap( final float to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Float2FloatSortedMap tailMap( final float from ) { return EMPTY_MAP; }
  public float firstFloatKey() { throw new NoSuchElementException(); }
  public float lastFloatKey() { throw new NoSuchElementException(); }
  public Float2FloatSortedMap headMap( Float oto ) { return headMap( ((oto).floatValue()) ); }
  public Float2FloatSortedMap tailMap( Float ofrom ) { return tailMap( ((ofrom).floatValue()) ); }
  public Float2FloatSortedMap subMap( Float ofrom, Float oto ) { return subMap( ((ofrom).floatValue()), ((oto).floatValue()) ); }
  public Float firstKey() { return (Float.valueOf(firstFloatKey())); }
  public Float lastKey() { return (Float.valueOf(lastFloatKey())); }
 }

 /** An empty type-specific sorted map (immutable). It is serializable and cloneable. */

 @SuppressWarnings("rawtypes")
 public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();


 /** An immutable class representing a type-specific singleton sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */

 public static class Singleton extends Float2FloatMaps.Singleton implements Float2FloatSortedMap , java.io.Serializable, Cloneable {

  public static final long serialVersionUID = -7046029254386353129L;

  protected final FloatComparator comparator;

  protected Singleton( final float key, final float value, FloatComparator comparator ) {
   super( key, value );
   this.comparator = comparator;
  }

  protected Singleton( final float key, final float value ) {
   this( key, value, null );
  }

  @SuppressWarnings("unchecked")
  final int compare( final float k1, final float k2 ) {
   return comparator == null ? ( (k1) < (k2) ? -1 : ( (k1) == (k2) ? 0 : 1 ) ) : comparator.compare( k1, k2 );
  }

  public FloatComparator comparator() { return comparator; }

  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Float2FloatMap.Entry > float2FloatEntrySet() { if ( entries == null ) entries = ObjectSortedSets.singleton( (Float2FloatMap.Entry )new SingletonEntry(), (Comparator<? super Float2FloatMap.Entry >)entryComparator( comparator ) ); return (ObjectSortedSet<Float2FloatMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Float, Float>> entrySet() { return (ObjectSortedSet)float2FloatEntrySet(); }

  public FloatSortedSet keySet() { if ( keys == null ) keys = FloatSortedSets.singleton( key, comparator ); return (FloatSortedSet )keys; }

  @SuppressWarnings("unchecked")
  public Float2FloatSortedMap subMap( final float from, final float to ) { if ( compare( from, key ) <= 0 && compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Float2FloatSortedMap headMap( final float to ) { if ( compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Float2FloatSortedMap tailMap( final float from ) { if ( compare( from, key ) <= 0 ) return this; return EMPTY_MAP; }

  public float firstFloatKey() { return key; }
  public float lastFloatKey() { return key; }


  public Float2FloatSortedMap headMap( Float oto ) { return headMap( ((oto).floatValue()) ); }
  public Float2FloatSortedMap tailMap( Float ofrom ) { return tailMap( ((ofrom).floatValue()) ); }
  public Float2FloatSortedMap subMap( Float ofrom, Float oto ) { return subMap( ((ofrom).floatValue()), ((oto).floatValue()) ); }

  public Float firstKey() { return (Float.valueOf(firstFloatKey())); }
  public Float lastKey() { return (Float.valueOf(lastFloatKey())); }

 }

 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Float2FloatSortedMap singleton( final Float key, Float value ) {
  return new Singleton ( ((key).floatValue()), ((value).floatValue()) );
 }

 /** RETURNS a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @param comparator the comparator to use in the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Float2FloatSortedMap singleton( final Float key, Float value, FloatComparator comparator ) {
  return new Singleton ( ((key).floatValue()), ((value).floatValue()), comparator );
 }



 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Float2FloatSortedMap singleton( final float key, final float value ) {
  return new Singleton ( key, value );
 }

 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @param comparator the comparator to use in the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Float2FloatSortedMap singleton( final float key, final float value, FloatComparator comparator ) {
  return new Singleton ( key, value, comparator );
 }




  /** A synchronized wrapper class for sorted maps. */

 public static class SynchronizedSortedMap extends Float2FloatMaps.SynchronizedMap implements Float2FloatSortedMap , java.io.Serializable {

  public static final long serialVersionUID = -7046029254386353129L;

  protected final Float2FloatSortedMap sortedMap;

  protected SynchronizedSortedMap( final Float2FloatSortedMap m, final Object sync ) {
   super( m, sync );
   sortedMap = m;
  }

  protected SynchronizedSortedMap( final Float2FloatSortedMap m ) {
   super( m );
   sortedMap = m;
  }

  public FloatComparator comparator() { synchronized( sync ) { return sortedMap.comparator(); } }

  public ObjectSortedSet<Float2FloatMap.Entry > float2FloatEntrySet() { if ( entries == null ) entries = ObjectSortedSets.synchronize( sortedMap.float2FloatEntrySet(), sync ); return (ObjectSortedSet<Float2FloatMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Float, Float>> entrySet() { return (ObjectSortedSet)float2FloatEntrySet(); }
  public FloatSortedSet keySet() { if ( keys == null ) keys = FloatSortedSets.synchronize( sortedMap.keySet(), sync ); return (FloatSortedSet )keys; }

  public Float2FloatSortedMap subMap( final float from, final float to ) { return new SynchronizedSortedMap ( sortedMap.subMap( from, to ), sync ); }
  public Float2FloatSortedMap headMap( final float to ) { return new SynchronizedSortedMap ( sortedMap.headMap( to ), sync ); }
  public Float2FloatSortedMap tailMap( final float from ) { return new SynchronizedSortedMap ( sortedMap.tailMap( from ), sync ); }

  public float firstFloatKey() { synchronized( sync ) { return sortedMap.firstFloatKey(); } }
  public float lastFloatKey() { synchronized( sync ) { return sortedMap.lastFloatKey(); } }


  public Float firstKey() { synchronized( sync ) { return sortedMap.firstKey(); } }
  public Float lastKey() { synchronized( sync ) { return sortedMap.lastKey(); } }

  public Float2FloatSortedMap subMap( final Float from, final Float to ) { return new SynchronizedSortedMap ( sortedMap.subMap( from, to ), sync ); }
  public Float2FloatSortedMap headMap( final Float to ) { return new SynchronizedSortedMap ( sortedMap.headMap( to ), sync ); }
  public Float2FloatSortedMap tailMap( final Float from ) { return new SynchronizedSortedMap ( sortedMap.tailMap( from ), sync ); }



 }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
 public static Float2FloatSortedMap synchronize( final Float2FloatSortedMap m ) { return new SynchronizedSortedMap ( m ); }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map, using an assigned object to synchronize.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @param sync an object that will be used to synchronize the access to the sorted sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */

 public static Float2FloatSortedMap synchronize( final Float2FloatSortedMap m, final Object sync ) { return new SynchronizedSortedMap ( m, sync ); }




 /** An unmodifiable wrapper class for sorted maps. */

 public static class UnmodifiableSortedMap extends Float2FloatMaps.UnmodifiableMap implements Float2FloatSortedMap , java.io.Serializable {

  public static final long serialVersionUID = -7046029254386353129L;

  protected final Float2FloatSortedMap sortedMap;

  protected UnmodifiableSortedMap( final Float2FloatSortedMap m ) {
   super( m );
   sortedMap = m;
  }

  public FloatComparator comparator() { return sortedMap.comparator(); }

  public ObjectSortedSet<Float2FloatMap.Entry > float2FloatEntrySet() { if ( entries == null ) entries = ObjectSortedSets.unmodifiable( sortedMap.float2FloatEntrySet() ); return (ObjectSortedSet<Float2FloatMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Float, Float>> entrySet() { return (ObjectSortedSet)float2FloatEntrySet(); }
  public FloatSortedSet keySet() { if ( keys == null ) keys = FloatSortedSets.unmodifiable( sortedMap.keySet() ); return (FloatSortedSet )keys; }

  public Float2FloatSortedMap subMap( final float from, final float to ) { return new UnmodifiableSortedMap ( sortedMap.subMap( from, to ) ); }
  public Float2FloatSortedMap headMap( final float to ) { return new UnmodifiableSortedMap ( sortedMap.headMap( to ) ); }
  public Float2FloatSortedMap tailMap( final float from ) { return new UnmodifiableSortedMap ( sortedMap.tailMap( from ) ); }

  public float firstFloatKey() { return sortedMap.firstFloatKey(); }
  public float lastFloatKey() { return sortedMap.lastFloatKey(); }


  public Float firstKey() { return sortedMap.firstKey(); }
  public Float lastKey() { return sortedMap.lastKey(); }

  public Float2FloatSortedMap subMap( final Float from, final Float to ) { return new UnmodifiableSortedMap ( sortedMap.subMap( from, to ) ); }
  public Float2FloatSortedMap headMap( final Float to ) { return new UnmodifiableSortedMap ( sortedMap.headMap( to ) ); }
  public Float2FloatSortedMap tailMap( final Float from ) { return new UnmodifiableSortedMap ( sortedMap.tailMap( from ) ); }



 }

 /** Returns an unmodifiable type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in an unmodifiable sorted map.
	 * @return an unmodifiable view of the specified sorted map.
	 * @see java.util.Collections#unmodifiableSortedMap(SortedMap)
	 */
 public static Float2FloatSortedMap unmodifiable( final Float2FloatSortedMap m ) { return new UnmodifiableSortedMap ( m ); }
}
