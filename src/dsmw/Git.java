

package dsmw;

import edu.nyu.cs.javagit.api.DotGit;
import edu.nyu.cs.javagit.api.JavaGitConfiguration;
import edu.nyu.cs.javagit.api.JavaGitException;
import edu.nyu.cs.javagit.api.commands.GitLogOptions;
import edu.nyu.cs.javagit.api.commands.GitLogResponse.Commit;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
/**
 *
 * @author klm
 */
public class Git {

    private String GitBin;

    Git(String bin)
    {
        GitBin=bin;
    }

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

}
