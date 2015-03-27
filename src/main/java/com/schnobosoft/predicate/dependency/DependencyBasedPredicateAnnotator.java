package com.schnobosoft.predicate.dependency;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.Level;

import com.schnobosoft.predicate.type.Predicate;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;

/**
 * Annotate predicates for each sentence based on dependency syntax parses.
 * <p>
 * This annotator requires that sentences, tokens, and dependencies have been annotated by previous
 * annotators in the pipeline.
 *
 * @author Carsten Schnober
 *
 */
public class DependencyBasedPredicateAnnotator
    extends JCasAnnotator_ImplBase
{
    private static final String PARTICLE_TYPE = "SVP";

    @Override
    public void process(JCas aJCas)
        throws AnalysisEngineProcessException
    {
        for (Sentence sentence : select(aJCas, Sentence.class)) {
            Predicate predicate = new Predicate(aJCas);
            boolean hasPredicate = false;
            boolean hasParticle = false;

            for (Token token : selectCovered(Token.class, sentence)) {
                List<Dependency> dependencies = selectCovered(Dependency.class, token);

                /* find root node */
                if (dependencies.isEmpty()) {
                    assert !hasPredicate;
                    hasPredicate = true;
                    predicate.setVerbBegin(token.getBegin());
                    predicate.setVerbEnd(token.getEnd());
                }
                else {
                    /* find particle */
                    for (Dependency dep : dependencies) {
                        if (dep.getDependencyType().equals(PARTICLE_TYPE)) {
                            assert !hasParticle;
                            hasParticle = false;
                            predicate.setHasParticle(true);
                            predicate.setParticleBegin(token.getBegin());
                            predicate.setParticleEnd(token.getEnd());
                        }
                    }
                }
            }
            if (hasPredicate) {
                predicate.addToIndexes(aJCas);
            }
            else {
                getContext().getLogger().log(Level.WARNING,
                        "No predicate found for sentence:\n" + sentence.getCoveredText());
            }
        }
    }

}
