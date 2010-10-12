package fr.univ.nantes.dsmwawarness;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.nyu.cs.javagit.api.*;
import edu.nyu.cs.javagit.api.commands.GitAddResponse;
import edu.nyu.cs.javagit.api.commands.GitLogResponse.Commit;
import edu.nyu.cs.javagit.test.utilities.FileUtilities;
import edu.nyu.cs.javagit.test.utilities.HelperGitCommands;




public class App 
{
    public static void main( String[] args )
    {
        if (args.length<2)
        {
                System.err.println("Usage: java -jar loadData <TDB folder> <ontology file> ");
                System.exit(0);
        }

        File repositoryDirectory = new File("~/code/linux2.6/.git");
        DotGit dotGit = DotGit.getInstance(repositoryDirectory);

        // Print commit messages of the current branch
        for (Commit c : dotGit.getLog()) {
            System.out.println(c.getMessage());
        }

        
        String DBdirectory = args[0] ;
        String ontoFile = "file:"+args[1];
        
        Site MySite = new Site("Site2");

        Jena J= new Jena(DBdirectory,ontoFile);

        J.addSite(MySite);

        J.listSites();
        System.out.println("===========");
        J.listStatements();
        J.close();
    }

    
}
