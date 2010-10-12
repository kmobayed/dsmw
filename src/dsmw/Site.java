
package dsmw;


import java.util.Collection;

/**
 *
 * @author halaskaf
 */
public class Site {
private String siteID;
private Collection<Document> docs;
private Collection<PushFeed> pushs;
private Collection<PullFeed> pulls;

public Site(String id) {
    siteID =id;
}

    public Collection<Document> getDocs() {
        return docs;
    }

    public void setDocs(Collection<Document> docs) {
        this.docs = docs;
    }

    public Collection<PullFeed> getPulls() {
        return pulls;
    }

    public void setPulls(Collection<PullFeed> pulls) {
        this.pulls = pulls;
    }

    public Collection<PushFeed> getPushs() {
        return pushs;
    }

    public void setPushs(Collection<PushFeed> pushs) {
        this.pushs = pushs;
    }

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

}
