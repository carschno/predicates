package com.schnobosoft.predicate.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.apache.uima.UimaContext;
import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.CasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;

import de.tudarmstadt.ukp.dkpro.core.api.parameter.ComponentParameters;

/**
 * @author Carsten Schnober
 *
 */
public class LineByLineReader
    extends CasCollectionReader_ImplBase

{
    public static final String PARAM_SOURCE_LOCATION = ComponentParameters.PARAM_SOURCE_LOCATION;
    @ConfigurationParameter(name = PARAM_SOURCE_LOCATION, mandatory = true)
    private File sourceLocation;

    public static final String PARAM_LANGUAGE = ComponentParameters.PARAM_LANGUAGE;
    @ConfigurationParameter(name = PARAM_LANGUAGE, mandatory = true)
    private String language;

    private BufferedReader reader;
    private Iterator<String> lineIterator;

    @Override
    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
        super.initialize(context);

        try {
            reader = new BufferedReader(new FileReader(sourceLocation.getPath()));
            lineIterator = reader.lines().iterator();
        }
        catch (FileNotFoundException e) {
            throw new ResourceInitializationException(e);
        }
    };

    public void getNext(CAS cas)
        throws IOException, CollectionException
    {
        cas.setDocumentText(lineIterator.next());
        cas.setDocumentLanguage(language);
    }

    public void close()
        throws IOException
    {
        reader.close();
    }

    public Progress[] getProgress()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean hasNext()
        throws IOException, CollectionException
    {
        return lineIterator.hasNext();
    }

}
