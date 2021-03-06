package uk.co.mtford.jalp.abduction.logic.instance.constraints;

import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.Variable;
import uk.co.mtford.jalp.abduction.logic.instance.*;
import uk.co.mtford.jalp.abduction.logic.instance.term.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * bob in [john,jane,bob]
 */
public class InConstantListConstraintInstance  extends InListConstraintInstance {

    public InConstantListConstraintInstance(ITermInstance left, CharConstantListInstance right) {
        super(left, right);
    }

    
    public IFirstOrderLogicInstance performSubstitutions(Map<VariableInstance, IUnifiableInstance> substitutions) {
        left = (ITermInstance)left.performSubstitutions(substitutions);
        right = (CharConstantListInstance) right.performSubstitutions(substitutions);
        return this;
    }

    
    public IFirstOrderLogicInstance deepClone(Map<VariableInstance, IUnifiableInstance> substitutions) {
        return new InConstantListConstraintInstance((ITermInstance)left.deepClone(substitutions), (CharConstantListInstance)right.deepClone(substitutions));
    }

    
    public IFirstOrderLogicInstance shallowClone() {
        return new InConstantListConstraintInstance((ITermInstance)left.shallowClone(),(CharConstantListInstance)right.shallowClone());
    }

    
    public boolean reduceToChoco(List<Map<VariableInstance, IUnifiableInstance>> possSubst, List<Constraint> chocoConstraints, HashMap<ITermInstance, Variable> chocoVariables, HashMap<Constraint,IConstraintInstance> constraintMap) {
        return left.inList((CharConstantListInstance) right,possSubst);
    }

    
    public boolean reduceToNegativeChoco(List<Map<VariableInstance, IUnifiableInstance>> possSubst, List<Constraint> chocoConstraints, HashMap<ITermInstance, Variable> chocoVariables, HashMap<Constraint,IConstraintInstance> constraintMap) {
        List<Map<VariableInstance,IUnifiableInstance>> newPossSubst = new LinkedList<Map<VariableInstance, IUnifiableInstance>>(possSubst);
        for (Map<VariableInstance, IUnifiableInstance> subst:possSubst) {
            for (CharConstantInstance constantInstance: ((CharConstantListInstance)right).getList()) {
                CharConstantInstance substLeft = (CharConstantInstance) left.performSubstitutions(subst);
                boolean unificationSuccess = substLeft.unify(constantInstance,subst);
                if (unificationSuccess) {
                    newPossSubst.remove(subst); // It's in the list.
                    break;
                }
            }
        }
        possSubst.removeAll(possSubst);
        possSubst.addAll(newPossSubst);
        if (possSubst.isEmpty()) return false;
        return true;
    }

}
