package com.schnobosoft.predicate.dependency;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.Level;

import com.schnobosoft.predicate.pos.PosBasedPredicateAnnotator;
import com.schnobosoft.predicate.type.Predicate;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
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

    /**
     * Only accept predicates with matching POS tag. Defaults to
     * {@link PosBasedPredicateAnnotator#PRED_POS_TAG} ("VVFIN").
     * <p>
     * Set to empty string to avoid pos-based filtering (e.g. when POS tags are not available or
     * unreliable).
     */
    public static final String PARAM_POS_TYPE = "posType";
    @ConfigurationParameter(name = PARAM_POS_TYPE, mandatory = true, defaultValue = PosBasedPredicateAnnotator.PRED_POS_TAG)
    private String posType;

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

                    if (!posType.isEmpty() && selectCovered(POS.class, token).stream()
                            .findFirst()
                            .filter(pos -> pos.getPosValue().equals(posType))
                            .isPresent()) {
                        hasPredicate = true;
                        predicate.setVerbBegin(token.getBegin());
                        predicate.setVerbEnd(token.getEnd());
                    }
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
