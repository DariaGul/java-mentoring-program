package task1;

public class Main {

    public static void main(String[] args) {
        PropertiesHelper prop = new PropertiesHelper("prop");
        PropertiesHelper site = new PropertiesHelper("site");

        System.out.println(prop.getProperties());
        System.out.println(site.getProperties());
        System.out.println(site.getProperty("password"));
    }
}
