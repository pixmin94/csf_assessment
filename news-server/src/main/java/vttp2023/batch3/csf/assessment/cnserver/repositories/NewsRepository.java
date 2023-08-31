package vttp2023.batch3.csf.assessment.cnserver.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationPipeline;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.csf.assessment.cnserver.models.News;
import vttp2023.batch3.csf.assessment.cnserver.models.TagCount;
import vttp2023.batch3.csf.assessment.cnserver.Utils;

@Repository
public class NewsRepository {

	@Autowired
	private MongoTemplate template;

	/* db.news.insert({
	    "title": "News Title",
	    "description": "lorem ipsum",
	    "tags": ['test', 'tag']
	})
	 */	
	public ObjectId postNews(News news) {
		Document doc = new Document();
		doc.put("postDate", System.currentTimeMillis());
		doc.put("title", news.getTitle());
		doc.put("description", news.getDescription());
		// doc.put("image", url);
		doc.put("tags", news.getTags());

		Document newDoc = template.insert(doc, "news");
		return newDoc.getObjectId("_id");
	}
	

	/* db.news.aggregate([
		{ $unwind: '$tags'},
		{ $group: { 
				_id: "$tags",
				count: { $sum: 1 }
		} },
		{ $project: {
				_id: 0,
				tag: "$_id",
				count: 1
		} },
	]) */
	public List<Document> getTags() {
		UnwindOperation unwind = Aggregation.unwind("tags");
		GroupOperation group = Aggregation.group("tags")
			.count().as("count");
		ProjectionOperation project = Aggregation.project("_id", "count")
			.and("_id").as("tag");
		Aggregation pipeline = Aggregation.newAggregation(unwind, group, project);
		AggregationResults<Document> result = template
			.aggregate(pipeline, "news", Document.class);
		return result.getMappedResults();
	}


	/* db.news.aggregate([
		{ $unwind: '$tags' },
		{ $match: { tags : "test"}},
		{ $project: {
			"title": 1,
			"description": 1,
			"tags": 1
		}}
	]) */
	public List<Document> getNewsByTag(String tag) {
		UnwindOperation unwind = Aggregation.unwind("tags");
		MatchOperation match = Aggregation.match(Criteria.where("tags").is(tag));
		ProjectionOperation project = Aggregation.project("title", "description", "tags");
		Aggregation pipeline = Aggregation.newAggregation(unwind, match, project);

		AggregationResults<Document> result = template.aggregate(pipeline, "news", Document.class);
		return result.getMappedResults();
	}


}
