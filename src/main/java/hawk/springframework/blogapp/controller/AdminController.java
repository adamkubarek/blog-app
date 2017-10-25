package hawk.springframework.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hawk.springframework.blogapp.domain.Tag;
import hawk.springframework.blogapp.service.ArticleService;
import hawk.springframework.blogapp.service.CommentService;
import hawk.springframework.blogapp.service.TagService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private ArticleService articleService;
	private CommentService commentService;
	private TagService tagService;
	
	@Autowired
	public AdminController(ArticleService articleService, CommentService commentService, TagService tagService) {
		this.articleService = articleService;
		this.commentService = commentService;
		this.tagService = tagService;
	}
	

	@GetMapping("/")
	public String adminPanel() {
		return "admin/adminPanel";
	}
	
	@GetMapping("/showArticles")
	public String showArticles(Model model) {
		model.addAttribute("articles", articleService.findAllArticles());
		return "admin/article/showArticles";
	}
	
	@GetMapping("/showTags")
	public String showTags(Model model) {
		model.addAttribute("tags", tagService.getAllTags());
		Tag newTag = new Tag();
		model.addAttribute("newTag", newTag);
		return "admin/tag/showTags";
	}
	
	@PostMapping("/tag/save")
	public String saveTag(@ModelAttribute("newTag") Tag tag) {
		tagService.saveTag(tag);
		return "redirect:/admin/showTags";
	}
	
	@GetMapping("/showComments")
	public String showComments(Model model) {
		model.addAttribute("comments", commentService.getAllComments());
		return "admin/comment/showComments";
	}
	
	@GetMapping("/addArticle")
	public String addArticle() {
		return "admin/addArticle";
	}
}
