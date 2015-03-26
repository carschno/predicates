package com.schnobosoft.predicate.dependency;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import com.schnobosoft.predicate.type.Predicate;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;

public class DependencyBasedPredicateAnnotator
    extends JCasAnnotator_ImplBase
{
    private static final String PARTICLE_TYPE = "SVP";

    @Override
    public void process(JCas aJCas)
        throws AnalysisEngineProcessException
    {
        String verb = null;
        String particle = "";
        Predicate predicate = new Predicate(aJCas);

        for (Sentence sentence : select(aJCas, Sentence.class)) {
            for (Token token : selectCovered(Token.class, sentence)) {
                List<Dependency> dependencies = selectCovered(Dependency.class, token);
                if (dependencies.isEmpty()) {
                    for (Lemma lemma : selectCovered(Lemma.class, token)) {
                        assert verb == null;
                        predicate.setVerbLemma(lemma.getValue());
                        predicate.setVerbBegin(token.getBegin());
                        predicate.setVerbEnd(token.getEnd());
                    }
                }
                else {
                    for (Dependency dep : dependencies) {
                        if (dep.getDependencyType().equals(PARTICLE_TYPE)) {
                            assert particle.isEmpty();
                            predicate.setHasParticle(true);
                            predicate.setParticleBegin(token.getBegin());
                            predicate.setParticleEnd(token.getEnd());
                            predicate.setParticleText(token.getCoveredText());
                        }
                    }
                }
            }
            predicate.addToIndexes(aJCas);
        }
    }

}
