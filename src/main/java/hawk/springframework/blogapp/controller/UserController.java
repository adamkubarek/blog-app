package hawk.springframework.blogapp.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hawk.springframework.blogapp.domain.Article;
import hawk.springframework.blogapp.domain.Comment;
import hawk.springframework.blogapp.domain.Tag;
import hawk.springframework.blogapp.service.ArticleService;
import hawk.springframework.blogapp.service.CommentService;
import hawk.springframework.blogapp.service.TagService;
import hawk.springframework.blogapp.util.Pager;

@Controller
public class UserController {
	
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 6;
	
	private final ArticleService articleService;
	private final TagService tagService;
	private final CommentService commentService;
	
	@Autowired
	public UserController(TagService tagService, ArticleService articleService,
			CommentService commentService) {
		this.tagService = tagService;
		this.articleService = articleService;
		this.commentService = commentService;
	}

	@GetMapping("/")
	public String getArticlesInMainPage (@RequestParam("page") Optional <Integer> page,
			Model model) {
		model.addAttribute("tags", tagService.getAllTags());
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get()-1;
		
		Page<Article> articles = articleService.findAllPageable(PageRequest.of(evalPage, INITIAL_PAGE_SIZE));
		Pager pager = new Pager(articles.getTotalPages(), articles.getNumber());
		model.addAttribute("articles", articles);
		model.addAttribute("selectedPageSize", INITIAL_PAGE_SIZE);
		model.addAttribute("pager", pager);
		
		return "index";
	}
	
	@GetMapping("/byTag/{tagName}")
	public String getArticlesByTagInMainPage(@PathVariable String tagName, 
			@RequestParam("page") Optional <Integer> page, Model model) {
		model.addAttribute("tags", tagService.getAllTags());
		Tag tag = tagService.findTagByName(tagName);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get()-1;
		
		Page <Article> articles = articleService.findArticlesByTags(tag,
				PageRequest.of(evalPage, INITIAL_PAGE_SIZE));
		Pager pager = new Pager(articles.getTotalPages(), articles.getNumber());
		model.addAttribute("articles", articles);
		model.addAttribute("selectedPageSize", INITIAL_PAGE_SIZE);
		model.addAttribute("pager", pager);

		return "index";
	}
	
    @GetMapping("/login")
    public String login() {
        return "/login";
    }
    
    @GetMapping("/showArticle/{articleId}")
    public String showArticle(@PathVariable Long articleId, Model model) {
    	model.addAttribute("article", articleService.findArticleById(articleId));
    	Comment newComment = new Comment();
    	model.addAttribute("newComment", newComment);
    	return "showArticle";
    }
    
    @PostMapping("/article/{articleId}/addComment")
    public String addComment(@Valid @ModelAttribute("newComment") Comment comment,
    		BindingResult result, @PathVariable Long articleId, Model model) {
    	if(result.hasErrors()) {
    		model.addAttribute("article", articleService.findArticleById(articleId));
    		return "showArticle";
    	}
    	commentService.addNewComment(articleId, comment);
    	return "redirect:/showArticle/" + articleId;
    }
}
