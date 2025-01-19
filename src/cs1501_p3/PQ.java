/**
 * The class for the heap priority queue data structure for CS1501 Project 3
 * @author    Leo Tuckey
 */
package cs1501_p3;

public class PQ {

    public HeapElement[] heapArr;
    private int size;

    public PQ() {
        this.heapArr = new HeapElement[10];
        this.size = 0;
    }

    /**
     * adds an element to the PQ
     * 
     * @param entry
     * @param indDLB
     * @param whichHeap
     * @return the index where this item was added in this priority queue
     */
    public int add(HeapElement entry, IndirectionDLB indDLB, int whichHeap) {
        this.size++;
        if(this.size>this.heapArr.length) { //resize
            HeapElement[] newHeapArr = new HeapElement[this.heapArr.length*2];
            for(int i=0; i<this.heapArr.length; i++) {
                newHeapArr[i]=this.heapArr[i];
            }
            this.heapArr=newHeapArr;
        }
        this.heapArr[size-1]=entry;
        return moveUp(size-1, indDLB, whichHeap);
    }

    public int moveUp(int index, IndirectionDLB indDLB, int whichHeap) {
        int parentIndex = (index - 1) / 2;
        while (index != 0 && this.heapArr[parentIndex].metric > this.heapArr[index].metric) {
            swap(index, parentIndex, whichHeap, indDLB);
            index=parentIndex;
            parentIndex=(index-1)/2;
        }
        return index;
    }

    public int moveDown(int index, IndirectionDLB indDLB, int whichHeap) {
        int childIndexOne=2*index+1;
        int childIndexTwo=2*index+2;
        int indexOfSmallerChild=index;
        if(childIndexOne<this.size && this.heapArr[childIndexOne].metric<this.heapArr[index].metric) {
            indexOfSmallerChild=childIndexOne;
        }
        if(childIndexTwo<this.size && this.heapArr[childIndexTwo].metric<this.heapArr[indexOfSmallerChild].metric) {
            indexOfSmallerChild=childIndexTwo;
        }
        if(indexOfSmallerChild!=index) {
            swap(index, indexOfSmallerChild, whichHeap, indDLB);
            return moveDown(indexOfSmallerChild, indDLB, whichHeap);
        }
        return index;
    }

    /**
     * Swaps two elements in the heap array
     * 
     * @param index1    the first index
     * @param index2    the second index. The index that needs to be updated in the indirection data structure.
     * @param whichHeap which heap we are changing and need to update the element of
     *                  in the indirection data structure. 1 if PriceHeap, 2 if
     *                  MileageHeap, 3 if SpecificPriceHeap, 4 if
     *                  SpecificMileageHeap.
     */
    public void swap(int index1, int index2, int whichHeap, IndirectionDLB indDLB) {
        HeapElement temp = this.heapArr[index2];
        this.heapArr[index2]=this.heapArr[index1];
        this.heapArr[index1]=temp;
        IndirectionDLBNode node = indDLB.getNode(temp.vin);
        if(whichHeap==1) {
            node.priceHeapIndex=index1;
        }
        else if(whichHeap==2) {
            node.mileageHeapIndex=index1;
        }
        else if(whichHeap==3) {
            node.specificPriceHeapIndex=index1;
        }
        else { //whichHeap is equal to 4.
            node.specificMileageHeapIndex=index1;
        }
    }

    public void delete(int index, IndirectionDLB indDLB, int whichHeap) {
        this.size--;
        HeapElement temp = this.heapArr[this.size];
        IndirectionDLBNode node = indDLB.getNode(temp.vin);
        this.heapArr[this.size]=null;
        this.heapArr[index]=temp;
        index=moveUp(index, indDLB, whichHeap);
        index=moveDown(index, indDLB, whichHeap);
        if(whichHeap==1) {
            node.priceHeapIndex=index;
        }
        else if(whichHeap==2) {
            node.mileageHeapIndex=index;
        }
        else if(whichHeap==3) {
            node.specificPriceHeapIndex=index;
        }
        else { //whichHeap is equal to 4.
            node.specificMileageHeapIndex=index;
        }
    }

    public void update(int index, int newMetric, IndirectionDLB indDLB, int whichHeap) {
        IndirectionDLBNode node = indDLB.getNode(this.heapArr[index].vin);
        this.heapArr[index].metric=newMetric;
        index=moveUp(index, indDLB, whichHeap);
        index=moveDown(index, indDLB, whichHeap);
        if(whichHeap==1) {
            node.priceHeapIndex=index;
        }
        else if(whichHeap==2) {
            node.mileageHeapIndex=index;
        }
        else if(whichHeap==3) {
            node.specificPriceHeapIndex=index;
        }
        else { //whichHeap is equal to 4.
            node.specificMileageHeapIndex=index;
        }
    }

}