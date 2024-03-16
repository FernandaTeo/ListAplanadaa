public class ListAplanada {

    // Clase interna que define un nodo de la lista
    public static class Node {
        public int value;
        public Node prev;
        public Node next;
        public Node child;

        // Constructor que inicializa el nodo con un valor
        public Node(int value) {
            this.value = value;
        }
    }

    // Método para aplanar una lista con nodos que pueden tener hijos
    public static Node flatten(Node head) {
        // Si la cabeza es nula, no hay nada que aplanar
        if (head == null) {
            return head;
        }

        // Nodo actual para recorrer la lista
        Node curr = head;
        // Recorre la lista
        while (curr != null) {
            // Si el nodo actual tiene un hijo
            if (curr.child != null) {
                // Guarda una referencia temporal al siguiente nodo
                Node temp = curr.next;
                // Aplana la lista recursivamente a partir del hijo
                Node childLast = flatten(curr.child);
                // Conecta el hijo a la lista principal
                curr.next = curr.child;
                curr.child.prev = curr;
                curr.child = null;
                childLast.next = temp;
                if (temp != null) {
                    temp.prev = childLast;
                }
            }
            // Si llega al final de la lista actual, sale del bucle
            if (curr.next == null) {
                break;
            }
            // Avanza al siguiente nodo
            curr = curr.next;
        }

        // Retorna la cabeza de la lista aplanada
        return head;
    }

    // Método para imprimir los elementos de la lista
    public static void printList(Node node) {
        while (node != null) {
            System.out.print(node.value + " <-> "); // Imprime el valor del nodo actual
            node = node.next; // Avanza al siguiente nodo
        }
        System.out.println("null"); // Cuando se llega al final de la lista, imprime "null"
    }

    // Método principal para probar la aplanación de la lista
    public static void main(String[] args) {
        // Creación de la lista con nodos y posibles hijos
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;
        head.next.next = new Node(3);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(4);
        head.next.next.next.prev = head.next.next;
        head.next.next.child = new Node(5);
        head.next.next.child.prev = head.next.next;
        head.next.next.child.next = new Node(6);
        head.next.next.child.next.prev = head.next.next.child;
        head.next.next.child.next.next = new Node(7);
        head.next.next.child.next.next.prev = head.next.next.child.next;
        head.next.next.child.next.next.next = new Node(8);
        head.next.next.child.next.next.next.prev = head.next.next.child.next.next;

        // Imprime la lista original
        System.out.println("Lista original:");
        printList(head);

        // Aplana la lista y la imprime
        System.out.println("Lista aplanada:");
        printList(flatten(head));
    }
}