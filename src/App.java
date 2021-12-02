import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Node head1 = new Node(1);
        Node iterator1 = head1;
        iterator1.next = new Node(2);
        iterator1 = iterator1.next;

        Node head2 = new Node(1);
        Node iterator2 = head2;
        iterator2.next = new Node(2);
        iterator2 = iterator2.next;
        Node result = calculateSum(head1, head2);

        while (result != null) {
            System.out.print(result.number);
            result = result.next;
        }
        System.out.println();
    }

    public static Node calculateSum(Node head1, Node head2) {
        Node reversedHead1 = reverse(head1);
        Node reversedHead2 = reverse(head2);

        Node resultList = null;
        Node iterator = null;
        boolean hasRemaining = false;
        while (true) {
            if (reversedHead1 == null && reversedHead2 == null) {
                break;
            }

            int num1 = reversedHead1 == null ? 0 : reversedHead1.number;
            int num2 = reversedHead2 == null ? 0 : reversedHead2.number;

            int sum = num1 + num2 + (hasRemaining ? 1 : 0);

            hasRemaining = sum > 10;

            if (resultList == null) {
                resultList = new Node(hasRemaining ? sum - 10 : sum);
                iterator = resultList;
            } else {
                iterator.next = new Node(hasRemaining ? sum - 10 : sum);
                iterator = iterator.next;
            }

            reversedHead1 = reversedHead1.next;
            reversedHead2 = reversedHead2.next;
        }
        if (hasRemaining) {
            iterator.next = new Node(1);
        }
        return reverse(resultList);

    }

    public static Node reverse(Node head) {
        Stack<Integer> stack = new Stack<>();
        Node iterator = head;
        while (iterator != null) {
            stack.push(iterator.number);
            iterator = iterator.next;
        }
        Node reversedList = new Node(stack.pop());
        iterator = reversedList;
        while (!stack.empty()) {
            iterator.next = new Node(stack.pop());
            iterator = iterator.next;
        }

        return reversedList;
    }
}
