package vttp2023.batch3.csf.assessment.cnserver.services;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.csf.assessment.cnserver.Utils;
import vttp2023.batch3.csf.assessment.cnserver.models.News;
import vttp2023.batch3.csf.assessment.cnserver.models.TagCount;
import vttp2023.batch3.csf.assessment.cnserver.repositories.NewsRepository;

@Service
public class NewsService {

	@Autowired
	private NewsRepository repo;
	
	// TODO: Task 1
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns the news id
	public String postNews(News news) {
		ObjectId newsIdObj = repo.postNews(news);
		return newsIdObj.toString();
	}
	 
	// TODO: Task 2
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns a list of tags and their associated count
	public List<TagCount> getTags() {
		List<TagCount> tagsObj = repo.getTags().stream()
			.map(o -> Utils.toTagCountObj(o))
			.collect(Collectors.toList());
		return tagsObj;
	}

	// TODO: Task 3
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns a list of news
	public List<News> getNewsByTag(String tag) {
		List<News> news = repo.getNewsByTag(tag).stream()
			.map(o -> Utils.toNewsObject(o))
			.collect(Collectors.toList());
		return news;
	}
	
}
