package com.schnobosoft.predicate.dependency;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.io.IOException;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;

import com.schnobosoft.predicate.io.PredicateWriter;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;

/**
 * Extract and write predicates based on dependency syntax parse.
 *
 * @author Carsten Schnober
 *
 */
public class DependencyBasedPredicateWriter
    extends PredicateWriter
{
    private static final String PARTICLE_TYPE = "SVP";



    @Deprecated
    public void processOld(JCas aJCas)
        throws AnalysisEngineProcessException
    {
        String verb = null;
        String particle = "";
        for (AnnotationFS token : select(aJCas, Token.class)) {
            List<Dependency> dependencies = selectCovered(Dependency.class, token);
            if (dependencies.isEmpty()) {
                for (Lemma lemma : selectCovered(Lemma.class, token)) {
                    assert verb == null;
                    verb = lemma.getValue();
                }
            }
            else {
                for (Dependency dep : dependencies) {
                    if (dep.getDependencyType().equals(PARTICLE_TYPE)) {
                        assert particle.isEmpty();
                        particle = token.getCoveredText();
                    }
                }
            }
        }
        try {
            getWriter().write(String.format("%s\t%s%s%n", aJCas.getDocumentText(), particle, verb));
        }
        catch (IOException e) {
            throw new AnalysisEngineProcessException(e);
        }
    }
}
