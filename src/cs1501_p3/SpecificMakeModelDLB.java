/**
 * The class for DLB tries for specific makes and models for CS1501 Project 3
 * @author    Leo Tuckey
 */
package cs1501_p3;

public class SpecificMakeModelDLB {

    private SpecificMakeModelDLBNode root;

    public SpecificMakeModelDLB() {
        this.root = new SpecificMakeModelDLBNode('\u0000');
    }

    /**
     * Returns the node corresponding to the given key which is a String
     * concatenation of the make and the model of a car. If the key is not in the
     * trie, it will be created.
     * 
     * @param makeModelConcatenation the String concatenation
     * @return the corresponding node
     */
    public SpecificMakeModelDLBNode getNode(String makeModelConcatenation) {
        this.root = addHelper(makeModelConcatenation + '^', 0, this.root);
        return getNodeHelper(makeModelConcatenation + '^', 0, this.root);
    }

    public SpecificMakeModelDLBNode addHelper(String key, int index, SpecificMakeModelDLBNode currNode) {
        if (index >= key.length()) {
            return currNode;
        }
        if (currNode == null) {
            currNode = new SpecificMakeModelDLBNode(key.charAt(index));
            currNode.setDown(addHelper(key, index + 1, currNode.getDown()));
        } else if (currNode.getLet() == key.charAt(index)) {
            currNode.setDown(addHelper(key, index + 1, currNode.getDown()));
        } else { // the current node's letter is not the same as the current letter we are on in
                 // the key.
            currNode.setRight(addHelper(key, index, currNode.getRight()));
        }
        if (index == key.length() - 1) {
            if (currNode.specificPriceHeap == null) {
                currNode.specificPriceHeap = new PQ();
            }
            if (currNode.specificMileageHeap == null) {
                currNode.specificMileageHeap = new PQ();
            }
        }
        return currNode;
    }

    /**
     * Returns the node associated with a given key (String concatenation). If it
     * doesn't exist,
     * returns null.
     * 
     * @param key      Word to search the dictionary for
     * @param index    current index in the word
     * @param currNode current node we are on while traversing
     * 
     * @return the corresponding node if key is in the dictionary, null otherwise
     */
    public SpecificMakeModelDLBNode getNodeHelper(String key, int index, SpecificMakeModelDLBNode currNode) {
        if (currNode == null) {
            return null;
        } else if (index == key.length() - 1) {
            if (currNode.getLet() == key.charAt(index)) {
                return currNode;
            } else {
                return getNodeHelper(key, index, currNode.getRight());
            }
        } else if (currNode.getLet() == key.charAt(index)) {
            return getNodeHelper(key, index + 1, currNode.getDown());
        } else { // the current node's letter is not the same as the current char in the key
            return getNodeHelper(key, index, currNode.getRight());
        }
    }

}