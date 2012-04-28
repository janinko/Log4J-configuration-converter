package cz.muni.fi.pb138.log4jconverter;

public class Main {

    public static void main(String[] args) {
        InputLoader fl = new InputLoader("neco.xml");
        System.out.println(fl.getType());
        
    }
}
