/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.mtford.alp.abduction.asystem;

import java.util.List;
import java.util.Stack;
import org.apache.log4j.Logger;
import uk.co.mtford.alp.abduction.AbductiveFramework;

/** A basic implementation of an ASystem state rewriter.
 *  Uses no heuristics. Simply LIFO on next state and goal selection.
 *
 * @author mtford
 */
public class ASystemBasicStateRewriter extends ASystemStateRewriter {
    
    final private static Logger LOGGER = Logger.getLogger(ASystemBasicStateRewriter.class);
    
    private Stack<ASystemState> stateStack = new Stack<ASystemState>();

    public ASystemBasicStateRewriter(AbductiveFramework abductiveFramework) {
        super(abductiveFramework);
    }

    @Override
    protected IASystemInferable getNextGoal(ASystemState state) {
        String logHead = "getNextGoal(): ";
        LOGGER.info(logHead+"starting goal selection.");
        IASystemInferable goal = null;
        if (!state.goals.isEmpty()) {
            goal = state.goals.get(0);
        }
        LOGGER.info(logHead+"chosen goal is "+goal);
        return goal;
    }

    @Override
    protected ASystemState stateTransition(IASystemInferable goal, ASystemState state) {
        String logHead = "stateTransition("+goal+"): ";
        LOGGER.info(logHead+"moving to next state.");
        if (goal==null) { // No more goals.
            LOGGER.info(logHead+"Ran out of goals.");
            return null;
        } 
        List<ASystemState> states = goal.applyInferenceRule(abductiveFramework, state);
        LOGGER.info(logHead+"Generated new states "+states);
        stateStack.addAll(states);
        if (stateStack.isEmpty()) {
            return null;
        }
        ASystemState nextState = stateStack.pop();
        LOGGER.info(logHead+"Next chosen state is "+nextState);
        return nextState;
    }

    @Override
    public void reset() {
        this.abductiveFramework=new AbductiveFramework();
        this.stateStack=new Stack<ASystemState>();
    }

}