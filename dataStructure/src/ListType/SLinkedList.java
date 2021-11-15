package ListType;

import Interface_from.List;

import java.util.NoSuchElementException;

public class SLinkedList<E> implements List<E> {
    
    private Node<E> head;   // 노드의 첫 부분
    private Node<E> tail;   // 노드의 마지막 부분
    private int size;   // 요소 개수

    // 생성자
    public SLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 특정 위치의 노드를 반환하는 메소드
    private Node<E> search(int index){

        // 범위 밖일 경우 예외
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node<E> x = head;   // head 가 가리키는 노드부터 시작

        for (int i = 0; i < index; i++) {
            x = x.next; // x 노드의 다음 노드를 x 에 저장
        }

        return x;
    }

    public void addFirst(E value){

        Node<E> newNode = new Node<>(value);    // 새 노드 생성
        newNode.next = head;    // 새 노드의 다음 노드로 head 노드를 연결
        head = newNode; // head 가 가리키는 노드를 새 노드로 변경
        size++;

        /*
          다음에 가리킬 노드가 없는 경우(데이터가 새 노드밖에 없는 경우)
          데이터가 한 개이므로 새 노드는 처음 시작노드이자 마지막 노드이다.
         */
        if(head.next == null){
            tail = head;
        }

    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    private void addLast(E value) {
        Node<E> newNode = new Node<>(value);

        if(size == 0){  // 처음 넣는 노드인 경우 addFirst로 추가
            addFirst(value);
            return;
        }

        /*
        마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고
        tail이 가리키는 노드를 새 노드로 변경
         */
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, E value) {

        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        if(index == 0){
            addFirst(value);
            return;
        }

        if(index == size){
            addLast(value);
            return;
        }

        // 추가하려는 위치의 이전 노드
        Node<E> prev_Node = search(index - 1);

        // 추가하려는 위치의 노드
        Node<E> next_Node = prev_Node.next;

        // 추가하려는 노드
        Node<E> newNode = new Node<>(value);

        /*
        이전 노드가 가리키는 노드를 새 노드에 연결
        새 노드가 가리키는 노드는 next_Node 로 설정
         */
        prev_Node.next = newNode;
        newNode.next = next_Node;
        size++;
    }

    public E remove(){

        Node<E> headNode = head;

        if(headNode == null)    throw new NoSuchElementException();

        // 삭제된 노드를 반환하기 위한 임시 변수
        E element = headNode.data;

        // head 의 다음 노드
        Node<E> nextNode = head.next;

        // head 노드의 데이터를 모두 삭제
        head.data = null;
        head.next = null;

        // head 가 다음 노드를 가리키도록 업데이트
        head = nextNode;
        size--;

        /*
        삭제된 요소가 리스트의 유일한 요소였을 경우
        그 요소는 head 이자 tail 이었으므로
        삭제되면서 tail 도 가리킬 요소가 없기 때문에
        size 가 0일 경우 tail 도 null 로 변환
         */
        if(size == 0){
            tail = null;
        }

        return element;
    }

    @Override
    public E remove(int index) {

        // 삭제하려는 노드가 첫 번째 원소일 경우
        if(index == 0){
            return remove();
        }

        // 잘못된 범위에 대한 예외
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        Node<E> prevNode = search(index - 1);   // 삭제할 노드의 이전 노드
        Node<E> removeNode = prevNode.next; // 삭제할 노드
        Node<E> nextNode = removeNode.next; // 삭제할 노드의 다음 노드

        E element = removeNode.data;    // 삭제되는 노드의 데이터를 반환하기 위한 임시변수

        // 이전 노드가 가리키는 노드를 삭제하려는 노드의 다음 노드로 변경
        prevNode.next = nextNode;

        // 데이터 삭제
        removeNode.next = null;
        removeNode.data = null;
        size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {

        Node<E> prevNode = head;
        Node<E> x = head;

        while (x != null){
            if(value.equals(x.data)){
                break;
            }
            prevNode = x;
            x = x.next;
        }

        if(x == null){
            return false;
        }

        if(x.equals(head)){
            remove();
        }else{
            prevNode.next = x.next;

            x.data = null;
            x.next = null;
            size--;
        }

        return true;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        Node<E> replaceNode = search(index);
        replaceNode.data = value;
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;

        for (Node<E> x = head; x != null; x = x.next){
            if(value.equals(x.data)){
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (Node<E> x = head; x != null;){
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x = nextNode;
        }
        head = tail = null;
        size == 0;
    }
}
