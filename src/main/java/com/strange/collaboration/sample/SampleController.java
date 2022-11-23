package com.strange.collaboration.sample;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    SampleService sampleService;
    
    @GetMapping({"/index"})
    public String getIndex() {
    	log.info("sample index is called");

        return "sample/index";
    }

    /*
     *
     */

    @GetMapping({"/articles"})
    @Operation(summary = "Get ArticleList")
    public String getArticleList(Model model) throws Exception {

        model.addAttribute("articles",sampleService.findAllSample());

        return "sample/article/articleList";
    }

    @GetMapping({"/articles/{id}"})
    @Operation(summary = "Get ArticleDetail")
    public String getArticleDetail(Model model, @PathVariable Long id) throws Exception {
        sampleService.findSampleDetailById(id).ifPresent(sample -> model.addAttribute("article", sample));
        return "sample/article/articleDetail";
    }

    @DeleteMapping("article/{id}")
    @Operation(summary = "Delete Article")
    public void deleteArticle(@PathVariable Long id) throws Exception {
        log.info(id.toString());
        sampleService.deleteArticleById(id);

    }

    /*
     *
     */

    @GetMapping("/article")
    @Operation(summary = "Get postArticleForm for Posting Article")
    public String getArticleForm(Model model, Sample article) throws Exception {
        model.addAttribute("article", article);

        return "sample/article/postArticleForm";
    }

    @PostMapping("/article")
    @Operation(summary = "Post Article")
    public String postArticle(@ModelAttribute Sample article) throws Exception {
        log.info(article.toString());
        sampleService.addArticle(article);

        return "redirect:/sample/articles";
    }

    /*
     *
     */

    @GetMapping("/article/{id}")
    @Operation(summary = "Get ArticleUpdate for Updating Article")
    public String getArticleUpdateForm(Model model, @PathVariable Long id) throws Exception {
        log.info(sampleService.findSampleDetailById(id).toString());
        sampleService.findSampleDetailById(id).ifPresent(sample -> model.addAttribute("article", sample));
        return "sample/article/updateArticleForm";
    }

    @PutMapping("/article")
    @Operation(summary = "Put ArticleDetail")
    public String updateArticleDetail(@ModelAttribute Sample article) throws Exception {
        log.info("i am updateArticleDetail Method "+article.toString());
        sampleService.updateArticle(article);

        return "redirect:/sample/articles";
    }


}
