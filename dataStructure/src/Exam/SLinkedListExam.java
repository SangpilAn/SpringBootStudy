package Exam;

import ListType.SLinkedList;

public class SLinkedListExam {

    /**
     * clone 예제
     */
    public void exam(){
        SLinkedList<Integer> original = new SLinkedList<>();
        original.add(10);

        SLinkedList<Integer> copy = original;
        @SuppressWarnings("unchecked") SLinkedList<Integer> clone = (SLinkedList<Integer>) original.clone();

        copy.add(20);
        clone.add(30);

        System.out.println("original list");
        for (int i = 0; i < original.size(); i++) {
            System.out.println("index "+i+" data = "+original.get(i));
        }

        System.out.println("copy list");
        for (int i = 0; i < copy.size(); i++) {
            System.out.println("index "+i+" data = "+copy.get(i));
        }

        System.out.println("clone list");
        for (int i = 0; i < clone.size(); i++) {
            System.out.println("index "+i+" data = "+clone.get(i));
        }

        System.out.println("\noriginal list reference : "+original);
        System.out.println("copy list reference : "+copy);
        System.out.println("clone list reference : "+clone);

    }

    /**
     * sort 예제
     */
    public void exam2(){
        SLinkedList<Integer> list = new SLinkedList<>();
        list.add(10);
        list.add(5);
        list.add(13);
        list.add(20);
        list.add(1);
        list.add(8);

        list.sort();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
