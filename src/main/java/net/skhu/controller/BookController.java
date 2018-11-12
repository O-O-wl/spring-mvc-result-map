package net.skhu.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.dto.Book;
import net.skhu.mapper.BookMapper;

import net.skhu.dto.Category;
import net.skhu.mapper.CategoryMapper;

import net.skhu.dto.Publisher;
import net.skhu.mapper.PublisherMapper;


@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired BookMapper bookMapper;
	@Autowired PublisherMapper publisherMapper;
	@Autowired CategoryMapper categoryMapper;
	
	@RequestMapping("list1")
	public String list1(Model model) {
		List<Book> books=bookMapper.findAll();
		model.addAttribute("books",books);
		return "book/list1";
	}
	@RequestMapping("list2")
	public String list2(Model model) {
		List<Book> books=bookMapper.findAll();
		model.addAttribute("books",books);
		return "book/list2";
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	public String create_before(Model model) {
		Book book=new Book();
		model.addAttribute("book",book);
		List<Category> categories=categoryMapper.findAll();
		List<Publisher> publishers=publisherMapper.findAll();
		
		model.addAttribute("publishers",publishers);
		model.addAttribute("categories",categories);
		return"book/edit";
	}
	@RequestMapping(value="create",method=RequestMethod.POST)
	public String create_after(Model model,Book book)
{
	bookMapper.insert(book);
		return"redirect:list1";
}
	
	@RequestMapping(value="edit",method=RequestMethod.GET)
	public String edit_before(Model model,@RequestParam("id")int id )
{
	Book book=bookMapper.findById(id);
	List<Category> categories=categoryMapper.findAll();
	List<Publisher> publishers=publisherMapper.findAll();
	model.addAttribute("book",book);
	model.addAttribute("publishers",publishers);
	model.addAttribute("categories",categories);
	
	return"book/edit";
}

	@RequestMapping(value="edit",method=RequestMethod.POST)
	public String edit_after(Model model,Book book )
	{
		bookMapper.update(book);
	
		return"redirect:list1";
	}
	
	@RequestMapping(value="delete")
	public String delete(Model model,@RequestParam("id")int id) {
		bookMapper.delete(id);
		return"redirect:list1";
	}
	


}
