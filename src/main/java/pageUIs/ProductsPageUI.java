package pageUIs;

public class ProductsPageUI {
    // Quick view đang mở
    public static final String QUICK_VIEW_OPEN =
            "//div[@id='quick-view' and contains(@class,'show')]";

    // Nút close trong quick view đang mở
    public static final String CLOSE_QUICK_VIEW_ICON =
            "//div[@id='quick-view' and contains(@class,'show')]//button[@aria-label='close']";

    public static final String CART_ICON =
            "//div[@class='cart-icon']/ancestor::div[@id='entry_217825']";
}
