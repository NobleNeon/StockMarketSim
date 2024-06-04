public class Main{

    public static void main(String[] args) {

        Stock tesla = new Stock("TSLA");

        System.out.println(tesla.getCompanyName());
    }

    public double buyStock(String ticker, int shares){
        double cost = 0;
        double lastPrice = 0;
        cost = lastPrice * shares;
        return cost;
    }
    
    public double sellStock(String ticker, int shares){
        double value = 0;
        double lastPrice = 0;
        value = lastPrice * shares;
        return value;
    }

    public double shortStock(String ticker, int shares){
        double value = 0;
        double lastPrice = 0;
    value = lastPrice * shares;
        return value;
    }

    public double cover(String ticker, int shares){
        double value = 0;
        double lastPrice = 0;
        value = lastPrice * shares;
        return value;
    }
}