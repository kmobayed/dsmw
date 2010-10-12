

package dsmw;

/**
 *
 * @author halaskaf
 */
public class PullFeed {
private String pullFeedID;
private PushFeed relatedPushFeed;
private ChangeSet headPullFeed;
private Site site;

public PullFeed(String id) {
    pullFeedID = id;
}

public PullFeed(String id, PushFeed r) {
    pullFeedID = id;
    relatedPushFeed = r;
}

    public ChangeSet getHeadPullFeed() {
        return headPullFeed;
    }

    public void setHeadPullFeed(ChangeSet headPullFeed) {
        this.headPullFeed = headPullFeed;
    }

    public PushFeed getRelatedPushFeed() {
        return relatedPushFeed;
    }

    public void setRelatedPushFeed(PushFeed relatedPushFeed) {
        this.relatedPushFeed = relatedPushFeed;
    }

    public void setSite(Site S)
    {
        site=S;
    }

    public Site getSite()
    {
        return site;
    }

    public String getPullFeedID() {
        return pullFeedID;
    }

    public void setPullFeedID(String pullFeedID) {
        this.pullFeedID = pullFeedID;
    }

    public ChangeSet get(ChangeSet c) {
    if (this.getRelatedPushFeed().getPushFeedID().equals(c.getInPushFeed().getPushFeedID()))
        if (c.getPreviousChgSet() != null)
            return c.getPreviousChgSet();
    return null;
}
    public void pull() {


  //      while (this.get(headPullFeed)!= null)
          //  if (this.get(headPullFeed).getChgSetID().equals(this.getRelatedPushFeed().))

        

    }
}

