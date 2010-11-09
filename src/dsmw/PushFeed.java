
package dsmw;


/**
 *
 * @author halaskaf
 */
public class PushFeed {
    private String pushFeedID;
    private ChangeSet hasPushHead;
    private String siteID;
    private String headID;
  
    
    public PushFeed(String id) {
        pushFeedID =id;
    }

    public String getPushFeedID() {
        return pushFeedID;
    }

    public void setPushFeedID(String pushFeedID) {
        this.pushFeedID = pushFeedID;
    }


    public String getHeadPushFeed() {
        return headID;
    }

    public void setHeadPushFeed(String headPullFeed) {
        this.headID = headPullFeed;
    }

    public ChangeSet getPushHead() {
        return hasPushHead;
    }

    public void setPushHead(ChangeSet hasPushHead) {
        this.hasPushHead = hasPushHead;
    }
    public void setSite(String S)
    {
        siteID=S;
    }

    public String getSite()
    {
        return siteID;
    }


}
