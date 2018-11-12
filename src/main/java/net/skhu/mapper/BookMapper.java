package net.skhu.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Book;

@Mapper
public interface BookMapper {
	public List<Book> findAll();
	public void insert(Book book);
	public Book findById(int id);
	public void update(Book book);
	public void delete(int id);
}
