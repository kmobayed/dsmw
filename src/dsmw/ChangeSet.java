
package dsmw;

import java.util.ArrayList;

/**
 *
 * @author halaskaf
 */
public class ChangeSet {
    private String chgSetID;
    private ArrayList<Patch> patches;
    private PushFeed inPushFeed;
    private PullFeed inPullFeed;
    private ArrayList<String> previousChgSetID;
    private String diff;
    private String message;
    private String date;

    public ChangeSet(String id) {
        chgSetID =id;
        previousChgSetID= new ArrayList<String>();
    }

    public ChangeSet() {
        chgSetID ="";
    }

    public ChangeSet(String id, PushFeed pu) {
        chgSetID =id;
        inPushFeed =pu;
    }

    public ArrayList<String> getPreviousChgSet() {
        return previousChgSetID;
    }

    public void addPreviousChgSet(String previousChgSet) {
        this.previousChgSetID.add(previousChgSet);
    }


    public String getChgSetID() {
        return chgSetID;
    }

    public void setDiff(String df) {
        diff=df;
    }

    public String getDiff() {
        return diff;
    }

    public void setMessage(String str) {
        message=str;
    }

    public String getMessage() {
        return message;
    }

    public void setDate(String str) {
        date=str;
    }

    public String getDate() {
        return date;
    }

    public void setChgSetID(String chgSetID) {
        this.chgSetID = chgSetID;
    }

    public PullFeed getInPullFeed() {
        return inPullFeed;
    }

    public void setInPullFeed(PullFeed inPullFeed) {
        this.inPullFeed = inPullFeed;
    }

    public PushFeed getInPushFeed() {
        return inPushFeed;
    }

    public void setInPushFeed(PushFeed inPushFeed) {
        this.inPushFeed = inPushFeed;
    }

    public ArrayList<Patch> getPatches() {
        return patches;
    }

    public void setPatches(ArrayList<Patch> patches) {
        this.patches = patches;
    }


//public void push(ArrayList<Patch> patches, Document doc) {
//
//    // push patches and make them as published
//    for (Patch a : patches)
//     if (!a.getPublished())
//         if (a.getDoc().getDocID().equals(doc.getDocID())) {
//           this.patches.add(a);
//           a.setPublished(Boolean.TRUE);
//         }
//  this.getInPushFeed().setHasPushHead(this);
//  this.setPreviousChgSet(this.getInPushFeed().getHasPushHead());
//
//}

}
