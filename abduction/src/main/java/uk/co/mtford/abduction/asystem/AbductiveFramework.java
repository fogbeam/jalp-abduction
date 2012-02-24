/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.mtford.abduction.asystem;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import uk.co.mtford.abduction.logic.PredicateInstance;
import uk.co.mtford.abduction.logic.program.Denial;
import uk.co.mtford.abduction.logic.program.Rule;

/**
 *
 * @author mtford
 */
public class AbductiveFramework {
    protected List<Rule> P; // Logic program.
    protected List<PredicateInstance> A; // Abducibles.
    protected List<Denial> IC; // Integrity constraints.
    
    public AbductiveFramework() {
        P = new LinkedList<Rule>();
        A = new LinkedList<PredicateInstance>();
        IC = new LinkedList<Denial>();
    }

    public AbductiveFramework(List<Rule> P, List<PredicateInstance> A, List<Denial> IC) {
        this.P = P;
        this.A = A;
        this.IC = IC;
    }

    public void setA(List<PredicateInstance> A) {
        this.A = A;
    }

    public void setIC(List<Denial> IC) {
        this.IC = IC;
    }

    public void setP(List<Rule> P) {
        this.P = P;
    }

    public List<PredicateInstance> getA() {
        return A;
    }

    public List<Denial> getIC() {
        return IC;
    }

    public List<Rule> getP() {
        return P;
    }
    
    
    
}
