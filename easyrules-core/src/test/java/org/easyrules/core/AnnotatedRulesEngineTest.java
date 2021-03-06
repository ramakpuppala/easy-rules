package org.easyrules.core;

import org.easyrules.annotation.SimpleAnnotatedRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Test class for annotated rules engine execution.
 *
 * @author Mahmoud Ben Hassine (mahmoud@benhassine.fr)
 */
public class AnnotatedRulesEngineTest {

    private SimpleAnnotatedRule simpleAnnotatedRule;

    private AnnotatedRulesEngine rulesEngine;

    @Before
    public void setup(){
        simpleAnnotatedRule = new SimpleAnnotatedRule();
        rulesEngine = new AnnotatedRulesEngine();
    }

    @Test
    public void wellAnnotatedRuleShouldBeExecuted() {
        rulesEngine.registerRule(simpleAnnotatedRule);
        rulesEngine.fireRules();
        //The annotated rule should be executed
        assertThat(simpleAnnotatedRule.isExecuted()).isTrue();

    }

    @Test(expected = IllegalArgumentException.class)
    public void notAnnotatedRuleMustNotBeAccepted() {
        //an exception should be thrown at rule registration time
        rulesEngine.registerRule(new Object());
    }

    @Test
    public void actionsMustBeExecutedInTheDefinedOrder() {
        rulesEngine.registerRule(simpleAnnotatedRule);
        rulesEngine.fireRules();
        //Actions must be executed in the defined order
        assertEquals("012", simpleAnnotatedRule.getActionSequence());
    }

    @After
    public void clearRules() {
        rulesEngine.clearRules();
    }

}
