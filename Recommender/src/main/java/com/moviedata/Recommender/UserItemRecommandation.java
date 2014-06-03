package com.moviedata.Recommender;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;


public class UserItemRecommandation 
{
    public static void main( String[] args ) throws Exception
    {
    	DataModel model = new FileDataModel(new File("data/dataset.csv"));
    	// DataModel model = new FileDataModel(new File("data/ratings.csv"));
    	UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
    	UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
    	UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
    	List<RecommendedItem> recommendations = recommender.recommend(7, 3);
    	System.out.println("These are the articles the person also like to have and the expected rating:");
    	for (RecommendedItem recommendation : recommendations) 
    	{
    		System.out.println(recommendation);
    	}
    	long[] similarusers = recommender.mostSimilarUserIDs(2, 3);
    	System.out.println("");
    	System.out.println("These are the articles the person also like to have and the expected rating:");
    	for (long userrecommendation : similarusers) 
    	{
    		System.out.println(userrecommendation);
    	}
    }
}
