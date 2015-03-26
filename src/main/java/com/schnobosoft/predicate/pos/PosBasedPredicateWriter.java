package com.schnobosoft.predicate.pos;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.io.IOException;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import com.schnobosoft.predicate.PredicateWriter;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;

public class PosBasedPredicateWriter
    extends PredicateWriter
{
    private static final String PRED_POS_TAG = "VVFIN";
    private static final String PARTICLE_POS_TAG = "PTKVZ";

    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        String verb = null;
        String particle = "";

        for (POS pos : select(jcas, POS.class)) {
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

        try {
            getWriter().write(String.format("%s\t%s%s%n", jcas.getDocumentText(), particle, verb));
            if (verb == null) {
                getLogger().warn("No predicate (finite verb) found!");
            }
        }
        catch (IOException e) {
            throw new AnalysisEngineProcessException(e);
        }
    }
}
