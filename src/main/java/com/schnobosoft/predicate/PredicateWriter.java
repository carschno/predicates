package com.schnobosoft.predicate;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.parameter.ComponentParameters;
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

    public static final String PARAM_TARGET_LOCATION = ComponentParameters.PARAM_TARGET_LOCATION;
    @ConfigurationParameter(name = PARAM_TARGET_LOCATION, mandatory = false)
    private String targetLocation;

    private BufferedWriter writer;

    @Override
    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
        super.initialize(context);

        if (targetLocation == null) {
            writer = new BufferedWriter(new OutputStreamWriter(System.out));
        }
        else {
            try {
                writer = new BufferedWriter(new FileWriter(targetLocation));
            }
            catch (IOException e) {
                throw new ResourceInitializationException(e);
            }
        }
    };

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
            writer.write(String.format("%s\t%s%s%n", jcas.getDocumentText(), particle, verb));
            if (verb == null) {
                getLogger().warn("No predicate (finite verb) found!");
            }
        }
        catch (IOException e) {
            throw new AnalysisEngineProcessException(e);
        }
    }

    @Override
    public void collectionProcessComplete()
        throws AnalysisEngineProcessException
    {
        super.collectionProcessComplete();
        try {
            writer.close();
        }
        catch (IOException e) {
            throw new AnalysisEngineProcessException(e);
        }
    }
}
