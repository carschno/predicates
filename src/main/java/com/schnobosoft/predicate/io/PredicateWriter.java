package com.schnobosoft.predicate.io;

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

import com.schnobosoft.predicate.type.Predicate;

import de.tudarmstadt.ukp.dkpro.core.api.parameter.ComponentParameters;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;

/**
 * @author Carsten Schnober
 *
 */
public class PredicateWriter
    extends JCasConsumer_ImplBase
{
    public static final String PARAM_TARGET_LOCATION = ComponentParameters.PARAM_TARGET_LOCATION;
    @ConfigurationParameter(name = PARAM_TARGET_LOCATION, mandatory = false)
    private String targetLocation;

    public static final String PARAM_USE_LEMMA = "useLemma";
    @ConfigurationParameter(name = PARAM_USE_LEMMA, mandatory = true, defaultValue = "true")
    private boolean useLemma;

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

    @Override
    public void process(JCas aJCas)
        throws AnalysisEngineProcessException
    {
        for (Sentence sentence : select(aJCas, Sentence.class)) {
            for (Predicate predicate : selectCovered(Predicate.class, sentence)) {
                String text = predicateText(aJCas, predicate);
                try {
                    writer.write(String.format("%s\t%s%n", sentence.getCoveredText(), text));
                }
                catch (IOException e) {
                    throw new AnalysisEngineProcessException(e);
                }
            }
        }
    }

    /**
     * Generate the text for the given predicate by concatenating the particle (if applicable) and
     * the verb lemma (or the original text).
     *
     * @param aJCas
     * @param predicate
     * @return a String containing the predicate
     */
    private String predicateText(JCas aJCas, Predicate predicate)
    {
        StringBuffer p = new StringBuffer();

        /* get particle text */
        if (predicate.getHasParticle()) {
            p.append(aJCas.getDocumentText().substring(
                    predicate.getParticleBegin(), predicate.getParticleEnd()));
        }

        /* get finite verb lemma or text */
        if (useLemma) {
            Lemma lemma = findLemma(aJCas, predicate.getVerbBegin(), predicate.getVerbEnd());
            if (lemma == null) {
                getLogger().warn("No matching lemma found. Using original text instead.");
                p.append(aJCas.getDocumentText().substring(
                        predicate.getVerbBegin(), predicate.getVerbEnd()));
            }
            else {
                p.append(lemma.getValue());
            }
        }
        else {
            p.append(aJCas.getDocumentText().substring(
                    predicate.getVerbBegin(), predicate.getVerbEnd()));
        }
        return p.toString();
    }

    /**
     * Find the lemma matching the given offsets.
     *
     * @param aJCas
     * @param begin
     *            the begin offset
     * @param end
     *            the end offset
     * @return the lemma starting end ending exactly at the given offsets.
     */
    private Lemma findLemma(JCas aJCas, int begin, int end)
    {
        Lemma result = null;
        for (Lemma lemma : select(aJCas, Lemma.class)) {
            if (lemma.getBegin() == begin && lemma.getEnd() == end) {
                result = lemma;
                break;
            }
        }
        return result;
    }
}
