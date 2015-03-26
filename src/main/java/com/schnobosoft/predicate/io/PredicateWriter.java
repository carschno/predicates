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
                StringBuffer p = new StringBuffer();
                if (predicate.getHasParticle()) {
                    p.append(predicate.getParticleText());
                }
                p.append(predicate.getVerbLemma());

                try {
                    writer.write(String.format("%s\t%s%n", aJCas.getDocumentText(), p.toString()));
                }
                catch (IOException e) {
                    throw new AnalysisEngineProcessException(e);
                }
            }
        }
    }

    @Deprecated
    public BufferedWriter getWriter()
    {
        return writer;
    }
}
