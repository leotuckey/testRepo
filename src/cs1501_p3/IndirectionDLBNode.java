/**
 * The class for a node in the DLB trie for CS1501 Project 3
 * @author    Leo Tuckey
 */
package cs1501_p3;

public class IndirectionDLBNode {

    public char letter;
    public Car car;
    public int priceHeapIndex;
    public int mileageHeapIndex;
    public int specificPriceHeapIndex;
    public int specificMileageHeapIndex;

    /**
     * Lead to other alternatives for current letter in the path
     */
    private IndirectionDLBNode right;

    /**
     * Leads to keys with prefixed by the current path
     */
    private IndirectionDLBNode down;

    public IndirectionDLBNode(char letter) {
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
    public IndirectionDLBNode getRight() {
        return right;
    }

    /**
     * Getter for the child DLBNode
     *
     * @return Reference to the down DLBNode
     */
    public IndirectionDLBNode getDown() {
        return down;
    }

    /**
     * Setter for the next linked-list DLBNode
     *
     * @param r DLBNode to set as the right reference
     */
    public void setRight(IndirectionDLBNode r) {
        right = r;
    }

    /**
     * Setter for the child DLBNode
     *
     * @param d DLBNode to set as the down reference
     */
    public void setDown(IndirectionDLBNode d) {
        down = d;
    }

}