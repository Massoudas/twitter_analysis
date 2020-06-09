import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

class TwitterAnalyzer {


    private static SentimentValue getSentiment(String tweet) {
        return SentimentValue;
    }
    private static List<Status> searchTweets(String query) {
        return 0;
    }
    private static List<Status> filterTweetsBasedOnSentiment(SentimentValue sentiment, List<Status> tweets) {
        ArrayList<Status> result = new ArrayList<Status>();
        for (Status tweet : tweets) {
            if (getSentiment(tweet.getText()) == sentiment) {
                result.add(tweet);
            }
        }
        return result;
    }
    private static String mostMentionedWord(List<Status> tweets) {
        System.out.println(tweets);
        String word;
        return word;
    }
}
