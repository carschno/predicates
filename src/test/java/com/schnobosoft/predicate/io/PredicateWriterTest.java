package com.schnobosoft.predicate.io;

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

import com.schnobosoft.predicate.pos.PosBasedPredicateAnnotator;

import de.tudarmstadt.ukp.dkpro.core.io.text.StringReader;
import de.tudarmstadt.ukp.dkpro.core.matetools.MateLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordPosTagger;

public class PredicateWriterTest
{
    @Test
    public void test()
        throws UIMAException, IOException
    {
        File targetFile = new File("target/writeroutput.txt");
        targetFile.deleteOnExit();
        String sentence = "Ich gehe nach Hause.";
        String expectedOutput = sentence + "\tgehen\n";

        CollectionReaderDescription reader = createReaderDescription(StringReader.class,
                StringReader.PARAM_LANGUAGE, "de",
                StringReader.PARAM_DOCUMENT_TEXT, sentence);

        AnalysisEngineDescription segmenter = createEngineDescription(OpenNlpSegmenter.class);
        AnalysisEngineDescription postagger = createEngineDescription(StanfordPosTagger.class);
        AnalysisEngineDescription lemmatizer = createEngineDescription(MateLemmatizer.class);
        AnalysisEngineDescription predicator = createEngineDescription(PosBasedPredicateAnnotator.class);
        AnalysisEngineDescription writer = createEngineDescription(PredicateWriter.class,
                PredicateWriter.PARAM_TARGET_LOCATION, targetFile);

        SimplePipeline.runPipeline(reader, segmenter, postagger, lemmatizer, predicator, writer);
        assertEquals(expectedOutput, FileUtils.readFileToString(targetFile));
    }

    @Test
    public void testParticle()
        throws UIMAException, IOException
    {
        File targetFile = new File("target/writeroutput_particle.txt");
        targetFile.deleteOnExit();
        String sentence = "Ich mache die Tür auf.";
        String expectedOutput = sentence + "\taufmachen\n";

        CollectionReaderDescription reader = createReaderDescription(StringReader.class,
                StringReader.PARAM_LANGUAGE, "de",
                StringReader.PARAM_DOCUMENT_TEXT, sentence);

        AnalysisEngineDescription segmenter = createEngineDescription(OpenNlpSegmenter.class);
        AnalysisEngineDescription postagger = createEngineDescription(StanfordPosTagger.class);
        AnalysisEngineDescription lemmatizer = createEngineDescription(MateLemmatizer.class);
        AnalysisEngineDescription predicator = createEngineDescription(PosBasedPredicateAnnotator.class);
        AnalysisEngineDescription writer = createEngineDescription(PredicateWriter.class,
                PredicateWriter.PARAM_TARGET_LOCATION, targetFile);

        SimplePipeline.runPipeline(reader, segmenter, postagger, lemmatizer, predicator, writer);
        assertEquals(expectedOutput, FileUtils.readFileToString(targetFile));
    }

    @Test
    public void testNoLemma()
        throws UIMAException, IOException
    {
        File targetFile = new File("target/writeroutput_nolemma.txt");
        targetFile.deleteOnExit();
        String sentence = "Ich gehe nach Hause.";
        String expectedOutput = sentence + "\tgehe\n";

        CollectionReaderDescription reader = createReaderDescription(StringReader.class,
                StringReader.PARAM_LANGUAGE, "de",
                StringReader.PARAM_DOCUMENT_TEXT, sentence);

        AnalysisEngineDescription segmenter = createEngineDescription(OpenNlpSegmenter.class);
        AnalysisEngineDescription postagger = createEngineDescription(StanfordPosTagger.class);
        AnalysisEngineDescription lemmatizer = createEngineDescription(MateLemmatizer.class);
        AnalysisEngineDescription predicator = createEngineDescription(PosBasedPredicateAnnotator.class);
        AnalysisEngineDescription writer = createEngineDescription(PredicateWriter.class,
                PredicateWriter.PARAM_TARGET_LOCATION, targetFile,
                PredicateWriter.PARAM_USE_LEMMA, false);

        SimplePipeline.runPipeline(reader, segmenter, postagger, lemmatizer, predicator, writer);
        assertEquals(expectedOutput, FileUtils.readFileToString(targetFile));
    }

}
