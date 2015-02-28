package com.schnobosoft.predicate;

import static org.apache.uima.fit.util.JCasUtil.select;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;

/**
 * @author Carsten Schnober
 *
 */
public class PredicateWriter
    extends JCasConsumer_ImplBase
{

    private static final String PRED_POS_TAG = "VVFIN";

    @Override
    public void process(JCas arg0)
        throws AnalysisEngineProcessException
    {
        System.out.println(arg0.getDocumentText());
        for (POS pos : select(arg0, POS.class)) {
            if (pos.getPosValue().equals(PRED_POS_TAG)) {
                System.out.println(pos.getCoveredText());
            }
        }
        System.out.println("-----------------------------------");
    }

}
