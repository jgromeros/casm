package co.jgrs.casm.jira.jiraclient;

import java.net.URI;
import java.util.Iterator;

import co.jgrs.casm.analyzers.StandardAnalyzer;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.IssueLink;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;

public class JiraSimpleClient {

    public static void main(String[] args) throws Exception {
  
        JerseyJiraRestClientFactory f = new JerseyJiraRestClientFactory();
        JiraRestClient jc = f.createWithBasicHttpAuthentication(new URI("https://jira.aciworldwide.com"), "romerog", "c0ntr4s3N$d1f1c!l");
 
        Issue issue1 = jc.getIssueClient().getIssue("WEB-987", null);
        System.out.println("description to search: " + issue1.getDescription());

//        SearchResult r = jc.getSearchClient().searchJql("resolution = Unresolved AND assignee in (currentUser()) ORDER BY updatedDate DESC", null);

        StandardAnalyzer analyzer = new StandardAnalyzer();
        String newQuery = analyzer.analyzeQuery(issue1.getDescription());
        System.out.println("Query: " + newQuery);
        SearchResult r = jc.getSearchClient().searchJql("text ~ \"" + newQuery + "\"", null);
        Iterator<BasicIssue> it = r.getIssues().iterator();
        int i = 0;
        while (it.hasNext() && i < 10) {
            i++;
            Issue issue = jc.getIssueClient().getIssue(((BasicIssue)it.next()).getKey(), null);
             
            System.out.println("Issue: " + issue.getKey() + " summary:" + issue.getSummary());
             
            Iterator<IssueLink> itLink = issue.getIssueLinks().iterator();
            while (itLink.hasNext()) {
                 
                IssueLink issueLink = (IssueLink)itLink.next();
                Issue issueL = jc.getIssueClient().getIssue((issueLink).getTargetIssueKey(), null);
                 
                System.out.println("Link: " + issueLink.getIssueLinkType().getDescription() + ": " + issueL.getKey() + " " + issueL.getSummary() + " " + issueL.getFieldByName("Story Points").getValue());
                 
            }
             
        }
         
    }
}
