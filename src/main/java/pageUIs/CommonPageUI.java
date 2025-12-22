package pageUIs;

public class CommonPageUI {
    public static final String DYNAMIC_PAGE_HEADING = "//div[@id='content']//h1";

    public static final String DYNAMIC_TEXTBOX_BY_NAME = "//input[@name='%s']";
    //public static final String DYNAMIC_CONTINUE_BUTTON_BY_VALUE="//div[@class='float-right']";
    public static final String DYNAMIC_SIDEBAR_ITEM_BY_NAME = "//aside[contains(@id,'column-right')]//a[normalize-space()='%s']";

    // Header dropdown "My account"
    public static final String MY_ACCOUNT_HEADER_MENU =
            "//a[contains(@class,'dropdown-toggle') and normalize-space()='My account']";

    // Item trong dropdown theo text: Login / Register / Logout...
    public static final String MY_ACCOUNT_HEADER_ITEM_BY_TEXT = "//ul[contains(@class,'dropdown-menu')]//a[normalize-space()='%s']";
    public static final String DYNAMIC_SUCCESS_MESSAGE = "//div[contains(@class,'alert-success')]";
    public static final String DYNAMIC_WARNING_MESSAGE="//div[contains(@class,'alert-danger')]";
    public static final String DYNAMIC_WARNING_TEXT="//div[contains(@class,'text-danger')]";
    public static final String DYNAMIC_BACK_BUTTON = "//a[normalize-space()='Back']";

    public static final String DYNAMIC_HOME_MENU_NAME=  "//div[contains(@class,'shop-by-category')]//a[@role='button' and @aria-label='Shop by Category']";
    public static final String DYNAMIC_SIDEBAR_CATEGORIES="//span[normalize-space()='%s']";
    public static final String DYNAMIC_SHOPING_TO_CART_BUTTON="//a[normalize-space()='%s']";
    public static final String DYNAMIC_PRODUCT_ACTION_BUTTON_BY_NAME_AND_TYPE="//a[contains(@class,'text-ellipsis') and contains(normalize-space(.),'%s')]/ancestor::div[contains(@class,'product-thumb')]//button[contains(@class,'%s')]";

}
