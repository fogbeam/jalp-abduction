package uk.co.mtford.jalp.abduction.logic.instance.equalities;

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
import uk.co.mtford.jalp.abduction.rules.E2RuleNode;
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
public class E2Test {
    public E2Test() {

    }

    @Before
    public void noSetup() {

    }

    @After
    public void noTearDown() {

    }

    @Test
    public void test1() throws Exception {
        UniqueIdGenerator.reset();

        E2RuleNode ruleNode = new E2RuleNode();
        LinkedList<IInferableInstance> goals = new LinkedList<IInferableInstance>();

        VariableInstance X = new VariableInstance("X");
        CharConstantInstance c = new CharConstantInstance("c");
        PredicateInstance p = new PredicateInstance("p",X);
        EqualityInstance e = new EqualityInstance(X,c);
        goals.add(e);
        goals.add(p);

        DenialInstance d = new DenialInstance(goals);
        d.getUniversalVariables().add(X);
        ruleNode.getGoals().add(d);

        JALP.applyRule(ruleNode);
        JALP.getVisualizer("debug/rules/E2/Test1",ruleNode);
    }

    @Test
    public void test2() throws Exception {
        UniqueIdGenerator.reset();

        E2RuleNode ruleNode = new E2RuleNode();
        LinkedList<IInferableInstance> goals = new LinkedList<IInferableInstance>();

        VariableInstance X = new VariableInstance("X");
        CharConstantInstance c = new CharConstantInstance("c");
        PredicateInstance p = new PredicateInstance("p",X);
        EqualityInstance e = new EqualityInstance(c,X);
        goals.add(e);
        goals.add(p);

        DenialInstance d = new DenialInstance(goals);
        d.getUniversalVariables().add(X);
        ruleNode.getGoals().add(d);

        JALP.applyRule(ruleNode);
        JALP.getVisualizer("debug/rules/E2/Test2",ruleNode);
    }

    @Test
    public void test4() throws Exception {
        UniqueIdGenerator.reset();

        E2RuleNode ruleNode = new E2RuleNode();
        LinkedList<IInferableInstance> goals = new LinkedList<IInferableInstance>();

        VariableInstance X = new VariableInstance("X");
        VariableInstance Y = new VariableInstance("Y");

        PredicateInstance p = new PredicateInstance("p",Y);
        PredicateInstance q = new PredicateInstance("q",X);

        EqualityInstance eq = new EqualityInstance(X,p);
        goals.add(eq);
        goals.add(q);

        DenialInstance d = new DenialInstance(goals);
        d.getUniversalVariables().add(X);
        d.getUniversalVariables().add(Y);
        ruleNode.getGoals().add(d);

        JALP.applyRule(ruleNode);
        JALP.getVisualizer("debug/rules/E2/Test4",ruleNode);
    }

}
