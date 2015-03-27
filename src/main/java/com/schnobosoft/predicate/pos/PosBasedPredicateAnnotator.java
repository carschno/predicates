package com.schnobosoft.predicate.pos;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.schnobosoft.predicate.type.Predicate;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;

/**
 * Annotate predicates for each sentence based on part-of-speech tags.
 *
 * @author Carsten Schnober
 *
 */
public class PosBasedPredicateAnnotator
    extends JCasAnnotator_ImplBase
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PosBasedPredicateAnnotator.class);
    private static final String PRED_POS_TAG = "VVFIN";
    private static final String PARTICLE_POS_TAG = "PTKVZ";

    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        for (Sentence sentence : select(jcas, Sentence.class)) {
            Predicate predicate = new Predicate(jcas);
            boolean hasPredicate = false;
            boolean hasParticle = false;

            for (POS pos : selectCovered(POS.class, sentence)) {
                if (pos.getPosValue().equals(PRED_POS_TAG)) {
                    assert !hasPredicate;
                    hasPredicate = true;
                    for (Lemma lemma : selectCovered(Lemma.class, pos)) {
                        predicate.setVerbBegin(pos.getBegin());
                        predicate.setVerbEnd(pos.getEnd());
                        predicate.setVerbLemma(lemma.getValue());
                    }
                }
                else if (pos.getPosValue().equals(PARTICLE_POS_TAG)) {
                    assert !hasParticle;
                    hasParticle = true;
                    predicate.setHasParticle(true);
                    predicate.setParticleBegin(pos.getBegin());
                    predicate.setParticleEnd(pos.getEnd());
                    predicate.setParticleText(pos.getCoveredText());
                }
            }
            if (hasPredicate) {
                predicate.addToIndexes(jcas);
            }
            else {
                LOGGER.warn("No predicate found for sentence:\n" + sentence.getCoveredText());
            }
        }
    }
}
