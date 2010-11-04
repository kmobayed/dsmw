//
//package dsmw;
//
//import net.sourceforge.jwbf.core.contentRep.SimpleArticle;
//import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;
//
//
//public class Wikipedia {
//    private String page;
//
//    Wikipedia (String S)
//    {
//        page = S;
//    }
//
//  public void getPage() throws Exception
//  {
//      MediaWikiBot b = new MediaWikiBot(page);
//      //b.login("user", "password");
//      SimpleArticle sa = new SimpleArticle(b.readContent("Main Page"));
//      System.out.println(sa.getText());
//   }
//}
