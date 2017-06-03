import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {

    private T value;
    private List<Tree<T>> children;

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = Arrays.asList(children);
    }

    public String print(int indent, StringBuilder builder) {
        StringBuilder stringBuilder = new StringBuilder(builder);
        stringBuilder.append(String.join("", Collections.nCopies(indent, " ")) + this.value + "\n");

        for(Tree<T> child: this.children){
            StringBuilder newStr = new StringBuilder();
            stringBuilder.append(child.print(indent+2, newStr));
        }

        return stringBuilder.toString();
    }

    public void each(Consumer<T> consumer) {
        consumer.accept(this.value);
        for (Tree<T> child : children) {
            child.each(consumer);
        }
    }

    public Iterable<T> orderDFS() {
        List<T> result = new ArrayList<T>();

        this.DFS(this, result);

        return result;
    }

    private void DFS(Tree<T> tree, List<T> result){

        for (Tree<T> child : tree.children) {
            this.DFS(child, result);
        }

        result.add(tree.value);
    }

    public Iterable<T> orderBFS() {
        List<T> result = new ArrayList<T>();
        ArrayDeque<Tree<T>> queue = new ArrayDeque<>();
        queue.add(this);

        while (queue.size()>0){
            Tree<T> current = queue.remove();
            result.add(current.value);

            for(Tree<T> child : current.children){
                queue.add(child);
            }
        }

        return result;
    }



}