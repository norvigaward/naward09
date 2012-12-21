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
package it.unimi.dsi.fastutil.chars;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import it.unimi.dsi.fastutil.shorts.ShortCollection;
import it.unimi.dsi.fastutil.shorts.ShortCollections;
import it.unimi.dsi.fastutil.shorts.ShortSets;
import java.util.Map;
/** A class providing static methods and objects that do useful things with type-specific maps.
 *
 * @see it.unimi.dsi.fastutil.Maps
 * @see java.util.Collections
 */
public class Char2ShortMaps {
 private Char2ShortMaps() {}
 /** An immutable class representing an empty type-specific map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */
 public static class EmptyMap extends Char2ShortFunctions.EmptyFunction implements Char2ShortMap , java.io.Serializable, Cloneable {
  public static final long serialVersionUID = -7046029254386353129L;
  protected EmptyMap() {}
  public boolean containsValue( final short v ) { return false; }
  public void putAll( final Map<? extends Character, ? extends Short> m ) { throw new UnsupportedOperationException(); }
  @SuppressWarnings("unchecked")
  public ObjectSet<Char2ShortMap.Entry > char2ShortEntrySet() { return ObjectSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public CharSet keySet() { return CharSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ShortCollection values() { return ShortSets.EMPTY_SET; }
  public boolean containsValue( final Object ov ) { return false; }
        private Object readResolve() { return EMPTY_MAP; }
  public Object clone() { return EMPTY_MAP; }
  public boolean isEmpty() { return true; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSet<Map.Entry<Character, Short>> entrySet() { return (ObjectSet)char2ShortEntrySet(); }

  public int hashCode() { return 0; }

  public boolean equals( final Object o ) {
   if ( ! ( o instanceof Map ) ) return false;

   return ((Map<?,?>)o).isEmpty();
  }

  public String toString() { return "{}"; }
 }



 /** An empty type-specific map (immutable). It is serializable and cloneable. */

 @SuppressWarnings("rawtypes")
 public static final EmptyMap EMPTY_MAP = new EmptyMap();


 /** An immutable class representing a type-specific singleton map.	 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */

 public static class Singleton extends Char2ShortFunctions.Singleton implements Char2ShortMap , java.io.Serializable, Cloneable {

  public static final long serialVersionUID = -7046029254386353129L;

  protected transient volatile ObjectSet<Char2ShortMap.Entry > entries;
  protected transient volatile CharSet keys;
  protected transient volatile ShortCollection values;

  protected Singleton( final char key, final short value ) {
   super( key, value );
  }

  public boolean containsValue( final short v ) { return ( (value) == (v) ); }

  public boolean containsValue( final Object ov ) { return ( (((((Short)(ov)).shortValue()))) == (value) ); }


  public void putAll( final Map<? extends Character, ? extends Short> m ) { throw new UnsupportedOperationException(); }

  public ObjectSet<Char2ShortMap.Entry > char2ShortEntrySet() { if ( entries == null ) entries = ObjectSets.singleton( (Char2ShortMap.Entry )new SingletonEntry() ); return entries; }
  public CharSet keySet() { if ( keys == null ) keys = CharSets.singleton( key ); return keys; }
  public ShortCollection values() { if ( values == null ) values = ShortSets.singleton( value ); return values; }

  protected class SingletonEntry implements Char2ShortMap.Entry , Map.Entry<Character,Short> {
   public Character getKey() { return (Character.valueOf(Singleton.this.key)); }
   public Short getValue() { return (Short.valueOf(Singleton.this.value)); }


   public char getCharKey() { return Singleton.this.key; }



   public short getShortValue() { return Singleton.this.value; }
   public short setValue( final short value ) { throw new UnsupportedOperationException(); }


   public Short setValue( final Short value ) { throw new UnsupportedOperationException(); }

   public boolean equals( final Object o ) {
    if (!(o instanceof Map.Entry)) return false;
    Map.Entry<?,?> e = (Map.Entry<?,?>)o;

    return ( (Singleton.this.key) == (((((Character)(e.getKey())).charValue()))) ) && ( (Singleton.this.value) == (((((Short)(e.getValue())).shortValue()))) );
   }

   public int hashCode() { return (Singleton.this.key) ^ (Singleton.this.value); }
   public String toString() { return Singleton.this.key + "->" + Singleton.this.value; }
  }

  public boolean isEmpty() { return false; }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSet<Map.Entry<Character, Short>> entrySet() { return (ObjectSet)char2ShortEntrySet(); }

  public int hashCode() { return (key) ^ (value); }

  public boolean equals( final Object o ) {
   if ( o == this ) return true;
   if ( ! ( o instanceof Map ) ) return false;

   Map<?,?> m = (Map<?,?>)o;
   if ( m.size() != 1 ) return false;
   return entrySet().iterator().next().equals( m.entrySet().iterator().next() );
  }

  public String toString() { return "{" + key + "=>" + value + "}"; }
 }

 /** Returns a type-specific immutable map containing only the specified pair. The returned map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned map.
	 * @param value the only value of the returned map.
	 * @return a type-specific immutable map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Char2ShortMap singleton( final char key, short value ) {
  return new Singleton ( key, value );
 }



 /** Returns a type-specific immutable map containing only the specified pair. The returned map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned map.
	 * @param value the only value of the returned map.
	 * @return a type-specific immutable map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Char2ShortMap singleton( final Character key, final Short value ) {
  return new Singleton ( ((key).charValue()), ((value).shortValue()) );
 }




 /** A synchronized wrapper class for maps. */

 public static class SynchronizedMap extends Char2ShortFunctions.SynchronizedFunction implements Char2ShortMap , java.io.Serializable {

  public static final long serialVersionUID = -7046029254386353129L;

  protected final Char2ShortMap map;

  protected transient volatile ObjectSet<Char2ShortMap.Entry > entries;
  protected transient volatile CharSet keys;
  protected transient volatile ShortCollection values;

  protected SynchronizedMap( final Char2ShortMap m, final Object sync ) {
   super( m, sync );
   this.map = m;
  }

  protected SynchronizedMap( final Char2ShortMap m ) {
   super( m );
   this.map = m;
  }

  public int size() { synchronized( sync ) { return map.size(); } }
  public boolean containsKey( final char k ) { synchronized( sync ) { return map.containsKey( k ); } }
  public boolean containsValue( final short v ) { synchronized( sync ) { return map.containsValue( v ); } }

  public short defaultReturnValue() { synchronized( sync ) { return map.defaultReturnValue(); } }
  public void defaultReturnValue( final short defRetValue ) { synchronized( sync ) { map.defaultReturnValue( defRetValue ); } }

  public short put( final char k, final short v ) { synchronized( sync ) { return map.put( k, v ); } }

  //public void putAll( final MAP KEY_VALUE_EXTENDS_GENERIC c ) { synchronized( sync ) { map.putAll( c ); } }
  public void putAll( final Map<? extends Character, ? extends Short> m ) { synchronized( sync ) { map.putAll( m ); } }

  public ObjectSet<Char2ShortMap.Entry > char2ShortEntrySet() { if ( entries == null ) entries = ObjectSets.synchronize( map.char2ShortEntrySet(), sync ); return entries; }
  public CharSet keySet() { if ( keys == null ) keys = CharSets.synchronize( map.keySet(), sync ); return keys; }
  public ShortCollection values() { if ( values == null ) return ShortCollections.synchronize( map.values(), sync ); return values; }

  public void clear() { synchronized( sync ) { map.clear(); } }
  public String toString() { synchronized( sync ) { return map.toString(); } }


  public Short put( final Character k, final Short v ) { synchronized( sync ) { return map.put( k, v ); } }



  public short remove( final char k ) { synchronized( sync ) { return map.remove( k ); } }
  public short get( final char k ) { synchronized( sync ) { return map.get( k ); } }
  public boolean containsKey( final Object ok ) { synchronized( sync ) { return map.containsKey( ok ); } }



  public boolean containsValue( final Object ov ) { synchronized( sync ) { return map.containsValue( ov ); } }







  public boolean isEmpty() { synchronized( sync ) { return map.isEmpty(); } }
  public ObjectSet<Map.Entry<Character, Short>> entrySet() { synchronized( sync ) { return map.entrySet(); } }

  public int hashCode() { synchronized( sync ) { return map.hashCode(); } }
  public boolean equals( final Object o ) { synchronized( sync ) { return map.equals( o ); } }
 }

 /** Returns a synchronized type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */
 public static Char2ShortMap synchronize( final Char2ShortMap m ) { return new SynchronizedMap ( m ); }

 /** Returns a synchronized type-specific map backed by the given type-specific map, using an assigned object to synchronize.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @param sync an object that will be used to synchronize the access to the map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */

 public static Char2ShortMap synchronize( final Char2ShortMap m, final Object sync ) { return new SynchronizedMap ( m, sync ); }



 /** An unmodifiable wrapper class for maps. */

 public static class UnmodifiableMap extends Char2ShortFunctions.UnmodifiableFunction implements Char2ShortMap , java.io.Serializable {

  public static final long serialVersionUID = -7046029254386353129L;

  protected final Char2ShortMap map;

  protected transient volatile ObjectSet<Char2ShortMap.Entry > entries;
  protected transient volatile CharSet keys;
  protected transient volatile ShortCollection values;

  protected UnmodifiableMap( final Char2ShortMap m ) {
   super( m );
   this.map = m;
  }

  public int size() { return map.size(); }
  public boolean containsKey( final char k ) { return map.containsKey( k ); }
  public boolean containsValue( final short v ) { return map.containsValue( v ); }

  public short defaultReturnValue() { throw new UnsupportedOperationException(); }
  public void defaultReturnValue( final short defRetValue ) { throw new UnsupportedOperationException(); }

  public short put( final char k, final short v ) { throw new UnsupportedOperationException(); }

  //public void putAll( final MAP KEY_VALUE_EXTENDS_GENERIC c ) { throw new UnsupportedOperationException(); }
  public void putAll( final Map<? extends Character, ? extends Short> m ) { throw new UnsupportedOperationException(); }

  public ObjectSet<Char2ShortMap.Entry > char2ShortEntrySet() { if ( entries == null ) entries = ObjectSets.unmodifiable( map.char2ShortEntrySet() ); return entries; }
  public CharSet keySet() { if ( keys == null ) keys = CharSets.unmodifiable( map.keySet() ); return keys; }
  public ShortCollection values() { if ( values == null ) return ShortCollections.unmodifiable( map.values() ); return values; }

  public void clear() { throw new UnsupportedOperationException(); }
  public String toString() { return map.toString(); }


  public Short put( final Character k, final Short v ) { throw new UnsupportedOperationException(); }



  public short remove( final char k ) { throw new UnsupportedOperationException(); }
  public short get( final char k ) { return map.get( k ); }
  public boolean containsKey( final Object ok ) { return map.containsKey( ok ); }



  public boolean containsValue( final Object ov ) { return map.containsValue( ov ); }







  public boolean isEmpty() { return map.isEmpty(); }
  public ObjectSet<Map.Entry<Character, Short>> entrySet() { return ObjectSets.unmodifiable( map.entrySet() ); }
 }

 /** Returns an unmodifiable type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in an unmodifiable map.
	 * @return an unmodifiable view of the specified map.
	 * @see java.util.Collections#unmodifiableMap(Map)
	 */
 public static Char2ShortMap unmodifiable( final Char2ShortMap m ) { return new UnmodifiableMap ( m ); }

}
