package vendingmachine;

public class VendingMachine extends LoopInput {
    private static final String BUY_PRODUCT_MESSAGE = "구매할 상품명을 입력해 주세요.";
    private static final Change change = new Change();
    private static final Validator validator = new Validator();
    private static final Customer customer = new Customer();
    private static ProductList productList = new ProductList();
    private static OutputMessage outputMessage = new OutputMessage();

    public VendingMachine() {
        this.initializeVendingMachine();
    }

    public void run() {
        customer.input();
        while (checkAvailableSellState()) {
            this.input();
        }
        change.returnChange(customer.getCustomerMoney());
    }

    public void inputMethod() {
        this.inputSellProduct();
    }

    private void initializeVendingMachine() {
        change.input();
        productList.input();
        change.createRandomChange();
    }

    private void inputSellProduct(){
        String productName = inputString(BUY_PRODUCT_MESSAGE);
        validator.validateExistedProduct(productList, productName);
        validator.validateProductIsAvailable(productList, productName);
        int productPrice = productList.sellProduct(productName);
        customer.calcCustomerMoney(productPrice);
        outputMessage.printInputMoney(customer.getCustomerMoney());
    }

    private boolean checkAvailableSellState() {
        return productList.checkAvailableState(customer.getCustomerMoney());
    }
}
