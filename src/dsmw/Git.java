
package dsmw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author klm
 */
public class Git {

    private String GitFolder;

    Git(String bin)
    {
        GitFolder = bin;
        String err = null;
        String cmd = "cd " + GitFolder;
        try {

            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((err = stdError.readLine()) != null) {
                System.out.print("Error :");
                System.out.println(err);
            }

        } catch (IOException ex) {
            Logger.getLogger(Git.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

/**
 public void config() throws IOException, JavaGitException
    {
        JavaGitConfiguration.setGitPath(GitBin);
    }

    public void getVersion() throws JavaGitException
    {

        System.out.println("Git version : "+JavaGitConfiguration.getGitVersion());

    }

    public void getLog() throws JavaGitException, IOException
    {
        File repositoryDirectory = new File("/Users/klm/code/cakephp");
        DotGit dotGit = DotGit.getInstance(repositoryDirectory);

        System.out.println("Log size = "+dotGit.getLog().size())
                ;

         //Print commit messages of the current branch

        //for (Commit c : dotGit.getLog()) {

        GitLogOptions options=new GitLogOptions();
        options.setOptOrderingReverse(true);
        options.setOptLimitFirstParent(true);
        options.setOptLimitFullHistory(true);
        //options.set

            Commit c= dotGit.getLog(options).get(0);
            System.out.println(c.getMessage());
            System.out.println(c.getAuthor());
            System.out.println(c.getDateString());
            System.out.println(c.getFiles());
            System.out.println(c.getFilesChanged());
            System.out.println(c.getLinesDeleted());
            System.out.println(c.getLinesInserted());

            if (c.getMergeDetails()!= null)
            {
                Iterator i = c.getMergeDetails().iterator();
                while (i.hasNext())
                {
                    String MD = i.next().toString();
                    System.out.println("Merge Detail ----"+MD);
                }
            }

        //}
    }
 */

    public void gitLogNoMerge(Jena J)
    {
        String cmd1="git log --abbrev-commit --parents --no-merges --format=%h%n%p%n%s";
        String CSid = null;
        String parent = null;
        String message = null;
        String err = null;
        ChangeSet CS =null;

        try
        {
            Process p = Runtime.getRuntime().exec(cmd1);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((CSid = stdInput.readLine()) != null)
            {
                CS=new ChangeSet(CSid);

                //System.out.println("CS="+CSid);


                if ((parent = stdInput.readLine()) !=null)
                {
                        //System.out.println("\t parent="+parent);
                        CS.addPreviousChgSet(parent);
                }

                if ((message = stdInput.readLine()) !=null)
                {
                //    System.out.println("\t message="+message);
                    CS.setMessage(message);
                }
            }

            while ((err = stdError.readLine()) != null)
            {
                System.out.print("Error :");
                System.out.println(err);
            }
            J.addChangeSet(CS);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Git.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gitLogMerge(Jena J)
    {
        String cmd1="git log --abbrev-commit --parents --merges --format=%h%n%p%n%s";
        String CSid = null;
        String tmpP = null;
        String message = null;
        String err = null;
        ChangeSet CS = null;
        Site S = null;
        PullFeed PF = null;
        int count1=0,count2=0;
        try
        {
            Process p = Runtime.getRuntime().exec(cmd1);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((CSid = stdInput.readLine()) != null)
            {
                //System.out.println("CS="+CSid);
                CS=new ChangeSet(CSid);

                if ((tmpP = stdInput.readLine()) !=null)
                {
                    String[] parents;
                    parents=tmpP.split(" ");
                    for (int i =0; i<parents.length; i++)
                    {
                        //System.out.println("\t p="+parents[i]);
                        CS.addPreviousChgSet(parents[i]);
                    }
                }

                if ((message = stdInput.readLine()) !=null)
                {
                    count1++;
                    CS.setMessage(message);
                    //System.out.println("\t m="+message);
                    //Pattern pat = Pattern.compile("'\\S+'");
                    Pattern pat = Pattern.compile("git://\\S+");
                    
                    Matcher m = pat.matcher(message);
                    while (m.find())
                    {
                        count2++;
                        String site=m.group();
                        S = new Site(site);
                        //site=site.substring(1, site.length());
                        //site=site.substring(0, site.length() - 1);
                        J.addSite(S);
                        //System.out.println("found site : "+S.getSiteID());
                    }
                    
                    PF= new PullFeed(CS.getChgSetID());
                    PF.setHeadPullFeed(CS.getChgSetID());
                    PF.setSite(S.getSiteID());
                    J.addPullFeed(PF);
                }
            }
            System.out.println("found "+count1+" merged CS and "+count2+" sites");

            while ((err = stdError.readLine()) != null)
            {
                System.out.print("Error :");
                System.out.println(err);
            }
            J.addChangeSet(CS);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Git.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String gitGetFirstCS()
    {
        String out = null;
        String err=null;
        try {
           
            String cmd1="git log -n 1 --abbrev-commit --pretty=format:%h";
            
            Process p = Runtime.getRuntime().exec(cmd1);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            out = stdInput.readLine();

            while ((err = stdError.readLine()) != null) {
                System.out.print("Error :");
                System.out.println(err);
            }
        } catch (IOException ex) {
            Logger.getLogger(Git.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    public ChangeSet gitGetCSdata(String CS)
    {
        ArrayList<String> out = new ArrayList<String>();
        String tmp = null;
        String err = null;
        ChangeSet CS1=new ChangeSet();
        try {
           
            String cmd1 = "git show --abbrev-commit --parents --format=%h%n%p%n%s" +CS;
            Process p = Runtime.getRuntime().exec(cmd1);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((tmp = stdInput.readLine()) != null) {
                out.add(tmp);
                
            }

            CS1.setChgSetID(out.get(0));

            String[] tmpP;
            String parents=out.get(1);
            tmpP=parents.split(" ");
            for (int i =0; i<tmpP.length; i++)
            {
                System.out.println(tmpP[i]);
            }
            while ((err = stdError.readLine()) != null) {
                System.out.print("Error :");
                System.out.println(err);
            }
        } catch (IOException ex) {
            Logger.getLogger(Git.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CS1;
    }

}
