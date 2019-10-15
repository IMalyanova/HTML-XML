import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class TegsHTML {


    public static void main(String[] args) throws IOException {

        // https://course.skillbox.ru/webdev/
        String pathHtml = "res\\page.html";
        String pathWrite = "res\\img";

        for ( ; ; ){

            System.out.println("Enter the address of the page from which you want to download images: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String strReadLine = reader.readLine();

            FileOutputStream outputStream = new FileOutputStream(pathHtml);

            if (strReadLine.equals("EXIT")) {
                System.out.println("exit");
                break;
            }else {
                try {
                    URL url = new URL(strReadLine);
                    InputStream stream = url.openStream();

                    for ( ; ; ){
                        int code = stream.read();
                        if (code < 0) {
                            break;
                        }
                        outputStream.write(code);
                    }
                    outputStream.flush();
                    outputStream.close();

                    Document doc = Jsoup.parse(new File(pathHtml), "UTF-8");
                    Elements elementsImg = doc.select("img");
                    PrintWriter printWriter = new PrintWriter(pathWrite);

                    for (Element element : elementsImg){
                        String strSrc = String.valueOf(element.attr("src"));
                        String strDO = String.valueOf(element.attr("data-original"));
                        if ((strSrc.equals(""))) {
                            printWriter.write(strDO + "\r\n");
                        } else {
                            printWriter.write(strSrc + "\r\n");
                        }
                    }
                    printWriter.flush();
                    printWriter.close();
                    System.out.println("Done \r\n\r\n");

                }catch (MalformedURLException e ){
                    System.out.println("The address is entered incorrectly!");
                }
            }
        }
    }


//    public static void main(String[] args) throws IOException {
//
//        String path = "res/page.html";
//        Document doc = Jsoup.parse(new File(path), "UTF-8");
//        Elements elementsImg = doc.select("img");
//
//        for (Element element : elementsImg){
//            String strSrc = String.valueOf(element.attr("src"));
//            String strDO = String.valueOf(element.attr("data-original"));
//            if ((strSrc.equals(""))) {
//                System.out.println(strDO);
//            } else {
//                System.out.println(strSrc);
//            }
//        }
//    }

}
