import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SentimentAnalyzer {

    public static SentimentValue sentimentAnalyse(String tweet) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = pipeline.process(tweet);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int value = RNNCoreAnnotations.getPredictedClass(tree);
            SentimentValue sentimentValue = SentimentValue.fromValue(value);
            System.out.println("Sentiment analysis of this tweet: "+ sentimentValue);
            return sentimentValue;
        }
        return SentimentValue.NEGATIVE;
    }
    public static void analyzeTweet(String tweet) {
        Document doc = new Document(tweet);
        System.out.println("#1 " + tweet + ": " + doc.sentences().size());
    }
    public static List<Status> filterTweetsBasedOnSentiment(SentimentValue sentiment, List<Status> tweets) {
        ArrayList<Status> result = new ArrayList<Status>();
        for (Status tweet : tweets) {
            if (sentimentAnalyse(tweet.getText()) == sentiment) {
                result.add(tweet);
            }
        }
        return result;
    }
    /*private static List<Status> searchTweets(String query) {
        return 0;
    }*/
}
