package vendingmachine;

import java.util.HashMap;

import camp.nextstep.edu.missionutils.Console;

public class ProductList {
    private static int MINIMUM_PRICE = Integer.MAX_VALUE;
    private static final HashMap<String, Product> productMap = new HashMap<>();
    private static final InputMessage inputMessage = new InputMessage();
    private static OutputMessage outputMessage = new OutputMessage();
    private static final Validator validator = new Validator();

    public void inputInitialProduct() {
        while (true) {
            try {
                insertProduct();
                break;
            } catch (IllegalArgumentException exception) {
                outputMessage.printErrorMessage(exception.getMessage());
            }
        }
    }

    public void insertProduct() {
        inputMessage.printInsertProductMessage();
        String productsInformation = Console.readLine();
        initProductMap();
        String[] productInformationList = validator.validateSplitRegex(productsInformation);
        setNumberOfProducts(productInformationList);
    }

    public boolean findProductByName(String name) {
        return productMap.containsKey(name);
    }

    public boolean isAvailableProduct(String name) {
        Product product = productMap.get(name);
        return product.isAvailable();
    }

    public boolean compareMinimumPrice(int customerMoney) {
        return customerMoney >= MINIMUM_PRICE;
    }

    public boolean checkAvailableState(int customerMoney) {
        return checkProductAmount() && checkAvailableBuyProduct(customerMoney);
    }

    private boolean checkAvailableBuyProduct(int customerMoney) {
        return customerMoney > MINIMUM_PRICE;
    }

    private boolean checkProductAmount() {
        for (String name : productMap.keySet()) {
            Product product = productMap.get(name);
            if (product.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public int sellProduct(String product) {
        Product sellProduct = productMap.get(product);
        sellProduct.sell();
        return sellProduct.price;
    }

    private void initProductMap() {
        productMap.clear();
    }

    private void setNumberOfProducts(String[] productInformationList) {
        for (String productInformation : productInformationList) {
            Product product = validator.validateSplitProductInformation(productMap, productInformation);
            compareMinimumPrice(product);
            productMap.put(product.name, product);
        }
    }

    private void compareMinimumPrice(Product product) {
        if (MINIMUM_PRICE > product.price) {
            MINIMUM_PRICE = product.price;
        }
    }
}
