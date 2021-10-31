package com.sangpil.boot;

import com.sangpil.boot.domain.Board;
import com.sangpil.boot.persistance.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootApplicationTests {

    @Autowired
    private BoardRepository repo;

    @Test
    public void findByTitle() {

        repo.findBoardByTitle("제목..177")
                .forEach(board -> System.out.println(board));

    }

    @Test
    public void testByWriter(){

        Collection<Board> results = repo.findByWriter("user00");

        results.forEach(board -> System.out.println(board));

    }

    @Test
    public void testByWriterContaining(){

        Collection<Board> results = repo.findByWriterContaining("05");

        results.forEach(board -> System.out.println(board));

    }

    @Test
    public void testByTitleAndBno(){

        Collection<Board> results = repo.findByTitleContainingAndBnoGreaterThan("5", 50L);

        results.forEach(board -> System.out.println(board));

    }

    @Test
public void testBnoOrderBy(){

    Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(90L);
    results.forEach(board -> System.out.println(board));
}

    @Test
    public void testBnoOrderByPaging(){

        Pageable paging = PageRequest.of(0,10);

        Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(0L,paging);

        results.forEach(board -> System.out.println(board));
    }

//    @Test
//    public void testBnoPagingSort(){
//
//        Pageable paging = PageRequest.of(0,10, Sort.Direction.ASC, "bno");
//
//        Collection<Board> results = repo.findByBnoGreaterThan(0L,paging);
//        results.forEach(board -> System.out.println(board));
//    }

    @Test
    public void testBnoPagingSort(){

        Pageable paging = PageRequest.of(0,10, Sort.Direction.ASC,"bno");

        Page<Board> result = repo.findByBnoGreaterThan(0L, paging);

        System.out.println("PAGE SIZE: "+result.getSize());
        System.out.println("TOTAL PAGES: "+result.getTotalPages());
        System.out.println("TOTAL COUNT: "+result.getTotalElements());
        System.out.println("NEXT: "+result.nextPageable());

        List<Board> list = result.getContent();

        list.forEach(board -> System.out.println(board));
    }

    @Test
    public void testByTitle2(){

        repo.findByTitle("17").forEach(board -> System.out.println(board));
    }

}
