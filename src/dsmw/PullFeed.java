

package dsmw;

/**
 *
 * @author halaskaf
 */
public class PullFeed {
private String pullFeedID;
private PushFeed relatedPushFeed;
private String headID;
private String siteID;

public PullFeed(String id) {
    pullFeedID = id;
}

public PullFeed(String id, PushFeed r) {
    pullFeedID = id;
    relatedPushFeed = r;
}

    public String getHeadPullFeed() {
        return headID;
    }

    public void setHeadPullFeed(String headPullFeed) {
        this.headID = headPullFeed;
    }

    public PushFeed getRelatedPushFeed() {
        return relatedPushFeed;
    }

    public void setRelatedPushFeed(PushFeed relatedPushFeed) {
        this.relatedPushFeed = relatedPushFeed;
    }

    public void setSite(String S)
    {
        siteID=S;
    }

    public String getSite()
    {
        return siteID;
    }

    public String getPullFeedID() {
        return pullFeedID;
    }

    public void setPullFeedID(String pullFeedID) {
        this.pullFeedID = pullFeedID;
    }

    public ChangeSet get(ChangeSet c) {
//    if (this.getRelatedPushFeed().getPushFeedID().equals(c.getInPushFeed().getPushFeedID()))
//        if (c.getPreviousChgSet() != null)
//            return c.getPreviousChgSet();
    return null;
    }
    
}

