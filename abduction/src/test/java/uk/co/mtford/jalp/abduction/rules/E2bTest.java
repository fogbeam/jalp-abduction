package uk.co.mtford.jalp.abduction.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.mtford.jalp.JALP;
import uk.co.mtford.jalp.abduction.logic.instance.DenialInstance;
import uk.co.mtford.jalp.abduction.logic.instance.IInferableInstance;
import uk.co.mtford.jalp.abduction.logic.instance.PredicateInstance;
import uk.co.mtford.jalp.abduction.logic.instance.equalities.EqualityInstance;
import uk.co.mtford.jalp.abduction.logic.instance.term.CharConstantInstance;
import uk.co.mtford.jalp.abduction.logic.instance.term.VariableInstance;
import uk.co.mtford.jalp.abduction.parse.query.JALPQueryParser;
import uk.co.mtford.jalp.abduction.tools.UniqueIdGenerator;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mtford
 * Date: 07/06/2012
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
public class E2bTest {
    public E2bTest() {

    }

    @Before
    public void noSetup() {

    }

    @After
    public void noTearDown() {

    }

    /*
    G = {ic :- X=c, q(X).}

    Expect two nodes, G = {ic :- q(c).} and G = {}, E = {X!=c}
     */
    @Test
    public void test1() throws Exception {
        UniqueIdGenerator.reset();

        E2bRuleNode ruleNode = new E2bRuleNode();
        List<IInferableInstance> denialBody = new LinkedList<IInferableInstance>();

        VariableInstance X = new VariableInstance("X");
        CharConstantInstance c = new CharConstantInstance("c");
        EqualityInstance e = new EqualityInstance(X,c);
        PredicateInstance q = new PredicateInstance("q",X);
        denialBody.add(e);
        denialBody.add(q);

        DenialInstance d = new DenialInstance(denialBody);
        ruleNode.getGoals().add(d);

        JALP.applyRule(ruleNode);
        JALP.getVisualizer("debug/rules/E2b/Test1",ruleNode);
    }

    /*
    G = {ic :- X=c, q(X).}

    Expect two nodes, G = {ic :- q(p(Y)).} and G = {}, E = {X!=p(Y)}
     */
    @Test
    public void test2() throws Exception {
        UniqueIdGenerator.reset();

        E2bRuleNode ruleNode = new E2bRuleNode();

        List<IInferableInstance> denialBody = new LinkedList<IInferableInstance>();

        VariableInstance X = new VariableInstance("X");
        VariableInstance Y = new VariableInstance("Y");
        PredicateInstance p = new PredicateInstance("p",Y);
        EqualityInstance e = new EqualityInstance(X,p);
        PredicateInstance q = new PredicateInstance("q",X);
        denialBody.add(e);
        denialBody.add(q);

        DenialInstance d = new DenialInstance(denialBody);
        ruleNode.getGoals().add(d);

        JALP.applyRule(ruleNode);
        JALP.getVisualizer("debug/rules/E2b/Test2",ruleNode);
    }
}
