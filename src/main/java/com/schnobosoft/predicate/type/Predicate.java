

/* First created by JCasGen Thu Mar 26 18:07:03 CET 2015 */
package com.schnobosoft.predicate.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Mar 26 18:36:54 CET 2015
 * XML source: /home/schnober/git/predicates/src/main/resources/com/schnobosoft/predicate/type/Predicate.xml
 * @generated */
public class Predicate extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Predicate.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Predicate() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Predicate(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Predicate(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Predicate(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: VerbLemma

  /** getter for VerbLemma - gets 
   * @generated
   * @return value of the feature 
   */
  public String getVerbLemma() {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_VerbLemma == null)
      jcasType.jcas.throwFeatMissing("VerbLemma", "com.schnobosoft.predicate.type.Predicate");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Predicate_Type)jcasType).casFeatCode_VerbLemma);}
    
  /** setter for VerbLemma - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setVerbLemma(String v) {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_VerbLemma == null)
      jcasType.jcas.throwFeatMissing("VerbLemma", "com.schnobosoft.predicate.type.Predicate");
    jcasType.ll_cas.ll_setStringValue(addr, ((Predicate_Type)jcasType).casFeatCode_VerbLemma, v);}    
   
    
  //*--------------*
  //* Feature: VerbBegin

  /** getter for VerbBegin - gets 
   * @generated
   * @return value of the feature 
   */
  public int getVerbBegin() {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_VerbBegin == null)
      jcasType.jcas.throwFeatMissing("VerbBegin", "com.schnobosoft.predicate.type.Predicate");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Predicate_Type)jcasType).casFeatCode_VerbBegin);}
    
  /** setter for VerbBegin - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setVerbBegin(int v) {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_VerbBegin == null)
      jcasType.jcas.throwFeatMissing("VerbBegin", "com.schnobosoft.predicate.type.Predicate");
    jcasType.ll_cas.ll_setIntValue(addr, ((Predicate_Type)jcasType).casFeatCode_VerbBegin, v);}    
   
    
  //*--------------*
  //* Feature: VerbEnd

  /** getter for VerbEnd - gets 
   * @generated
   * @return value of the feature 
   */
  public int getVerbEnd() {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_VerbEnd == null)
      jcasType.jcas.throwFeatMissing("VerbEnd", "com.schnobosoft.predicate.type.Predicate");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Predicate_Type)jcasType).casFeatCode_VerbEnd);}
    
  /** setter for VerbEnd - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setVerbEnd(int v) {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_VerbEnd == null)
      jcasType.jcas.throwFeatMissing("VerbEnd", "com.schnobosoft.predicate.type.Predicate");
    jcasType.ll_cas.ll_setIntValue(addr, ((Predicate_Type)jcasType).casFeatCode_VerbEnd, v);}    
   
    
  //*--------------*
  //* Feature: hasParticle

  /** getter for hasParticle - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getHasParticle() {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_hasParticle == null)
      jcasType.jcas.throwFeatMissing("hasParticle", "com.schnobosoft.predicate.type.Predicate");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Predicate_Type)jcasType).casFeatCode_hasParticle);}
    
  /** setter for hasParticle - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setHasParticle(boolean v) {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_hasParticle == null)
      jcasType.jcas.throwFeatMissing("hasParticle", "com.schnobosoft.predicate.type.Predicate");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Predicate_Type)jcasType).casFeatCode_hasParticle, v);}    
   
    
  //*--------------*
  //* Feature: ParticleText

  /** getter for ParticleText - gets 
   * @generated
   * @return value of the feature 
   */
  public String getParticleText() {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_ParticleText == null)
      jcasType.jcas.throwFeatMissing("ParticleText", "com.schnobosoft.predicate.type.Predicate");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Predicate_Type)jcasType).casFeatCode_ParticleText);}
    
  /** setter for ParticleText - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setParticleText(String v) {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_ParticleText == null)
      jcasType.jcas.throwFeatMissing("ParticleText", "com.schnobosoft.predicate.type.Predicate");
    jcasType.ll_cas.ll_setStringValue(addr, ((Predicate_Type)jcasType).casFeatCode_ParticleText, v);}    
   
    
  //*--------------*
  //* Feature: ParticleBegin

  /** getter for ParticleBegin - gets 
   * @generated
   * @return value of the feature 
   */
  public int getParticleBegin() {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_ParticleBegin == null)
      jcasType.jcas.throwFeatMissing("ParticleBegin", "com.schnobosoft.predicate.type.Predicate");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Predicate_Type)jcasType).casFeatCode_ParticleBegin);}
    
  /** setter for ParticleBegin - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setParticleBegin(int v) {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_ParticleBegin == null)
      jcasType.jcas.throwFeatMissing("ParticleBegin", "com.schnobosoft.predicate.type.Predicate");
    jcasType.ll_cas.ll_setIntValue(addr, ((Predicate_Type)jcasType).casFeatCode_ParticleBegin, v);}    
   
    
  //*--------------*
  //* Feature: ParticleEnd

  /** getter for ParticleEnd - gets 
   * @generated
   * @return value of the feature 
   */
  public int getParticleEnd() {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_ParticleEnd == null)
      jcasType.jcas.throwFeatMissing("ParticleEnd", "com.schnobosoft.predicate.type.Predicate");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Predicate_Type)jcasType).casFeatCode_ParticleEnd);}
    
  /** setter for ParticleEnd - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setParticleEnd(int v) {
    if (Predicate_Type.featOkTst && ((Predicate_Type)jcasType).casFeat_ParticleEnd == null)
      jcasType.jcas.throwFeatMissing("ParticleEnd", "com.schnobosoft.predicate.type.Predicate");
    jcasType.ll_cas.ll_setIntValue(addr, ((Predicate_Type)jcasType).casFeatCode_ParticleEnd, v);}    
  }

    