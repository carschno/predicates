package com.schnobosoft.predicate.pos;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.pipeline.SimplePipeline;

import com.schnobosoft.predicate.io.LineByLineReader;

import de.tudarmstadt.ukp.dkpro.core.matetools.MateLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordPosTagger;

/**
 * @author Carsten Schnober
 *
 */
public class PosBasedPredicateExtractionPipeline
{

    /**
     * @param args
     * @throws UIMAException
     * @throws IOException
     */
    public static void main(String[] args)
        throws UIMAException, IOException
    {
        String headings = "src/test/resources/headings.de.txt";

        CollectionReaderDescription reader = createReaderDescription(LineByLineReader.class,
                LineByLineReader.PARAM_LANGUAGE, "de",
                LineByLineReader.PARAM_SOURCE_LOCATION, headings);

        AnalysisEngineDescription segmenter = createEngineDescription(OpenNlpSegmenter.class);
        AnalysisEngineDescription postagger = createEngineDescription(StanfordPosTagger.class);
        AnalysisEngineDescription lemmatizer = createEngineDescription(MateLemmatizer.class);
        AnalysisEngineDescription predicatePrinter = createEngineDescription(PosBasedPredicateWriter.class);

        SimplePipeline.runPipeline(reader, segmenter, postagger, lemmatizer, predicatePrinter);
    }

}