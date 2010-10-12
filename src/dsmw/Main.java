/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dsmw;

import edu.nyu.cs.javagit.api.DotGit;
import edu.nyu.cs.javagit.api.JavaGitConfiguration;
import edu.nyu.cs.javagit.api.JavaGitException;
import edu.nyu.cs.javagit.api.commands.GitLogResponse.Commit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author klm
 */
public class Main {

        public static void main( String[] args ) throws JavaGitException, IOException
    {
        if (args.length<2)
        {
                System.err.println("Usage: java -jar loadData <TDB folder> <ontology file> ");
                System.exit(0);
        }

//        System.out.println("Git version : "+JavaGitConfiguration.getGitVersion());
//
//        File repositoryDirectory = new File("~/code/linux2.6/.git");
//        DotGit dotGit = DotGit.getInstance(repositoryDirectory);
//
//        // Print commit messages of the current branch
//        for (Commit c : dotGit.getLog()) {
//            System.out.println(c.getMessage());
//        }



        String DBdirectory = args[0] ;
        String ontoFile = "file:"+args[1];

        Site MySite = new Site("Site1");

        Jena J= new Jena(DBdirectory,ontoFile);

        J.addSite(MySite);

        J.listSites();
        System.out.println("===========");
        J.listStatements();
        J.close();
    }

}
