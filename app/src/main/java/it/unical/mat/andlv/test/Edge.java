package it.unical.mat.andlv.test;

import it.unical.mat.andlv.mapper.Predicate;
import it.unical.mat.andlv.mapper.Term;

/**
 */
@Predicate("edge")
public class Edge {

   @Term(0)
   private String node1;

   @Term(1)
   private String node2;

    public Edge() {
    }

    public Edge(String node1, String node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public String getNode1() {
        return node1;
    }

    public void setNode1(String node1) {
        this.node1 = node1;
    }

    public String getNode2() {
        return node2;
    }

    public void setNode2(String node2) {
        this.node2 = node2;
    }
}
