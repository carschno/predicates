package com.schnobosoft.predicate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.parameter.ComponentParameters;

/**
 * @author Carsten Schnober
 *
 */
public abstract class PredicateWriter
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


    public BufferedWriter getWriter()
    {
        return writer;
    }
}
