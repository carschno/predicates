package com.schnobosoft.predicate.pos;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.apache.uima.fit.util.JCasUtil.select;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.junit.Test;

import com.schnobosoft.predicate.type.Predicate;

import de.tudarmstadt.ukp.dkpro.core.io.text.StringReader;
import de.tudarmstadt.ukp.dkpro.core.matetools.MateLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordPosTagger;

/**
 * Test POS-based predicate extractor.
 *
 * @author Carsten Schnober
 *
 */
public class PosBasedPredicateAnnotatorTest
{
    @Test
    public void test()
        throws ResourceInitializationException
    {
        String sentence = "Ich gehe nach Hause .";
        String expectedPredicateLemma = "gehen";
        short expectedPredicates = 1;
        short expectedBegin = 4;
        short expectedEnd = 8;

        CollectionReaderDescription reader = createReaderDescription(StringReader.class,
                StringReader.PARAM_LANGUAGE, "de",
                StringReader.PARAM_DOCUMENT_TEXT, sentence);
        AnalysisEngineDescription segmenter = createEngineDescription(OpenNlpSegmenter.class);
        AnalysisEngineDescription postagger = createEngineDescription(StanfordPosTagger.class);
        AnalysisEngineDescription lemmatizer = createEngineDescription(MateLemmatizer.class);
        AnalysisEngineDescription predicator = createEngineDescription(PosBasedPredicateAnnotator.class);

        for (JCas jcas : SimplePipeline.iteratePipeline(
                reader, segmenter, postagger, lemmatizer, predicator)) {
            short predicatesFound = 0;

            for (Predicate predicate : select(jcas, Predicate.class)) {
                predicatesFound++;

                /* test for verb */
                assertEquals(expectedPredicateLemma, predicate.getVerbLemma());
                assertEquals(expectedBegin, predicate.getVerbBegin());
                assertEquals(expectedEnd, predicate.getVerbEnd());

                /* test for particle */
                assertFalse(predicate.getHasParticle());
            }
            assertEquals(expectedPredicates, predicatesFound);
        }
    }

    @Test
    public void testParticle()
        throws ResourceInitializationException
    {
        String sentence = "Ich mache die Tür auf .";
        String expectedPredicateLemma = "machen";
        short expectedPredicates = 1;
        short expectedBegin = 4;
        short expectedEnd = 9;
        String expectedParticle = "auf";
        short expectedParticleBegin = 18;
        short expectedParticleEnd = 21;

        CollectionReaderDescription reader = createReaderDescription(StringReader.class,
                StringReader.PARAM_LANGUAGE, "de",
                StringReader.PARAM_DOCUMENT_TEXT, sentence);
        AnalysisEngineDescription segmenter = createEngineDescription(OpenNlpSegmenter.class);
        AnalysisEngineDescription postagger = createEngineDescription(StanfordPosTagger.class);
        AnalysisEngineDescription lemmatizer = createEngineDescription(MateLemmatizer.class);
        AnalysisEngineDescription predicator = createEngineDescription(PosBasedPredicateAnnotator.class);

        for (JCas jcas : SimplePipeline.iteratePipeline(
                reader, segmenter, postagger, lemmatizer, predicator)) {
            short predicatesFound = 0;

            for (Predicate predicate : select(jcas, Predicate.class)) {
                predicatesFound++;

                /* test for verb */
                assertEquals(expectedPredicateLemma, predicate.getVerbLemma());
                assertEquals(expectedBegin, predicate.getVerbBegin());
                assertEquals(expectedEnd, predicate.getVerbEnd());

                /* test for particle */
                assertTrue(predicate.getHasParticle());
                assertEquals(expectedParticleBegin, predicate.getParticleBegin());
                assertEquals(expectedParticleEnd, predicate.getParticleEnd());
                assertEquals(expectedParticle, predicate.getParticleText());
            }
            assertEquals(expectedPredicates, predicatesFound);
        }
    }
}
