package hawk.springframework.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hawk.springframework.blogapp.domain.Article;
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
	
	@GetMapping("/addArticle")
	public String addArticle(Model model) {
		Article newArticle = new Article();
		model.addAttribute("newArticle", newArticle);
		model.addAttribute("allTags", tagService.getAllTags());
		return "admin/article/addArticle";
	}
	
	@GetMapping("/article/{articleId}/update")
	public String updateArticle(@PathVariable Long articleId, Model model) {
		Article articleToEdit = articleService.findArticleById(articleId);
		model.addAttribute("newArticle", articleToEdit);
		model.addAttribute("allTags", tagService.getAllTags());
		return "admin/article/addArticle";
	}
	
	@GetMapping("/article/{articleId}/delete")
	public String deleteArticle(@PathVariable Long articleId) {
		articleService.deleteArticle(articleId);
		return "redirect:/admin/showArticles";
	}
	
	@PostMapping("/addArticle")
	public String saveArticle(@ModelAttribute("newArticle") Article article) {
		articleService.saveArticle(article);
		return "redirect:/admin/showArticles";
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
	
	@GetMapping("/tag/{tagId}/delete")
	public String deleteTag(@PathVariable Long tagId) {
		tagService.deleteTag(tagId);
		return "redirect:/admin/showTags";
	}
	
	@GetMapping("/showComments")
	public String showComments(Model model) {
		model.addAttribute("comments", commentService.getAllComments());
		return "admin/comment/showComments";
	}
	
	@GetMapping("/comment/{commentId}/delete")
	public String deleteComment(@PathVariable Long commentId) {
		commentService.deleteComment(commentId);
		return "redirect:/admin/showComments";
	}
}
