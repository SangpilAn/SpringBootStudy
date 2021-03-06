package com.sangpil.boot.persistance;

import com.sangpil.boot.domain.Board;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long>,
        QuerydslPredicateExecutor<Board> {

    public List<Board> findBoardByTitle(String title);

    public Collection<Board> findByWriter(String writer);

    // 작성자에 대한 like % 키워드 %
    public Collection<Board> findByWriterContaining(String writer);

    // OR 조건 처리
    public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);

    // title LIKE % ? % AND BNO > ?
    public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);

    // bno > ? ORDER BY bno DESC
    public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    // bno > ? ORDER BY bno DESC limit ?, ?
    public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);

    // == public List<Board> findByBnoGreaterThan(Long bno, Pageable paging);
    public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);

    @Query("SELECT b FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
    public List<Board> findByTitle(String title);

    @Query("select b from Board b where b.content like %:content% and b.bno > 0 " +
            "order by b.bno desc ")
    public List<Board> findByContent(@Param("content") String content);

    @Query("select b from #{#entityName} b where b.writer like %?1% and b.bno > 0 " +
            "order by b.bno desc")
    List<Board> findByWriter2(String writer);

    @Query("select b.bno, b.title, b.writer, b.regDate from Board b " +
            "where b.title like %?1% and b.bno > 0 order by b.bno desc")
    public List<Object[]> findByTitle2(String title);

    @Query(value = "select bno, title, writer from tbl_boards " +
            "where title like CONCAT('%','?1','%') and bno > 0 " +
            "order by bno desc ", nativeQuery = true)
    List<Object[]> findBytitle3(String title);

    @Query("select b from Board b where b.bno > 0 order by b.bno desc")
    public List<Board> findByPage(Pageable pageable);


}
