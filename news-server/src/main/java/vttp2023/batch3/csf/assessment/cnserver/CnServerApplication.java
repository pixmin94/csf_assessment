package vttp2023.batch3.csf.assessment.cnserver;

import java.util.List;
import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2023.batch3.csf.assessment.cnserver.models.News;
import vttp2023.batch3.csf.assessment.cnserver.repositories.NewsRepository;

@SpringBootApplication
public class CnServerApplication {// implements CommandLineRunner {

	// @Autowired
	// private NewsRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(CnServerApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// 	News news = new News();
	// 	news.setTitle("Title");
	// 	news.setDescription("Lorem Ipsum");
	// 	List<String> tags = new ArrayList<>();
	// 	tags.add("test");
	// 	tags.add("tag");
	// 	news.setTags(tags);

	// 	ObjectId newsId = repo.postNews(news);
	// 	System.out.println(newsId);
	// }
}
