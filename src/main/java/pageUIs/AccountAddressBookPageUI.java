package pageUIs;

public class AccountAddressBookPageUI {
    public static final String NEW_ADDRESS_BUTTON="//a[normalize-space()='New Address']";
    public static final String ADDRESS_ACTION_LINK_BY_TEXT ="(//td[@class='text-right p-3']/child::a[normalize-space()='%s'])[2]";
    public static final String ADDRESS_BLOCK = "//td[@class='text-left p-3' and contains(normalize-space(),'%s') and contains(normalize-space(),'%s')]";

}

