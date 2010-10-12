/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dsmw;


import edu.nyu.cs.javagit.api.JavaGitException;
import java.io.IOException;

/**
 *
 * @author klm
 */
public class Main {

        public static void main( String[] args ) throws JavaGitException, IOException, Exception
    {
        if (args.length<2)
        {
                System.err.println("Usage: java -jar loadData <TDB folder> <ontology file> ");
                System.exit(0);
        }


        Git G = new Git("/usr/bin/");
        G.getVersion();
        G.getLog();

        String page="http://en.wikipedia.org/w/";
        Wikipedia W= new Wikipedia(page);
        W.getPage();

        String DBdirectory = args[0] ;
        String ontoFile = "file:"+args[1];

        Site MySite = new Site("Site1");

        Jena J= new Jena(DBdirectory,ontoFile);

        J.addSite(MySite);

        J.listSites();
        //System.out.println("===========");
        //J.listStatements();
        J.close();
    }

}
