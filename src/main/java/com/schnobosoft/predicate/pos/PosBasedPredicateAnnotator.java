package com.schnobosoft.predicate.pos;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import com.schnobosoft.predicate.type.Predicate;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;

public class PosBasedPredicateAnnotator
    extends JCasAnnotator_ImplBase
{
    private static final String PRED_POS_TAG = "VVFIN";
    private static final String PARTICLE_POS_TAG = "PTKVZ";

    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        for (Sentence sentence : select(jcas, Sentence.class)) {
            Predicate predicate = new Predicate(jcas);
            for (POS pos : selectCovered(POS.class, sentence)) {
                if (pos.getPosValue().equals(PRED_POS_TAG)) {
                    for (Lemma lemma : selectCovered(Lemma.class, pos)) {
                        predicate.setBegin(pos.getBegin());
                        predicate.setEnd(pos.getEnd());
                        predicate.setVerbLemma(lemma.getValue());
                    }
                }
                else if (pos.getPosValue().equals(PARTICLE_POS_TAG)) {
                    predicate.setHasParticle(true);
                    predicate.setParticleBegin(pos.getBegin());
                    predicate.setParticleEnd(pos.getEnd());
                    predicate.setParticleText(pos.getCoveredText());
                }
            }
            predicate.addToIndexes(jcas);
        }
    }
}
