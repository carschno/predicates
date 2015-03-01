package com.schnobosoft.predicate;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;

/**
 * @author Carsten Schnober
 *
 */
public class PredicateWriter
    extends JCasConsumer_ImplBase
{

    private static final String PRED_POS_TAG = "VVFIN";
    private static final String PARTICLE_POS_TAG = "PTKVZ";

    @Override
    public void process(JCas arg0)
        throws AnalysisEngineProcessException
    {
        System.out.println(arg0.getDocumentText());
        String verb = null;
        String particle = "";

        for (POS pos : select(arg0, POS.class)) {
            getLogger().debug(String.format("%s\t%s", pos.getCoveredText(), pos.getPosValue()));

            if (pos.getPosValue().equals(PRED_POS_TAG)) {
                for (Lemma lemma : selectCovered(Lemma.class, pos)) {
                    assert verb == null;
                    verb = lemma.getValue();
                }
            }
            else if (pos.getPosValue().equals(PARTICLE_POS_TAG)) {
                for (Lemma lemma : selectCovered(Lemma.class, pos)) {
                    assert particle.isEmpty();
                    particle = lemma.getValue();
                }
            }
        }
        System.out.printf("%s%s%n", particle, verb);
        System.out.println("-----------------------------------");
    }
}
