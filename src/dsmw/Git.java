

package dsmw;

import edu.nyu.cs.javagit.api.DotGit;
import edu.nyu.cs.javagit.api.JavaGitConfiguration;
import edu.nyu.cs.javagit.api.JavaGitException;
import edu.nyu.cs.javagit.api.commands.GitLogResponse.Commit;

import java.io.File;
import java.io.IOException;
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

        System.out.println("Log size = "+dotGit.getLog().size());

        // Print commit messages of the current branch
//        for (Commit c : dotGit.getLog()) {
//            System.out.println(c.getMessage());
//        }
    }

}
