package uk.co.mtford.jalp.abduction.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.mtford.jalp.JALP;
import uk.co.mtford.jalp.abduction.AbductiveFramework;
import uk.co.mtford.jalp.abduction.logic.instance.DenialInstance;
import uk.co.mtford.jalp.abduction.logic.instance.IInferableInstance;
import uk.co.mtford.jalp.abduction.logic.instance.PredicateInstance;
import uk.co.mtford.jalp.abduction.logic.instance.term.CharConstantInstance;
import uk.co.mtford.jalp.abduction.parse.program.JALPParser;
import uk.co.mtford.jalp.abduction.parse.program.ParseException;
import uk.co.mtford.jalp.abduction.tools.UniqueIdGenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: mtford
 * Date: 07/06/2012
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
public class D2Test {
    public D2Test() {

    }

    @Before
    public void noSetup() {

    }

    @After
    public void noTearDown() {

    }

    /*
   G = {ic :- p(t,s)}
   P = {p(X,Y) :- g(X), d(Y), P(X,Y) :- e(X,Y).}
    */
    @Test
    public void test1() throws ParseException, uk.co.mtford.jalp.abduction.parse.query.ParseException, IOException { // TODO D1.alp
        UniqueIdGenerator.reset();

        AbductiveFramework framework = JALPParser.readFromFile("examples/inference-rules/D1.alp");



        D2RuleNode ruleNode = new D2RuleNode();
        ruleNode.setAbductiveFramework(framework);

        DenialInstance denial = new DenialInstance();
        CharConstantInstance t = new CharConstantInstance("t");
        CharConstantInstance s = new CharConstantInstance("s");
        PredicateInstance p = new PredicateInstance("p",t,s);
        denial.getBody().add(p);

        ruleNode.getGoals().add(denial);
        ruleNode.setQuery(new LinkedList<IInferableInstance>(ruleNode.getGoals()));



        JALP.applyRule(ruleNode);
        JALP.getVisualizer("debug/rules/D2/Test1",ruleNode);


    }
}
