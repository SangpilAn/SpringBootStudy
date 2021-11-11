package Interface_from;

public interface List<E> {

    /**
     *  리스트에 요소 추가
     * @param value 리스트에 추가할 요소
     * @return 중복 요소가 있으면 {@code false}, 없으면 {@code true} 반환
     *
     */
    boolean add(E value);

    /**
     *  리스트 특정 위치에 요소 추가
     * @param index 요소가 추가될 위치
     * @param value 리스트에 추가할 요소
     *
     */
    void add(int index, E value);

    /**
     *  리스트의 index 위치에 있는 요소 삭제
     * @param index 삭제할 요소의 위치
     * @return 삭제된 요소 반환
     *
     */
    E remove(int index);

    /**
     *  리스트의 특정 요소 삭제, 요소가 여러개인 경우 첫번째 요소 삭제
     * @param value 삭제할 요소
     * @return 삭제 실패 시 {@code false}, 성공 시 {@code true}
     *
     */
    boolean remove(Object value);

    /**
     *  리스트의 index 위치에 있는 요소 반환
     * @param index 반환할 요소 위치
     * @return index 위치에 있는 요소 반환
     *
     */
    E get(int index);

    /**
     *  리스트의 특정 위치의 요소를 새 요소로 변경
     * @param index 요소의 위치
     * @param value 변경할 요소
     *
     */
    void set(int index, E value);

    /**
     *  특정 요소가 리스트에 있는지 확인
     * @param value 확인할 요소
     * @return 리스트 내 요소가 없다면 {@code false}, 있다면 {@code true}
     *
     */
    boolean contains(Object value);

    /**
     *  리스트의 특정 요소가 몇번째 위치에 있는지 확인
     * @param value 위치를 확인할 요소
     * @return 요소의 위치 반환
     *
     */
    int indexOf(Object value);

    /**
     *  리스트에 있는 요소 개수 반환
     * @return 리스트에 있는 요소 개수 반환
     *
     */
    int size();

    /**
     *  리스트에 요소가 비어있는지 확인
     * @return 요소가 존재하면 {@code false}, 비어있으면 {@code true}
     *
     */
    boolean isEmpty();

    /**
     *  리스트의 모든 요소 제거
     *
     */
    public void clear();

}
