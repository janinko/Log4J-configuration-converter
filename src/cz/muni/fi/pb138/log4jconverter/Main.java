package cz.muni.fi.pb138.log4jconverter;

public class Main {

    public static void main(String[] args) {
        FileLoader fl = new FileLoader("neco.xmlsd");
        System.out.println(fl.getType());
        
    }
}
