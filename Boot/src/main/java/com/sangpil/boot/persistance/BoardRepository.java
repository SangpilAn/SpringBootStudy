package com.sangpil.boot.persistance;

import com.sangpil.boot.domain.Board;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {

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
}
