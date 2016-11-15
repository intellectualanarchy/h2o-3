package water.fvec;

import water.H2O;
import water.util.UnsafeUtils;

/**
 * The empty-compression function, where data is in 'double's.
 */
public class C4FVolatileChunk extends Chunk {
  public transient final float [] _fs;
  C4FVolatileChunk(float[] ds ) { _mem=new byte[0]; _start = -1; _len = ds.length; _fs = ds; }

  @Override protected final long   at8_impl( int i ) {
    double res = atd_impl(i);
    if( Double.isNaN(res) ) throw new IllegalArgumentException("at8_abs but value is missing");
    return (long)res;
  }
  @Override protected final double   atd_impl( int i ) {
    return _fs[i] ;
  }
  @Override protected final boolean isNA_impl( int i ) { return Float.isNaN(_fs[i]); }
  @Override boolean set_impl(int idx, long l) {
    return false;
  }

  /**
   * Fast explicit set for double.
   * @param i
   * @param d
   */


  @Override boolean set_impl(int i, double d) {
    _fs[i] = (float)d;
    return true;
  }
  @Override boolean set_impl(int i, float f ) {
    _fs[i] = f;
    return true;
  }


  @Override boolean setNA_impl(int idx) { UnsafeUtils.set8d(_mem,(idx<<3),Double.NaN); return true; }
  @Override public NewChunk inflate_impl(NewChunk nc) {
    //nothing to inflate - just copy
    nc.alloc_doubles(_len);
    for( int i=0; i< _len; i++ )
      nc.doubles()[i] = _fs[i];
    nc.set_sparseLen(nc.set_len(_len));
    return nc;
  }
  // 3.3333333e33
//  public int pformat_len0() { return 22; }
//  public String pformat0() { return "% 21.15e"; }
  @Override public final void initFromBytes () {
    throw H2O.unimpl();
  }

  @Override
  public double [] getDoubles(double [] vals, int from, int to){
    throw H2O.unimpl();
  }

  /**
   * Dense bulk interface, fetch values from the given range
   * @param vals
   * @param from
   * @param to
   */
  @Override
  public double [] getDoubles(double [] vals, int from, int to, double NA){
    throw H2O.unimpl();
  }
  /**
   * Dense bulk interface, fetch values from the given ids
   * @param vals
   * @param ids
   */
  @Override
  public double [] getDoubles(double [] vals, int [] ids){
    throw H2O.unimpl();
  }

}
