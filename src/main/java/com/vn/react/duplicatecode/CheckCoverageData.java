package com.vn.react.duplicatecode;

public class CheckCoverageData {

    static Node head;

    public Node reverse(Node node) {
        Node prev = null;
        Node current = node;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        node = prev;
        return node;
    }

    Node reverseUtil(Node curr, Node prev) {
        if (curr.next == null) {
            head = curr;
            curr.next = prev;
            return head;
        }

        Node next1 = curr.next;
        curr.next = prev;
        reverseUtil(next1, curr);
        return head;
    }

    public Node reverseDuplicate(Node node) {
        Node prev = null;
        Node current = node;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        node = prev;
        return node;
    }

    Node reverseUtilDuplicate(Node curr, Node prev) {
        if (curr.next == null) {
            head = curr;
            curr.next = prev;
            return head;
        }

        Node next1 = curr.next;
        curr.next = prev;
         reverseUtilDuplicate(next1, curr);
        return head;
    }

    public  void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    private static class Node{
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
}
