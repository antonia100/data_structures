import java.util.Collections;
import java.util.function.Consumer;

public class BinaryTree<T> {
    private T value;
    private BinaryTree<T> leftChild, rightChild = null;

    public BinaryTree(T value) {
        this.value = value;
    }

    public BinaryTree(T value, BinaryTree<T> child) {
        this.value = value;
        this.leftChild = child;
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public String printIndentedPreOrder(int indent, StringBuilder builder) {
        StringBuilder stringBuilder = new StringBuilder(builder);
        stringBuilder.append(String.join("", Collections.nCopies(indent, " ")) + this.value + "\n");

        if(this.leftChild!=null){
            StringBuilder newStr = new StringBuilder();
            stringBuilder.append(this.leftChild.printIndentedPreOrder(indent+2, newStr));
        }

        if(this.rightChild!=null){
            StringBuilder newStr = new StringBuilder();
            stringBuilder.append(this.rightChild.printIndentedPreOrder(indent+2, newStr));
        }

        return stringBuilder.toString();
    }

    public void eachInOrder(Consumer<T> consumer) {
        if(this.leftChild!=null){
            this.leftChild.eachInOrder(consumer);
        }

        consumer.accept(this.value);

        if(this.rightChild!=null){
           this.rightChild.eachInOrder(consumer);
        }
    }

    public void eachPostOrder(Consumer<T> consumer) {
        if(this.leftChild!=null){
            this.leftChild.eachPostOrder(consumer);
        }

        if(this.rightChild!=null){
            this.rightChild.eachPostOrder(consumer);
        }

        consumer.accept(this.value);

    }
}
