import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("YKE6GZQQb30QL1VmkNGZ9PDuc")
                .setOAuthConsumerSecret("YKiPXe6sY4uHnEKVSPsDU5aOV8JNUijKf3V4qPr9juRkn5FOJm")
                .setOAuthAccessToken("143509053-OSU1SOsrfWXXpyDblNv8s26dUBKapvFvCReZJ8pc")
                .setOAuthAccessTokenSecret("ZwZU7j0Ac5VozEgoFTXHdWAmr5FKfuleaIuvDgAoclRqP");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        // The factory instance is re-useable and thread safe.
        try {
            System.out.println("Please enter a #Hashtag:");
            Query query = new Query(input);
            QueryResult result = twitter.search(query);
            List<Status> statuses = result.getTweets();
            System.out.println("Showing home timeline.");
            for (Status status : statuses) {
                SentimentAnalyzer.analyzeTweet(status.getText());
                SentimentAnalyzer.sentimentAnalyse(status.getText());
                SentimentAnalyzer.filterTweetsBasedOnSentiment(SentimentValue.fromValue(1), statuses);
                System.out.println("=========");
            }
            Scanner scanner1 = new Scanner(System.in);
            String input1 = scanner.nextLine();
            System.out.println("How are you feeling? Tweet with which sentiment do you want to see?:");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}