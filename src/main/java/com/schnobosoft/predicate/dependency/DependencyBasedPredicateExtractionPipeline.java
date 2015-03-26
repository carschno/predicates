package com.schnobosoft.predicate.dependency;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.pipeline.SimplePipeline;

import com.schnobosoft.predicate.io.LineByLineReader;
import com.schnobosoft.predicate.io.PredicateWriter;

import de.tudarmstadt.ukp.dkpro.core.matetools.MateLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.matetools.MateParser;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordPosTagger;

/**
 * Pipeline for extracting predicates based on dependency syntax parse.
 *
 * @author Carsten Schnober
 *
 */
public class DependencyBasedPredicateExtractionPipeline
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
        AnalysisEngineDescription parser = createEngineDescription(MateParser.class);
        AnalysisEngineDescription predicateExtractor = createEngineDescription(DependencyBasedPredicateAnnotator.class);
        AnalysisEngineDescription writer = createEngineDescription(PredicateWriter.class);

        SimplePipeline.runPipeline(reader, segmenter, postagger, lemmatizer, parser,
                predicateExtractor, writer);
    }
}