public class No {
    private int key;
    private int value;
    private No parent;
    private No leftChild;
    private No rightChild;

    public No(int key, int value){
        this.key = key;
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

    public int getKey(){
        return this.key;
    }

    public No getParent(){
        return this.parent;
    }

    public void setParent(No parent){
        this.parent = parent;
    }

    public No getLeftChild(){
        return this.leftChild;
    }

    public void setLeftChild(No leftChild){
        this.leftChild = leftChild;
    }

    public No getRightChild(){
        return this.rightChild;
    }

    public void setRightChild(No rightChild){
        this.rightChild = rightChild;
    }

    public Object getValue(){
        return this.value;
    }

    public void setValue(int value){
        this.value = value;
    }
}