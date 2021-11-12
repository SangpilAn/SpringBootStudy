package ListType;

import Interface_from.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E>, Cloneable {

    private static final int DEFAULT_CAPACITY = 10; // 최소(기본) 용적 크기
    private static final Object[] EMPTY_ARRAY = {}; // 빈 배열

    private int size; // 요소 개수

    Object[] array; // 요소를 담을 배열

    // 생성자1 (초기 공간 할당 x)
    public ArrayList(){
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    // 생성자2 (초기 공간 할당 o)
    public ArrayList(int capacity){
        this.array = new Object[capacity];
        this.size = 0;
    }

    /**
     *  리스트의 동적할당을 위한 메서드
     */
    private void resize(){
        int array_capacity = array.length;

        // 리스트의 용적 크기가 0 인 경우
        if(Arrays.equals(array, EMPTY_ARRAY)){
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        // 리스트의 데이터 개수가 용적의 크기와 같은 경우
        if(size == array_capacity){
            int new_capacity = array_capacity * 2;

            // 복사할 배열보다 용적의 크기가 클 경우 배열 복사 후 나머지 공간을 null 로 채운 후 반환
            array = Arrays.copyOf(array, new_capacity);
        }

        // 리스트의 데이터 개수가 용적 크기의 절반 미만인 경우
        if(size < (array_capacity / 2)){
            int new_capacity = array_capacity / 2;

            // 복사할 배열보다 용적의 크기가 작을 경우 새로운 용적까지만 복사 후 반환
            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
        }
    }

    @Override
    public boolean add(E value) {
        addList(value);
        return true;
    }

    private void addList(E value) {

        // 리스트가 꽉 찬 경우 용적 재할당
        if(size == array.length){
            resize();
        }
        array[size] = value; // 마지막 위치에 요소 추가
        size++; // 사이즈 1 증가

    }

    @Override
    public void add(int index, E value) {

        // 영역을 벗어날 경우 예외 발생
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        // index 가 마지막 위치일 경우 addList 메소드로 요소 추가
        if(index == size){
            addList(value);
        }else{

            // 리스트가 꽉 차있다면 용적 재할당
            if(size == array.length){
                resize();
            }

            // index 기준 요소를 한 칸씩 뒤로 밀기
            for (int i = size; i > index; i--) {
                array[i] = array[i-1];
            }

            array[index] = value;
            size++;

        }

    }

    // 리스트 맨 앞에 요소 추가
    public void addFirst(E value){
        add(0, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        E element = (E) array[index];
        array[index] = null;

        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }

        size--;
        resize();
        return element;
    }

    @Override
    public boolean remove(Object value) {

        int index = indexOf(value);

        if(index == -1){
            return false;
        }

        remove(index);
        return true;
    }


    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        // 리스트 범위를 넘어가면 예외 발생
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        // Object 타입에서 E 타입으로 캐스팅 후 반환
        return (E) array[index];
    }

    @Override
    public void set(int index, E value) {

        // 리스트 범위를 넘어가면 예외 발생
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }else{
            array[index] = value;
        }

    }

    @Override
    public boolean contains(Object value) {

        return indexOf(value) >= 0;

    }

    @Override
    public int indexOf(Object value) {

        // value 와 같은 객체일 경우 i(위치) 반환
        for (int i = 0; i < size; i++) {
            if(array[i].equals(value)){
                return i;
            }
        }

        // 일치하는 것이 없을경우 -1을 반환
        return -1;
    }

    public int lastIndexOf(Object value){
        for (int i = size - 1; i >= 0; i--) {
            if(array[i].equals(value)){
                return i;
            }
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

        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }

    @Override
    public Object clone(){
        // 새로운 객체 생성
        ArrayList<?> cloneList = null;
        try {
            cloneList = (ArrayList<?>) super.clone();

            // 새로운 객체의 배열 생성
            cloneList.array = new Object[size];

            System.arraycopy(array, 0, cloneList.array, 0, size);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return cloneList;
    }
}
