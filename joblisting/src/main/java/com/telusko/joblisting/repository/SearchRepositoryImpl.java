package com.telusko.jobListing.repository;

import com.telusko.joblisting.model.Post;

import java.util.ArrayList;
import java.util.List;

@Component

public class SearchRepository implements SearchRepository{

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByText(String text){

        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("minor");
        MongoCollection <Document> collection = database.getcollection("");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                        new Document("query", "java")
                        .append("path", Arrays.asList("techs", "desc", "profile")))),
                        new Document("$sort",
                        new Document("exp", 1L)),
                        new Document("$limit", 5L)));

        result.forEach(doc -> posts.add(converter.read(Post.class,doc)));

        return posts;
    }
}