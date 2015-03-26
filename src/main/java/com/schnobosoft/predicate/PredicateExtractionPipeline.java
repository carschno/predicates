package com.schnobosoft.predicate;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.pipeline.SimplePipeline;

import com.schnobosoft.predicate.dependency.DependencyBasedPredicateAnnotator;
import com.schnobosoft.predicate.io.LineByLineReader;
import com.schnobosoft.predicate.io.PredicateWriter;
import com.schnobosoft.predicate.pos.PosBasedPredicateAnnotator;

import de.tudarmstadt.ukp.dkpro.core.matetools.MateLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.matetools.MateParser;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordPosTagger;

public class PredicateExtractionPipeline
{
    private enum METHOD
    {
        DEPENDENCY, POS
    };

    private static final METHOD method = METHOD.POS;

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
        AnalysisEngineDescription writer = createEngineDescription(PredicateWriter.class);

        AnalysisEngineDescription predicateExtractor;
        switch (method) {
        case DEPENDENCY:
            predicateExtractor = createEngineDescription(DependencyBasedPredicateAnnotator.class);
            break;
        case POS:
            predicateExtractor = createEngineDescription(PosBasedPredicateAnnotator.class);
            break;
        default:
            throw new IllegalStateException();
        }

        SimplePipeline.runPipeline(reader, segmenter, postagger, lemmatizer, parser,
                predicateExtractor, writer);

    }
}
