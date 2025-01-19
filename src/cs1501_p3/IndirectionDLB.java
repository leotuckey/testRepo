/**
 * The class for the indirection data structure which is a DLB trie for CS1501 Project 3
 * @author    Leo Tuckey
 */
package cs1501_p3;

public class IndirectionDLB {

    private IndirectionDLBNode root;

    public IndirectionDLB() {
        this.root = new IndirectionDLBNode('\u0000');
    }

    public void add(Car car, int priceHeapIndex, int mileageHeapIndex, int specificPriceHeapIndex,
            int specificMileageHeapIndex) {
        this.root = addHelper(car.getVIN() + '^', 0, this.root, car, priceHeapIndex, mileageHeapIndex,
                specificPriceHeapIndex, specificMileageHeapIndex);
    }

    /**
     * Helper method for the add method
     * 
     * @param key      New word to be added to the dictionary
     * @param index    current index in the word
     * @param currNode current node we are on while traversing
     * 
     * @return the current node
     */
    public IndirectionDLBNode addHelper(String key, int index, IndirectionDLBNode currNode, Car car, int priceHeapIndex,
            int mileageHeapIndex, int specificPriceHeapIndex,
            int specificMileageHeapIndex) {
        if (index >= key.length()) {
            return currNode;
        }
        if (currNode == null) {
            currNode = new IndirectionDLBNode(key.charAt(index));
            currNode.setDown(addHelper(key, index + 1, currNode.getDown(), car, priceHeapIndex, mileageHeapIndex,
                    specificPriceHeapIndex,
                    specificMileageHeapIndex));
        } else if (currNode.getLet() == key.charAt(index)) {
            currNode.setDown(addHelper(key, index + 1, currNode.getDown(), car, priceHeapIndex, mileageHeapIndex,
                    specificPriceHeapIndex,
                    specificMileageHeapIndex));
        } else { // the current node's letter is not the same as the current letter we are on in
                 // the key.
            currNode.setRight(addHelper(key, index, currNode.getRight(), car, priceHeapIndex, mileageHeapIndex,
                    specificPriceHeapIndex,
                    specificMileageHeapIndex));
        }
        if (index == key.length() - 1) {
            currNode.car = car;
            currNode.priceHeapIndex = priceHeapIndex;
            currNode.mileageHeapIndex = mileageHeapIndex;
            currNode.specificPriceHeapIndex = specificPriceHeapIndex;
            currNode.specificMileageHeapIndex = specificMileageHeapIndex;
        }
        return currNode;
    }

    public boolean delete(String vin) {
        return deleteHelper(vin + '^', 0, this.root);
    }

    /**
     * Helper method for the delete method
     * 
     * @param key      Word to search the dictionary for
     * @param index    current index in the word
     * @param currNode current node we are on while traversing
     * 
     * @return true if key is in the dictionary, false otherwise
     */
    public boolean deleteHelper(String key, int index, IndirectionDLBNode currNode) {
        if (currNode == null) {
            return false;
        } else if (index == key.length() - 2) {
            if (currNode.getDown() == null) {
                while (currNode.getRight() != null) {
                    if (currNode.getRight().getLet() == '^') {
                        currNode.setRight(currNode.getRight().getRight());
                        return true;
                    }
                    currNode = currNode.getRight();
                }
                return false;
            } else if (currNode.getDown().getLet() == '^') {
                currNode.setDown(currNode.getDown().getRight());
                return true;
            } else {
                currNode = currNode.getDown();
                while (currNode.getRight() != null) {
                    if (currNode.getRight().getLet() == '^') {
                        currNode.setRight(currNode.getRight().getRight());
                        return true;
                    }
                    currNode = currNode.getRight();
                }
                return false;
            }
        } else if (currNode.getLet() == key.charAt(index)) {
            return deleteHelper(key, index + 1, currNode.getDown());
        } else { // the current node's letter is not the same as the current char in the key
            return deleteHelper(key, index, currNode.getRight());
        }
    }

    public IndirectionDLBNode getNode(String vin) {
        return getNodeHelper(vin + '^', 0, this.root);
    }

    /**
     * Returns the node associated with a given key (vin). If it doesn't exist,
     * returns null.
     * 
     * @param key      Word to search the dictionary for
     * @param index    current index in the word
     * @param currNode current node we are on while traversing
     * 
     * @return the corresponding node if key is in the dictionary, null otherwise
     */
    public IndirectionDLBNode getNodeHelper(String key, int index, IndirectionDLBNode currNode) {
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