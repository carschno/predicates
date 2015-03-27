
/* First created by JCasGen Thu Mar 26 18:07:03 CET 2015 */
package com.schnobosoft.predicate.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Fri Mar 27 10:28:55 CET 2015
 * @generated */
public class Predicate_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Predicate_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Predicate_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Predicate(addr, Predicate_Type.this);
  			   Predicate_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Predicate(addr, Predicate_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Predicate.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("com.schnobosoft.predicate.type.Predicate");
 
  /** @generated */
  final Feature casFeat_VerbBegin;
  /** @generated */
  final int     casFeatCode_VerbBegin;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getVerbBegin(int addr) {
        if (featOkTst && casFeat_VerbBegin == null)
      jcas.throwFeatMissing("VerbBegin", "com.schnobosoft.predicate.type.Predicate");
    return ll_cas.ll_getIntValue(addr, casFeatCode_VerbBegin);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setVerbBegin(int addr, int v) {
        if (featOkTst && casFeat_VerbBegin == null)
      jcas.throwFeatMissing("VerbBegin", "com.schnobosoft.predicate.type.Predicate");
    ll_cas.ll_setIntValue(addr, casFeatCode_VerbBegin, v);}
    
  
 
  /** @generated */
  final Feature casFeat_VerbEnd;
  /** @generated */
  final int     casFeatCode_VerbEnd;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getVerbEnd(int addr) {
        if (featOkTst && casFeat_VerbEnd == null)
      jcas.throwFeatMissing("VerbEnd", "com.schnobosoft.predicate.type.Predicate");
    return ll_cas.ll_getIntValue(addr, casFeatCode_VerbEnd);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setVerbEnd(int addr, int v) {
        if (featOkTst && casFeat_VerbEnd == null)
      jcas.throwFeatMissing("VerbEnd", "com.schnobosoft.predicate.type.Predicate");
    ll_cas.ll_setIntValue(addr, casFeatCode_VerbEnd, v);}
    
  
 
  /** @generated */
  final Feature casFeat_hasParticle;
  /** @generated */
  final int     casFeatCode_hasParticle;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getHasParticle(int addr) {
        if (featOkTst && casFeat_hasParticle == null)
      jcas.throwFeatMissing("hasParticle", "com.schnobosoft.predicate.type.Predicate");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_hasParticle);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setHasParticle(int addr, boolean v) {
        if (featOkTst && casFeat_hasParticle == null)
      jcas.throwFeatMissing("hasParticle", "com.schnobosoft.predicate.type.Predicate");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_hasParticle, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ParticleBegin;
  /** @generated */
  final int     casFeatCode_ParticleBegin;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getParticleBegin(int addr) {
        if (featOkTst && casFeat_ParticleBegin == null)
      jcas.throwFeatMissing("ParticleBegin", "com.schnobosoft.predicate.type.Predicate");
    return ll_cas.ll_getIntValue(addr, casFeatCode_ParticleBegin);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setParticleBegin(int addr, int v) {
        if (featOkTst && casFeat_ParticleBegin == null)
      jcas.throwFeatMissing("ParticleBegin", "com.schnobosoft.predicate.type.Predicate");
    ll_cas.ll_setIntValue(addr, casFeatCode_ParticleBegin, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ParticleEnd;
  /** @generated */
  final int     casFeatCode_ParticleEnd;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getParticleEnd(int addr) {
        if (featOkTst && casFeat_ParticleEnd == null)
      jcas.throwFeatMissing("ParticleEnd", "com.schnobosoft.predicate.type.Predicate");
    return ll_cas.ll_getIntValue(addr, casFeatCode_ParticleEnd);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setParticleEnd(int addr, int v) {
        if (featOkTst && casFeat_ParticleEnd == null)
      jcas.throwFeatMissing("ParticleEnd", "com.schnobosoft.predicate.type.Predicate");
    ll_cas.ll_setIntValue(addr, casFeatCode_ParticleEnd, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Predicate_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_VerbBegin = jcas.getRequiredFeatureDE(casType, "VerbBegin", "uima.cas.Integer", featOkTst);
    casFeatCode_VerbBegin  = (null == casFeat_VerbBegin) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_VerbBegin).getCode();

 
    casFeat_VerbEnd = jcas.getRequiredFeatureDE(casType, "VerbEnd", "uima.cas.Integer", featOkTst);
    casFeatCode_VerbEnd  = (null == casFeat_VerbEnd) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_VerbEnd).getCode();

 
    casFeat_hasParticle = jcas.getRequiredFeatureDE(casType, "hasParticle", "uima.cas.Boolean", featOkTst);
    casFeatCode_hasParticle  = (null == casFeat_hasParticle) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_hasParticle).getCode();

 
    casFeat_ParticleBegin = jcas.getRequiredFeatureDE(casType, "ParticleBegin", "uima.cas.Integer", featOkTst);
    casFeatCode_ParticleBegin  = (null == casFeat_ParticleBegin) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ParticleBegin).getCode();

 
    casFeat_ParticleEnd = jcas.getRequiredFeatureDE(casType, "ParticleEnd", "uima.cas.Integer", featOkTst);
    casFeatCode_ParticleEnd  = (null == casFeat_ParticleEnd) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ParticleEnd).getCode();

  }
}



    