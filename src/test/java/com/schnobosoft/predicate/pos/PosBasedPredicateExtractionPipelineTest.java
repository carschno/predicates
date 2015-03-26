package com.schnobosoft.predicate.pos;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.junit.Test;

import com.schnobosoft.predicate.io.LineByLineReader;
import com.schnobosoft.predicate.io.PredicateWriter;
import com.schnobosoft.predicate.pos.PosBasedPredicateWriter;

import de.tudarmstadt.ukp.dkpro.core.matetools.MateLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordPosTagger;

/**
 * Test the full pipeline.
 *
 * @author Carsten Schnober
 *
 */
public class PosBasedPredicateExtractionPipelineTest
{
    @Test
    public void testPipeline()
        throws UIMAException, IOException
    {
        File input = new File("src/test/resources/headings.de.txt");
        File output = new File("target/predicates.txt");
        File expected = new File("src/test/resources/predicates.txt");

        CollectionReaderDescription reader = createReaderDescription(LineByLineReader.class,
                LineByLineReader.PARAM_LANGUAGE, "de",
                LineByLineReader.PARAM_SOURCE_LOCATION, input);

        AnalysisEngineDescription segmenter = createEngineDescription(OpenNlpSegmenter.class);
        AnalysisEngineDescription postagger = createEngineDescription(StanfordPosTagger.class);
        AnalysisEngineDescription lemmatizer = createEngineDescription(MateLemmatizer.class);
        AnalysisEngineDescription predicatePrinter = createEngineDescription(
                PosBasedPredicateWriter.class,
                PredicateWriter.PARAM_TARGET_LOCATION, output);

        SimplePipeline.runPipeline(reader, segmenter, postagger, lemmatizer, predicatePrinter);

        assertEquals(FileUtils.readFileToString(expected), FileUtils.readFileToString(output));

    }

}
