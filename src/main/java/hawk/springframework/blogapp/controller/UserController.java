package hawk.springframework.blogapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hawk.springframework.blogapp.domain.Article;
import hawk.springframework.blogapp.domain.Comment;
import hawk.springframework.blogapp.service.ArticleService;
import hawk.springframework.blogapp.service.CommentService;
import hawk.springframework.blogapp.service.TagService;
import hawk.springframework.blogapp.util.Pager;

@Controller
public class UserController {
	
	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 6;
	
	private ArticleService articleService;
	private TagService tagService;
	private CommentService commentService;
	
	@Autowired
	public UserController(TagService tagService, ArticleService articleService, CommentService commentService) {
		this.tagService = tagService;
		this.articleService = articleService;
		this.commentService = commentService;
	}

	@GetMapping("/")
	public String mainPage (@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional <Integer> page, Model model) {
		
		model.addAttribute("tags", tagService.getAllTags());
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get()-1;
		
		Page<Article> articles = articleService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		Pager pager = new Pager(articles.getTotalPages(), articles.getNumber(), BUTTONS_TO_SHOW);
		model.addAttribute("articles", articles);
		model.addAttribute("selectedPageSize", evalPageSize);
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
    public String addNewComment(@PathVariable Long articleId, @ModelAttribute("newComment") Comment comment) {
    	commentService.addNewComment(articleId, comment);
    	return "redirect:/showArticle/"+articleId;
    }
}
