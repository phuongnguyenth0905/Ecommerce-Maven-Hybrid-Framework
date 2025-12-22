package pageUIs;

public class CheckOutPageUI {
    public static final String CART_ROW_BY_PRODUCT ="//tr[.//a[normalize-space()='%s']]";

    public static final String QTY_INPUT_IN_ROW = CART_ROW_BY_PRODUCT + "//input[contains(@name,'quantity')]";

    public static final String UPDATE_BTN_IN_ROW =CART_ROW_BY_PRODUCT + "//button[contains(@class,'btn-primary')]";

    public static final String REMOVE_BTN_IN_ROW =CART_ROW_BY_PRODUCT + "//button[contains(@class,'btn-danger')]";
    public static final String QTY_INPUT_BY_PRODUCT =
            "//tr[.//a[contains(normalize-space(),'%s')]]//input[contains(@name,'quantity')]";
    public static final String PRODUCT_NAME="//span[@class='text-danger']/following::a[normalize-space()='iMac']";
}
