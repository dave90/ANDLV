package it.unical.mat.andlv.base;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import it.unical.mat.andlv.mapper.ASPMapper;

/**
 * <p>AnswerSet class rapresents an Answer Set and contains get and set methods to handle it.</p>
 * @see java.util.HashMap
 */
public class AnswerSet {
    private String answerSet;//String representing the Answer Set
    private HashMap<Integer,Integer> weightMap;//Answer sets weights
    private Set<Object> objectsAtoms;

    /**
     * Constructor intialize an AnswerSet object with a String containing the Answer Set
     * and an {@link java.util.HashMap} containing Answer Set weight
     * @param outputString String representing an Answer Set
     */
    public AnswerSet(String outputString){
        this.answerSet = outputString;
        this.weightMap = new HashMap<Integer, Integer>();
    }

    /**
     * Get function for the Answer Set in String format
     * @return answerSet Answer Set in String format
     */
    public String getAnswerSet(){
        return this.answerSet;
    }

    public Set<Object> getAnswerObjects() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(objectsAtoms ==null) {
            objectsAtoms = new HashSet<>();
            ASPMapper mapper = ASPMapper.getInstance();
            String[] atoms = answerSet.split(", ");
            if (atoms.length > 0 && !atoms[0].equals("{}")) {
                atoms[0] = atoms[0].substring(1);
                atoms[atoms.length - 1] = atoms[atoms.length - 1].substring(0, atoms[atoms.length - 1].length() - 1);
                for (String atom : atoms) {
                    Object obj=mapper.getObject(atom);
                    if(obj!=null)
                        objectsAtoms.add(obj);
                }

            }
        }
        return objectsAtoms;
    }

}
