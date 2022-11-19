package com.strange.cooperation.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SampleService {

    @Autowired
    SampleRepository sampleRepository;

    public List<Sample> findAllSample() throws Exception {
        return sampleRepository.findAll();
    }

    public Optional<Sample> findSampleDetailById(Long id) throws Exception {
        // id를 통해서 특정 article을 불러옴, 불러온다는 것은 접근한다는것
        // 한편, Optional은 null 도 받을 수 있게 추가 설정해주는 것이라고 한다.
        // boot에서 자동설정해주는 것으로 우선 이해
        Optional<Sample> article=sampleRepository.findById(id);
        log.info(article.toString());

        // 접근할수있다면, 그 중에서도 조회수에 대해서 +1할 수 있다. 도전과제

        log.info(article.toString());

        return article;
    }

    public void addArticle(Sample article) throws  Exception{
        sampleRepository.save(article);
    }

    public void updateArticle(Sample article) throws Exception{
        sampleRepository.save(article);
//        Board find=selectOne(board.getBoardIdx()); // 기존 board
//        find.setContents(board.getContents());
//        find.setTitle(board.getTitle());
    }

//    public void updateHitCnt(Board board) throws Exception{
//        Board find=selectOne(board.getBoardIdx()); // 기존 board
//        find.setHitCnt(board.getHitCnt()+1);
//    }

    public void deleteArticleById(Long id) throws Exception{
        sampleRepository.deleteById(id);
    }
}
