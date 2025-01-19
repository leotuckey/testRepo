/**
 * The class for a node in the DLB trie for specific makes and models for CS1501 Project 3
 * @author    Leo Tuckey
 */
package cs1501_p3;

public class SpecificMakeModelDLBNode {

    public char letter;
    public PQ specificPriceHeap;
    public PQ specificMileageHeap;
    /**
     * Lead to other alternatives for current letter in the path
     */
    private SpecificMakeModelDLBNode right;

    /**
     * Leads to keys with prefixed by the current path
     */
    private SpecificMakeModelDLBNode down;

    public SpecificMakeModelDLBNode(char letter) {
        this.letter = letter;
    }

    /**
     * Getter for the letter this DLBNode represents
     *
     * @return The letter
     */
    public char getLet() {
        return letter;
    }

    /**
     * Getter for the next linked-list DLBNode
     *
     * @return Reference to the right DLBNode
     */
    public SpecificMakeModelDLBNode getRight() {
        return right;
    }

    /**
     * Getter for the child DLBNode
     *
     * @return Reference to the down DLBNode
     */
    public SpecificMakeModelDLBNode getDown() {
        return down;
    }

    /**
     * Setter for the next linked-list DLBNode
     *
     * @param r DLBNode to set as the right reference
     */
    public void setRight(SpecificMakeModelDLBNode r) {
        right = r;
    }

    /**
     * Setter for the child DLBNode
     *
     * @param d DLBNode to set as the down reference
     */
    public void setDown(SpecificMakeModelDLBNode d) {
        down = d;
    }

}